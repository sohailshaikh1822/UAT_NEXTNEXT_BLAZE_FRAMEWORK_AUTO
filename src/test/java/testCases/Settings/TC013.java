package testCases.Settings;

import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC013 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifySelectAllAndCLearAllButton()
            throws InterruptedException {
        logger.info("****** Starting the TC002 :  Verify that user is able to create a Global Custom Field.*****************");
        logger.info("****** Starting the TC013*****************");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickGlobalFieldSetting();
            logger.info("Navigated  to Global Field Settings section");

            globalTab.clickonSelectAll();
            logger.info("Clicked on Select All Button ");

            Thread.sleep(3000);

            globalTab.clickonClearAll();
            logger.info("Clicked on Clear All Button");
            Thread.sleep(3000);
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
