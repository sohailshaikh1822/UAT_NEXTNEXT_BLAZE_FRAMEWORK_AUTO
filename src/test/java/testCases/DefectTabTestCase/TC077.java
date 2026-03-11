package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC077 extends BaseClass {

    @Test( dataProvider = "tc077",
            dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void VerifyExportDefectsModalOpensOnClickingExportButton( String defectId) throws InterruptedException {

        logger.info("****** Starting TC077 ******");

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
            defectLandingPage.verifyExportModalDisplayed();
            WaitUtils.waitFor1000Milliseconds();
            defectLandingPage.verifyCancelAndSaveButtons();
            logger.info("TC077 executed successfully");
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
