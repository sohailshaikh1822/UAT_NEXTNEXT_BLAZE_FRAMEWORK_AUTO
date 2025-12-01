package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC018 extends BaseClass {

    @Test(dataProvider = "tc018", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class, groups = {
        "Settings"})
    public void verifyClickOnCheckboxOfAvailableCustomField(
            String fieldName,
            String textBox,
            String objType1,
            String objType2)
            throws InterruptedException {

        logger.info(
                "****** Starting the TC018 : Verify that user able to click on the check box of available custom field ******");

        try {

            login();
            logger.info("User logged in successfully with valid credentials");

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

            globalTab.EnterFieldName(dynamicfield);
            logger.info("Entered Field Name: " + dynamicfield);

            globalTab.SelectDataType(textBox);
            logger.info("Selected Data Type: " + textBox);

            globalTab.clickObjectTypeCheckbox(objType1);
            logger.info("Selected Object Type: " + objType1);

            globalTab.clickObjectTypeCheckbox(objType2);
            logger.info("Selected Object Type: " + objType2);

            globalTab.clickSaveButton();
            logger.info("Clicked on Save button to add global field");

            globalTab.clickShowInGridCheckbox(dynamicfield);
            logger.info("Clicked on the 'Show in Grid' checkbox for field: " + dynamicfield);

            boolean isChecked = globalTab.isShowInGridCheckboxSelected(dynamicfield);
            if (isChecked) {
                logger.info("Verified: The 'Show in Grid' checkbox is selected (Activated)");
            } else {
                logger.info("Verified: The 'Show in Grid' checkbox is deselected (Deactivated)");
            }

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC018 Test Case Finished Successfully *************************");
    }
}
