package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC008 extends BaseClass {

    @Test(dataProvider = "tc008", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDropdownSelectionAndDisplayOfChosenValue(String expectedUrlAfterClick,String defectID,String affectedrealese, String severity,String fixedRelease,String type,String module,String reason,String category,String targetRealse,String status,
                                                               String priority , String assignTo) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            // Step 1: Login
            login();
            logger.info("Logged in successfully and dashboard loaded");

            // Step 2: Navigate to Defect Tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            // Step 3: Assert current URL contains expected Defect page URL
            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
                    "User did not navigate to the expected Defect Page URL.");

            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);
            defectLandingPage.defectDetailsById(defectID);
            logger.info("Successfully navigated to Defect details page");
            Thread.sleep(3000);
            createDefectPage.selectAffectedRelease(affectedrealese);
            logger.info("Affected Release selected: {}", affectedrealese);

            createDefectPage.selectSeverity(severity);
            logger.info("Severity selected: {}", severity);

            createDefectPage.selectFixedRelease(fixedRelease);
            logger.info("Fixed Release selected: {}", fixedRelease);

            createDefectPage.selectType(type);
            logger.info("Type selected: {}", type);

            createDefectPage.selectModule(module);
            logger.info("Module selected: {}", module);

            createDefectPage.selectReason(reason);
            logger.info("Reason selected: {}", reason);

            createDefectPage.selectCategory(category);
            logger.info("Category selected: {}", category);

            createDefectPage.selectAssignTo(assignTo);
            logger.info("Assigned To selected: {}", assignTo);


            createDefectPage.selectTargetRelease(targetRealse);
            logger.info("Target Release selected: {}", targetRealse);

            createDefectPage.selectStatus(status);
            logger.info("Status selected: {}", status);

            createDefectPage.selectPriority(priority);
            logger.info("Priority selected: {}", priority);



        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify Navigation to Defect Page *************");
    }
}
