package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC035 extends BaseClass {
    @Test(dataProvider = "tc035", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifySavingWithOnlyMandatoryFieldsFilled(
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
            Thread.sleep(3000);
            defectLandingPage.selectStatus(status);
            Thread.sleep(3000);
            defectLandingPage.selectSeverity(severity);
            Thread.sleep(3000);
            defectLandingPage.selectPriority(priority);
            Thread.sleep(3000);
            defectLandingPage.selectAssignedTo(assigned_To);
            Thread.sleep(3000);
            defectLandingPage.clickSearchButton();
            Thread.sleep(3000);
            defectLandingPage.clickClearButton();
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