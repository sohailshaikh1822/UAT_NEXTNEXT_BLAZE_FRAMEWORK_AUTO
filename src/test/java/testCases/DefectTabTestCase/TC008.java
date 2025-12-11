package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC008 extends BaseClass {

    @Test(dataProvider = "tc008", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDropdownSelectionAndDisplayOfChosenValue(String expectedUrlAfterClick,String defectID,String affectedrealese, String severity,String fixedRelease,String type,String module,String reason,String category,String assignTo, String targetRealse,String status,
                                                               String priority) throws InterruptedException {

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
        Thread.sleep(5000);

            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);
            defectLandingPage.ClickDefectbyID(defectID);
            logger.info("Successfully navigated to Defect details page");
            Thread.sleep(3000);
            createDefectPage.selectAffectedRelease(affectedrealese);
            logger.info("Affected Release selected: {}", affectedrealese);
            Thread.sleep(3000);

            createDefectPage.selectSeverityByIndex(1);

            createDefectPage.selectFixedRelease(fixedRelease);

            createDefectPage.selectTypeByIndex(1);
            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.selectModuleByIndex(1);
            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.selectReasonByIndex(1);
            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.selectCategoryByIndex(1);
            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.selectAssignToByIndex(2);

            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.selectTargetReleaseByIndex(1);

            createDefectPage.selectStatusByIndex(1);
            logger.info("Status selected: {}", status);

            createDefectPage.selectPriorityByIndex(1);
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
