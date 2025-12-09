package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC017 extends BaseClass {

    @Test(dataProvider = "tc017", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_success_message_content_after_save(
            String expectedUrlAfterClick,
            String Summary,
            String Type,
            String Category,
            String Priority,
            String Status,
            String AssignTo,
            String Description) throws InterruptedException {

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
            Thread.sleep(3000);

            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Defect Button");
            Thread.sleep(3000);

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());

            createDefectPage.enterSummary(Summary);
            Thread.sleep(3000);
            createDefectPage.selectTypeByIndex(Integer.parseInt(Type));
            logger.info("Type selected:" + Type);
            Thread.sleep(3000);

            createDefectPage.selectCategoryByIndex(Integer.parseInt(Category));
            logger.info("Category selected:" + Category);
            Thread.sleep(3000);

            createDefectPage.selectPriorityByIndex(Integer.parseInt(Priority));
            logger.info("Priority selected:" + Priority);
            Thread.sleep(3000);

            createDefectPage.selectStatusByIndex(Integer.parseInt(Status));
            logger.info("Status selected:" + Status);
            Thread.sleep(3000);

            createDefectPage.selectAssignToByIndex(Integer.parseInt(AssignTo));
            logger.info("Assign To selected:" + AssignTo);
            Thread.sleep(3000);
            createDefectPage.enterDescription(Description);

            createDefectPage.clickSave();

            createDefectPage.verifySuccessNotification();

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

    }
}