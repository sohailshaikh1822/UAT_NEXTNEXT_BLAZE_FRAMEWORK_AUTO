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
            String Severity,
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
            logger.info("Defect Page loaded and form fields are visible.");
            Thread.sleep(3000);

            defectLandingPage.clickCreateTestCaseButton();
            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(3000);
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);

            createDefectPage.selectSeverity(Severity);
            logger.info("Severity selected:"+Severity);

            createDefectPage.selectType(Type);
            logger.info("Severity selected:"+Type);

            createDefectPage.selectCategory(Category);
            logger.info("Category selected:"+Category);

            createDefectPage.selectPriority(Priority);
            logger.info("Priority selected:"+Priority);

            createDefectPage.selectStatus(Status);
            logger.info("Status selected:"+Status);

            createDefectPage.selectAssignTo(AssignTo);
            logger.info("Assign To selected:"+AssignTo);

            createDefectPage.enterDescription(Description);
            logger.info("Description filled:"+Description);

            createDefectPage.clickSave();
            logger.info("Clicked on save button");

            String actualMsg = createDefectPage.getSuccessNotificationText();
            Assert.assertEquals(actualMsg, "Defect created successfully.",
                    "Success message mismatch!");
            logger.info("Success message verified: " + actualMsg);



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
