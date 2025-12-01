package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC006 extends BaseClass {

    @Test(dataProvider = "tc006", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verifythatuserabletoclickonthecheckboxofavailablecustomfield(String rowName) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify that user able to click on the check box of available custom field *****************");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickGlobalFieldSetting();
            logger.info("Navigated to Global Field Settings section");

            globalTab.clickonCheckbox(rowName);
            logger.info("Clicked on checkbox for row: " + rowName);
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
