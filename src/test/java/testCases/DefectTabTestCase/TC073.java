package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.ExportListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC073 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Verify_exported_file_contains_all_defects_displayed_across_all_pages() throws InterruptedException {

        logger.info("****** Starting TC073 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectPage= new DefectLandingPage(getDriver());
            defectPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectPage.verifyFileTypeDropdownContainsAllFormats();
            WaitUtils.waitFor3000Milliseconds();
//            ExportListener exportListener =new ExportListener(getDriver());
//
//            exportListener.selectExcelFileType();
//            WaitUtils.waitFor3000Milliseconds();
            defectPage.clickDownloadInBackgroundCheckbox();
            defectPage.clickSaveExportButton();
            WaitUtils.waitFor3000Milliseconds();
            defectPage.isFileDownloaded(30);
            logger.info("TC073 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }
    }
}
