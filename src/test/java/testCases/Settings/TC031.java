package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC031 extends BaseClass {

    @Test(dataProvider = "tc002", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_SelectAll_and_ClearAll_for_CustomFields(String fieldName, String textBox, String objType1,
            String objType2) throws InterruptedException {

        logger.info("****** Starting TC031 : Verify Select All and Clear All functionality for Custom Fields ******");

        try {

            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickGlobalFieldSetting();
            logger.info("Navigated to Global Field Settings section");

            if (!globalTab.isCustomFieldPresent(fieldName)) {
                logger.warn("Custom field not found. Creating one as per TC002 steps...");

                globalTab.clickonAddGlobalField();
                logger.info("Clicked on Add Global Field button");

                globalTab.EnterFieldName(fieldName);
                logger.info("Entered Field Name: " + fieldName);

                globalTab.SelectDataType(textBox);
                logger.info("Selected Data Type: " + textBox);

                globalTab.clickObjectTypeCheckbox(objType1);
                logger.info("Selected Object Type: " + objType1);

                globalTab.clickObjectTypeCheckbox(objType2);
                logger.info("Selected Object Type: " + objType2);

                globalTab.clickSaveButton();
                logger.info("Clicked on Save button to create the custom field");

                Thread.sleep(2000);
            } else {
                logger.info("Custom field already exists: " + fieldName);
            }

             WaitUtils.waitFor1000Milliseconds();

            int totalCheckboxes = globalTab.getTotalCheckboxCount();
            int selectedBefore = globalTab.getSelectedCheckboxCount();
            logger.info("Initial State -> Total: " + totalCheckboxes + " | Selected: " + selectedBefore);

            globalTab.clickonSelectAll();
            logger.info("Clicked on Select All button");
             WaitUtils.waitFor1000Milliseconds();

            int selectedAfterSelectAll = globalTab.getSelectedCheckboxCount();
            logger.info("After Select All -> Total: " + totalCheckboxes + " | Selected: " + selectedAfterSelectAll);

            Assert.assertEquals(selectedAfterSelectAll, totalCheckboxes,
                    "Not all fields are selected after clicking Select All");

            logger.info(" All fields are selected successfully after Select All");

            globalTab.clickonClearAll();
            logger.info("Clicked on Clear All button");
            Thread.sleep(750);

            int selectedAfterClearAll = globalTab.getSelectedCheckboxCount();
            logger.info("After Clear All -> Total: " + totalCheckboxes + " | Selected: " + selectedAfterClearAll);

            Assert.assertEquals(selectedAfterClearAll, 0,
                    "Some fields are still selected after clicking Clear All");

            logger.info(" All fields are cleared successfully after Clear All");

        } catch (AssertionError e) {
            logger.error(" Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(" Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ Test Case TC031 Finished *************************");
    }
}
