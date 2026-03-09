package testCases.DefectTabTestCase;

import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC059 extends BaseClass {


    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyPdfFileIsDownloadedSuccessfully() throws InterruptedException {

        logger.info("****** Starting TC059 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.clickDefectTab();

            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.verifyExportButtonVisibleAndClickable();
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.selectPdfFileType();
            defectLandingPage.clickSaveExportButton();
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.isFileDownloaded(30);

            logger.info("TC058 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("****** Finished ******");
    }
}
