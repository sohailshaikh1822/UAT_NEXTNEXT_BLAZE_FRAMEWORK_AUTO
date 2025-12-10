package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC049 extends BaseClass {

    @Test(dataProvider = "tc049", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifySelectAllAndClearAllButtonsWorkInTestSuiteTab(String testFieldName)
            throws InterruptedException {
        logger.info("****** Starting TC049: Verify Select All and Clear All buttons in Test Suite tab ******");

        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickGlobalFieldSetting();
            logger.info("Navigated to Global Field Settings section");

            boolean isFieldPresent = globalTab.isCustomFieldPresent(testFieldName);

            if (!isFieldPresent) {
                logger.warn("No Global Fields found. Executing TC002 steps to create a Global Custom Field.");
                globalTab.clickonAddGlobalField();
                globalTab.EnterFieldName(testFieldName);
                globalTab.SelectDataType("Text Box");
                globalTab.clickObjectTypeCheckbox("Test Case");
                globalTab.clickObjectTypeCheckbox("Requirement");
                globalTab.clickSaveButton();
                WaitUtils.waitFor1000Milliseconds();
                Assert.assertTrue(globalTab.isCustomFieldPresent(testFieldName),
                        "Global Field creation failed — field not found after save.");
                logger.info("Global Field created successfully: " + testFieldName);
            } else {
                logger.info("Global Field is already present: " + testFieldName);
            }

            otherTab.clickOnSelectAll();
            logger.info("Clicked on Select All button.");

            int totalCheckboxes = globalTab.getTotalCheckboxCount();
            int selectedCheckboxes = globalTab.getSelectedCheckboxCount();

            logger.info("Total Checkboxes: " + totalCheckboxes + ", Selected after Select All: " + selectedCheckboxes);
            Assert.assertEquals(selectedCheckboxes, totalCheckboxes,
                    "Not all checkboxes were selected after clicking Select All.");

            otherTab.clickOnClearAll();
            logger.info("Clicked on Clear All button.");

            int selectedAfterClear = globalTab.getSelectedCheckboxCount();
            logger.info("Selected checkboxes after Clear All: " + selectedAfterClear);
            Assert.assertEquals(selectedAfterClear, 0, "Some checkboxes remained selected after Clear All.");

            logger.info("****** TC049 PASSED: Select All and Clear All functionality works correctly ******");
        } catch (AssertionError e) {
            logger.error("Assertion failed in TC049: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred in TC049: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC049 Finished *************************");
    }
}
