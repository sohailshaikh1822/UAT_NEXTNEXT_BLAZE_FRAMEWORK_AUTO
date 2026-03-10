package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC081 extends BaseClass {

    @Test(dataProvider = "tc081",
            dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDefectExportInAllSupportedFormats(String defectid) throws Exception {

        logger.info("************** Starting TC081 **************");

        try {

            logger.info(" Logging into the application");
            login();
            logger.info("Login successful");
            WaitUtils.waitFor3000Milliseconds();

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());

            logger.info("Navigating to Defect tab");
            defectLandingPage.clickDefectTab();
            logger.info("Defect tab opened successfully");
            WaitUtils.waitFor3000Milliseconds();
            logger.info(" Opening defect with ID ");
            defectLandingPage.ClickDefectbyID(defectid);
            logger.info("Defect details page opened successfully");
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.verifyExportButtonVisibleAndClickable();
            logger.info("Export modal opened successfully");
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.selectExcelFileType();
            logger.info("Excel format selected successfully");
            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.clickSaveAndConfirmDownload();
            logger.info("Excel export initiated successfully");
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.verifyExportButtonVisibleAndClickable();
            logger.info("Export modal opened again successfully");
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.selectCsvFileType();
            logger.info("CSV format selected successfully");
            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.clickSaveAndConfirmDownload();
            logger.info("CSV export initiated successfully");
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.verifyExportButtonVisibleAndClickable();
            logger.info("Export modal opened again successfully");
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.selectPdfFileType();
            logger.info("PDF format selected successfully");
            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.clickSaveAndConfirmDownload();
            logger.info("PDF export initiated successfully");
            WaitUtils.waitFor3000Milliseconds();

            logger.info("TC081 executed successfully");

        } catch (AssertionError e) {

            logger.error("Assertion failed", e.getMessage());
            throw e;

        } catch (Exception e) {

            logger.error("Unexpected exception", e.getMessage());
            throw e;
        }

        logger.info("************** TC081 Finished Successfully **************");
    }
}