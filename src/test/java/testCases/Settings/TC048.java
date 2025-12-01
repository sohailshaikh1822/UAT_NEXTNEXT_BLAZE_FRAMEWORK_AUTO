package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC048 extends BaseClass {

    @Test(dataProvider = "tc048", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_able_to_click_on_the_checkbox_of_available_customfield(
            String rowName
    ) throws InterruptedException {
        logger.info("****** Starting TC48: Verify that user able to click on the check box of available custom field *****************");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickTestSuite();
            logger.info("Navigated to TestSuite section");

            boolean isClickable = globalTab.isCheckboxClickable(rowName);
            Assert.assertTrue(isClickable, "Checkbox is not clickable for row: " + rowName);
            logger.info("Verified checkbox is clickable for row: " + rowName);

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
