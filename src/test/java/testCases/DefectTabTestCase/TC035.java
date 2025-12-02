package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;

public class TC035 extends BaseClass {
    @Test(dataProvider = "tc035", dataProviderClass = DefectTabTestCaseDataProvider.class)
    public void VerifySavingWithOnlyMandatoryFieldsFilled(
            String affectedRelease,
            String status,
            String severity
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            Thread.sleep(3000);

            logger.info("Defect Page loaded and form fields are visible.");
            defectLandingPage.clickCreateTestCaseButton();

            logger.info("clicked on Create Defect Button");

            defectLandingPage.selectAffectedRelease(affectedRelease);
            defectLandingPage.selectStatus(status);
            defectLandingPage.selectSeverity(severity);
            defectLandingPage.clickSearchButton();
            defectLandingPage.clickClearButton();

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
