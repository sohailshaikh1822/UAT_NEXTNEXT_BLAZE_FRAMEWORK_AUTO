package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC002 extends BaseClass {

    @Test(dataProvider = "tc002", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_is_able_to_create_a_Global_Custom_Field(
            String fieldName,
            String textBox,
            String objType1,
            String objType2)
            throws InterruptedException {
        logger.info("****** Starting the TC002 :  Verify that user is able to create a Global Custom Field.*****************");
        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickGlobalFieldSetting();
            logger.info("Navigated to Global Field Settings section");

            globalTab.clickonAddGlobalField();
            logger.info("Clicked on Add Global Field button");

            long timeMillis = System.currentTimeMillis();
            String dynamicfield = fieldName + timeMillis;
            globalTab.EnterFieldName(fieldName);
            logger.info("Entered Field Name: " + fieldName);

            globalTab.SelectDataType(textBox);
            logger.info("Selected Data Type as Text Box");

            globalTab.clickObjectTypeCheckbox(objType1);
            logger.info("Selected Object Type: Test Case");

            globalTab.clickObjectTypeCheckbox(objType2);
            logger.info("Selected Object Type: Requirement");

            globalTab.clickSaveButton();
            logger.info("Clicked on Save button to add global field");

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
