package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC072 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDownloadInBackgroundCheckboxFunctionalityInExportAll() throws InterruptedException {

        logger.info("****** Starting TC072 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectPage= new DefectLandingPage(getDriver());
            defectPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectPage.verifyExportAllButtonVisibleAndClickable();

            WaitUtils.waitFor1000Milliseconds();
            defectPage.verifyExportModalDisplayed();

            WaitUtils.waitFor1000Milliseconds();
            defectPage.verifyFileTypeDropdown();
            WaitUtils.waitFor1000Milliseconds();
            defectPage.verifyCancelAndSaveButtons();

            logger.info("TC072 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }
    }
}
