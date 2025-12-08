package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC016 extends BaseClass {
    @Test(dataProvider = "tc016", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_saving_with_all_fields_filled(
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

        logger.info("****** Starting Test Case: Verify saving with all fields filled ********");

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
            Thread.sleep(3000);

            createDefectPage.selectSeverity(Severity);
            logger.info("Severity selected:"+Severity);
            Thread.sleep(3000);

            createDefectPage.selectType(Type);
            logger.info("Severity selected:"+Type);
            Thread.sleep(3000);

            createDefectPage.selectCategory(Category);
            logger.info("Category selected:"+Category);
            Thread.sleep(3000);

            createDefectPage.selectPriority(Priority);
            logger.info("Priority selected:"+Priority);
            Thread.sleep(3000);

            createDefectPage.selectStatus(Status);
            logger.info("Status selected:"+Status);
            Thread.sleep(3000);

            createDefectPage.selectAssignTo(AssignTo);
            logger.info("Assign To selected:"+AssignTo);
            Thread.sleep(3000);

            createDefectPage.enterDescription(Description);
            logger.info("Description filled:"+Description);
            Thread.sleep(3000);

            createDefectPage.clickSave();
            logger.info("Clicked on save button");


        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify saving with all fields filled *************");
    }
}
