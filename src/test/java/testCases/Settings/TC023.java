package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC023 extends BaseClass {

    @Test(dataProvider = "tc023", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_can_delete_a_custom_field_using_the_Delete_icon(
            String customfield,
            String Datatype,
            String fieldName
    //            String value,
    //            String text
    ) throws InterruptedException {

        logger.info("****** Starting the TC023:Verify that user can delete a custom field using the Delete icon***************");

        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            Thread.sleep(3000);

            otherTab.clickRequirement();
            logger.info("Navigated to Requirement tab");

            otherTab.clickOnAddCustomField();
            logger.info("clicked on add custom field");

            otherTab.createCustomEnterFieldName(customfield);
            logger.info("Custom field:" + customfield);

            otherTab.createCustomSelectDataType(Datatype);
            logger.info("selected datatype:" + Datatype);

            otherTab.clickcreatefieldButton();

            otherTab.clickOnDelete(fieldName);
            logger.info("clicked on delete row:" + fieldName);

            otherTab.clickConfirmYesButton();
            logger.info("Clicked on confirm button:" + "yes");
//
//            otherTab.clickOnAddCustomField(); ;
//            otherTab.createCustomEnterFieldName(value);
//            otherTab.createCustomSelectDataType(text);
//            otherTab.clickcreatefieldButton();

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
