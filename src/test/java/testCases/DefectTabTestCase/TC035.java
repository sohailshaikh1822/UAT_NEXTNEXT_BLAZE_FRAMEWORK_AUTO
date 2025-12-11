package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

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
            WaitUtils.waitFor2000Milliseconds();;

            logger.info("Defect Page loaded and form fields are visible.");


            defectLandingPage.selectAffectedReleaseByIndex(Integer.parseInt(affectedRelease));
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.selectStatusByIndex(Integer.parseInt(status));
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.selectSeverityByIndex(Integer.parseInt(severity));
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.selectPriorityByIndex(Integer.parseInt(priority));
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.selectAssignToByIndex(Integer.parseInt(assigned_To));
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.clickSearchButton();
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.clickClearButton();
            WaitUtils.waitFor2000Milliseconds();;

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