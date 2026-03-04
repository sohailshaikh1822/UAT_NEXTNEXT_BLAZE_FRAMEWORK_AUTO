package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC055 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyExportDefectsModalOpensOnClickingExportButton() throws InterruptedException {

        logger.info("****** Starting TC055 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectPage= new DefectLandingPage(getDriver());
            defectPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectPage.clickExportButton();

            // Step 2: Verify modal title
            defectPage.verifyExportModalDisplayed();

            // Step 3: Verify File Type dropdown
            defectPage.verifyFileTypeDropdown();

            // Step 4: Verify Cancel and Save buttons
            defectPage.verifyCancelAndSaveButtons();

            logger.info("TC055 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("****** Finished TC050: Defect update notification verification ******");
    }
}
