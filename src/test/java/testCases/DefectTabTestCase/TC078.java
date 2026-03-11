package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC078 extends BaseClass {

    @Test( dataProvider = "tc078",
            dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void VerifyFileTypeDropdownContainsAlThreeFormats(String defectId) throws InterruptedException {

        logger.info("****** Starting TC078 ******");

        try {
            login();
            logger.info("Logged in successfully");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.clickDefectTab();
            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.ClickDefectbyID(defectId);
            defectLandingPage.verifyExportButtonVisibleAndClickable();
            WaitUtils.waitFor1000Milliseconds();
            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.verifyFileTypeDropdownContainsAllFormatsforExportAll();
            logger.info("TC078 executed successfully");
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
