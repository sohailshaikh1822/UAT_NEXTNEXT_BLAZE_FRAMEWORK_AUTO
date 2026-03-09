package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC080 extends BaseClass {

    @Test(dataProvider = "tc080",
            dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDownloadInBackgroundCheckboxFunctionality(String defectid) throws InterruptedException {

        logger.info("****** Starting TC080 ******");

        try {
            login();
            logger.info("Logged in successfully");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.clickDefectTab();
            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.ClickDefectbyID(defectid);
            WaitUtils.waitFor1000Milliseconds();
            defectLandingPage.verifyExportButtonVisibleAndClickable();
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.selectExcelFileType();
            defectLandingPage.clickDownloadInBackgroundCheckbox();
            defectLandingPage.clickSaveExportButton();

            logger.info("TC080 executed successfully");
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
