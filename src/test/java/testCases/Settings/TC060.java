package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC060 extends BaseClass {

    @Test(dataProvider = "tc060", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_able_to_click_on_the_checkbox_of_available_customfield_in_defect_section(
            String rowName) throws InterruptedException {
        logger.info(
                "****** Starting TC48: Verify that user able to click on the check box of available custom field *****************");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickDefect();
            logger.info("Navigated to Defect section");

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
