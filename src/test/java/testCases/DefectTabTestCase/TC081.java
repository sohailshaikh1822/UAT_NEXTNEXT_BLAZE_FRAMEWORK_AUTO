package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.ExportListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC081 extends BaseClass {

    @Test(dataProvider = "tc078",
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

            WaitUtils.waitFor3000Milliseconds();
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.verifyExportButtonVisibleAndClickable();
            WaitUtils.waitFor3000Milliseconds();
            ExportListener exportListener =new ExportListener(getDriver());

            exportListener.selectExcelFileType();
            defectLandingPage.clickSaveExportButton();
            WaitUtils.waitFor1000Milliseconds();
//            defectLandingPage.pressSaveButton();
            WaitUtils.waitFor3000Milliseconds();

            defectLandingPage.verifyExportButtonVisibleAndClickable();
            WaitUtils.waitFor3000Milliseconds();

            exportListener.selectCsvFileType();
            defectLandingPage.clickSaveExportButton();
            WaitUtils.waitFor3000Milliseconds();

            defectLandingPage.verifyExportButtonVisibleAndClickable();
            WaitUtils.waitFor3000Milliseconds();
            exportListener.selectPdfFileType();
            defectLandingPage.clickSaveExportButton();
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