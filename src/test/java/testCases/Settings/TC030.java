package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC030 extends BaseClass {

    @Test(dataProvider = "tc030", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyClickOnCheckboxOfAvailableCustomFieldForTestCaseTab(
            String fieldName,
            String fieldtype
    ) throws InterruptedException {
        logger.info("****** Starting the TC030 ***************");

        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            WaitUtils.waitFor1000Milliseconds();

            otherTab.clickTestCase();
            logger.info("Navigated to TestCase Tab");

            otherTab.clickOnAddCustomField();
            logger.info("Clicked on Add Custom Field button");

            otherTab.createCustomEnterFieldName(fieldName);
            logger.info("Entered Custom Field Name: " + fieldName);

            otherTab.createCustomSelectDataType(fieldtype);
            logger.info("Selected Custom Field Type: " + fieldtype);

            otherTab.clickcreatefieldButton();
            logger.info("clicked on create field button#");

            WaitUtils.waitFor1000Milliseconds();

            otherTab.checkboxForRow(fieldName).click();
            logger.info("Clicked on checkbox");
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
