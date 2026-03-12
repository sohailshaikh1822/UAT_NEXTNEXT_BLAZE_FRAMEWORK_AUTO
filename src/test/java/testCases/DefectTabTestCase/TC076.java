package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC076 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Verify_Export_button_is_visible_and_clickable_in_particular_defect() throws InterruptedException {

        logger.info("****** Starting TC076 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            WaitUtils.waitFor2000Milliseconds();

//            defectLandingPage.ClickDefectbyID("DF-311");
            defectLandingPage.clickDefectByIndex(2);
            WaitUtils.waitFor2000Milliseconds();

            defectLandingPage.verifyExportButtonVisibleAndClickable();

            logger.info("TC076 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("****** Finished TC076: Verify Export button is visible and clickable in particular defect\n" + "\n" + "\n ******");
    }
}
