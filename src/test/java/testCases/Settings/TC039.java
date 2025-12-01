package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC039 extends BaseClass {

    @Test(dataProvider = "tc039", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyCreationOfCustomeFieldInsideTestCYcleTab(
            String fieldName,
            String fieldtype
    ) throws InterruptedException {

        logger.info("****** Starting the TC039 ******");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickTestCycle();
            logger.info("Navigated to TestCycle tab");

            otherTab.clickOnAddCustomField();
            logger.info("Clicked on Add Custom Field button");

            otherTab.createCustomEnterFieldName(fieldName);
            logger.info("Entered Custom Field Name: " + fieldName);

            otherTab.createCustomSelectDataType(fieldtype);
            logger.info("Selected Custom Field Type: " + fieldtype);

            otherTab.clickcreatefieldButton();
            logger.info("clicked on create field button#");

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
