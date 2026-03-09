package testCases.DefectTabTestCase;

import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC070 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyPdfFileIsDownloadedSuccessfullyUsingExportAll() throws InterruptedException {

        logger.info("****** Starting TC070 ******");

        try {
            login();
            logger.info("Logged in successfully");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.clickDefectTab();
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.verifyExportAllButtonVisibleAndClickable();
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.selectPdfFileType();
            defectLandingPage.clickSaveExportButton();
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.isFileDownloaded(30);
            logger.info("TC070 executed successfully");
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
