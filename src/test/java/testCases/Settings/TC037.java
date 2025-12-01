package testCases.Settings;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC037 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verify_SelectAll_And_ClearAllButton_Clickable_On_TestStep() throws InterruptedException {
        logger.info("****** Starting TC037: verify SelectAll And ClearAllButton Clickable On TestStep *****************");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickTestStep();
            logger.info("Navigated to Test Step section");

            boolean areButtonsClickable = otherTab.SelectAllAndClearAllButtonsClickable();
            Assert.assertTrue(areButtonsClickable, "Select All and/or Clear All button is not clickable");
            logger.info("Verified Select All and Clear All buttons are clickable on TestStep Tab");

//            otherTab.clickOnSelectAll();
//            logger.info("Clicked on selectAll Button");
//
//            otherTab.clickOnClearAll();
//            logger.info("Clicked on ClearAll Button");
        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
