package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC035 extends BaseClass {
    @Test(dataProvider = "tc035", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifySearchADefectAndClearTheFields(
            String affectedRelease,
            String status,
            String severity, String priority, String assigned_To) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            Thread.sleep(3000);

            logger.info("Defect Page loaded and form fields are visible.");


            defectLandingPage.selectAffectedRelease(affectedRelease);
            logger.info("Selected Affected Release: " + affectedRelease);
            Thread.sleep(3000);

            defectLandingPage.selectStatus(status);
            logger.info("Selected Status: " + status);
            Thread.sleep(3000);

            defectLandingPage.selectSeverity(severity);
            logger.info("Selected Severity: " + severity);
            Thread.sleep(3000);

            defectLandingPage.selectPriority(priority);
            logger.info("Selected Priority: " + priority);
            Thread.sleep(3000);

            defectLandingPage.selectAssignedTo(assigned_To);
            logger.info("Selected Assigned To: " + assigned_To);
            Thread.sleep(3000);

            defectLandingPage.clickSearchButton();
            logger.info("Clicked on Search Button");
            Thread.sleep(3000);

            defectLandingPage.clickClearButton();
            logger.info("Clicked on Clear Button to reset filters");
            Thread.sleep(3000);


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