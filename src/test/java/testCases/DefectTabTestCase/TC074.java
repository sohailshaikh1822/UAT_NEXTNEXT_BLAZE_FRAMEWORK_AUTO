package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.ExportListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC074 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Verify_Export_Defects_dialog_box_opens_with_Excel_selected_by_default_Export_All() throws InterruptedException {

        logger.info("****** Starting TC074 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectPage= new DefectLandingPage(getDriver());
            defectPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectPage.verifyFileTypeDropdownContainsAllFormats();
            WaitUtils.waitFor3000Milliseconds();
            logger.info("TC074 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }
    }
}
