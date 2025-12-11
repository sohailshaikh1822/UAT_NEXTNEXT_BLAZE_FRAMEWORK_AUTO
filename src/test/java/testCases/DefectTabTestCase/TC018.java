package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;
public class TC018 extends BaseClass {

    @Test(dataProvider = "tc018", dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void Verify_success_message_content_after_save(
            String expectedUrlAfterClick,
            String Summary,
            String Type,
            String Category,
            String Priority,
            String Status,
            String AssignTo,
            String Description
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify success message content after save ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertNotNull(actualUrl);
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
                    "User did not navigate to the expected Defect Page URL.");
            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);
            WaitUtils.waitFor2000Milliseconds();;

            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled: " + Summary);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectTypeByIndex(Integer.parseInt(Type));
            logger.info("Type selected: " + Type);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectCategoryByIndex(Integer.parseInt(Category));
            logger.info("Category selected: " + Category);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectPriorityByIndex(Integer.parseInt(Priority));
            logger.info("Priority selected: " + Priority);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectStatusByIndex(Integer.parseInt(Status));
            logger.info("Status selected: " + Status);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectAssignToByIndex(Integer.parseInt(AssignTo));
            logger.info("Assign To selected: " + AssignTo);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.enterDescription(Description);
            logger.info("Description filled: " + Description);
            WaitUtils.waitFor2000Milliseconds();;

            Assert.assertTrue(createDefectPage.isSaveButtonVisible(),
                    "Save button is not visible!");
            logger.info("Save button is visible");

            Assert.assertTrue(createDefectPage.isSaveButtonClickable(),
                    "Save button is not clickable!");
            logger.info("Save button is clickable");

            createDefectPage.clickSaveIfVisibleAndClickable();
            logger.info("Clicked on Save button successfully");

           createDefectPage.verifySuccessNotification();


        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify success message content after save *************");
    }
}
