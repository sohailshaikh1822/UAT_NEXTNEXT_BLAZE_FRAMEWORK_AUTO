package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC053 extends BaseClass {

    @Test(dataProvider = "tc041", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_delete_Custom_Field_inside_TestRuns_Tab(
            String fieldName1,
            String fieldType, String fieldName2
    ) throws InterruptedException {

        logger.info("****** Starting the TC009: Verify that user is able to create a Custom Field inside Module Tab *****************");

        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickTestRuns();
            logger.info("Navigated to Module tab");

            otherTab.clickOnAddCustomField();
            logger.info("Clicked on Add Custom Field button");

            otherTab.createCustomEnterFieldName(fieldName1);
            logger.info("Entered Custom Field Name: " + fieldName1);

            otherTab.createCustomSelectDataType(fieldType);
            logger.info("Selected Custom Field Type: " + fieldType);

            otherTab.clickcreatefieldButton();
            logger.info("clicked on create field button#");

            otherTab.clickOnDelete(fieldName2);
            logger.info("Clicked Delete icon for the field: " + fieldName2);

            otherTab.clickOnDeleteRowConfirmation();
            logger.info("clicked on yes confirmation");

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
