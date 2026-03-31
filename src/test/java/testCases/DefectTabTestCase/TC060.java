package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.ExportListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC060 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyCancelButtonClosesExportModal() throws InterruptedException {

        logger.info("****** Starting TC060 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.verifyExportButtonVisibleAndClickable();
            WaitUtils.waitFor2000Milliseconds();
            ExportListener exportListener =new ExportListener(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            exportListener.clickCancelButton();
            logger.info("TC060 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("****** Finished TC060: Verify Cancel button closes Export modal ******");
    }
}
