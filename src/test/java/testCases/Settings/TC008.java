package testCases.Settings;

import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC008 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_is_able_to_select_Module_Tab() throws InterruptedException {

        logger.info("****** Starting the TC009: Verify that user is able to create a Custom Field inside Module Tab *****************");

        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickModule();
            logger.info("Navigated to Module tab");

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
