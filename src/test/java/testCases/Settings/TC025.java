package testCases.Settings;

import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC025 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifySelectAllAndClearAllButtonForRequirementField() throws InterruptedException {
        logger.info("****** Starting the TC025 ***************");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            WaitUtils.waitFor1000Milliseconds();

            otherTab.clickRequirement();
            logger.info("Navigated to Requirement tab");

            WaitUtils.waitFor1000Milliseconds();

            otherTab.clickOnSelectAll();
            logger.info("Clicked on select All Button");

            WaitUtils.waitFor1000Milliseconds();

            otherTab.clickOnClearAll();
            logger.info("Clicked on Clear All button");

            WaitUtils.waitFor1000Milliseconds();

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
