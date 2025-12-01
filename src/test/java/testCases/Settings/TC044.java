package testCases.Settings;

import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC044 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_able_to_access_the_Setting_and_navigate_to_TestSuite_tab() throws InterruptedException {
        logger.info("****** Starting the TC044 : Verify that user able to access the Setting and navigate to TestSuite tab*****************");
        try {
            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");
            GlobalTabPage globalTabPage = new GlobalTabPage(getDriver());
            globalTabPage.clickCurrentUserAndGoToSettings();
            logger.info("clicked on setting tab");

            OtherTabPage otherTabPage = new OtherTabPage(getDriver());
            otherTabPage.clickTestSuite();
            logger.info("clicked on TestSuite Tab");
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
