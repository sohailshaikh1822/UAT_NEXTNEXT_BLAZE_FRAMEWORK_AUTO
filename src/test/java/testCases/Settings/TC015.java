package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC015 extends BaseClass {

    @Test(dataProvider = "tc015", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_is_able_to_create_a_Custom_Field_inside_Release_Tab(
            String fieldName,
            String textBox)
            throws InterruptedException {
        logger.info("****** Starting the TC015 :  Verify that user is able to create a Custom Field inside Release Tab*****************");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickOnRelease();
            logger.info("Navigated to Release section");

            otherTab.clickOnAddCustomField();
            logger.info("Clicked on custom Field button");

            long timeMillis = System.currentTimeMillis();
            String dynamicfield = fieldName + timeMillis;

            otherTab.createCustomEnterFieldName(dynamicfield);
            logger.info("Entered Field Name: " + dynamicfield);

            otherTab.createCustomSelectDataType(textBox);
            logger.info("Selected Data Type as Text Box");

            otherTab.clickcreatefieldButton();
            logger.info("Clicked on create field button");

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
