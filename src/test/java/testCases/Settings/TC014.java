package testCases.Settings;

import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC014 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_able_to_access_the_Setting_and_navigate_to_Release_tab() throws InterruptedException {
        logger.info("****** Starting the TC014 :  Verify that user able to access the Setting.Verify that user able to access the Setting and navigate to Release tab*****************");
        try {
            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");
            GlobalTabPage globalTabPage = new GlobalTabPage(getDriver());
            globalTabPage.clickCurrentUserAndGoToSettings();

            OtherTabPage otherTabPage = new OtherTabPage(getDriver());
            otherTabPage.clickOnRelease();
            logger.info("clicked on release");
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
