package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.ExportListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC075 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Verify_Save_button_is_clickable_in_Export_Defects_dialog_box() throws InterruptedException {

        logger.info("****** Starting TC075 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectPage= new DefectLandingPage(getDriver());
            defectPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectPage.verifyFileTypeDropdownContainsAllFormats();
            WaitUtils.waitFor3000Milliseconds();
            ExportListener exportListener =new ExportListener(getDriver());
            WaitUtils.waitFor3000Milliseconds();

            exportListener.selectExcelFileType();
            WaitUtils.waitFor3000Milliseconds();
            defectPage.clickDefectPageSaveExportButton();
            WaitUtils.waitFor3000Milliseconds();
            defectPage.isFileDownloaded(30);
            logger.info("TC075 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }
    }
}
