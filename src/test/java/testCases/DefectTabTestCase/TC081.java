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

        logger.info("****** Starting TC081 ******");

        try {
            login();
            logger.info("Logged in successfully");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.clickDefectTab();
            WaitUtils.waitFor3000Milliseconds();
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
        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("****** Finished  ******");
    }
}
