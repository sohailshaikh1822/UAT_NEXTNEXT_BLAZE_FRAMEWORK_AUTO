package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC063 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyExportDefectsDialogBoxOpensWithExcelSelectedByDefault() throws InterruptedException {

        logger.info("****** Starting TC063 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage= new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.clickExportButton();
            logger.info("Clicked on Export button");
            WaitUtils.waitFor1000Milliseconds();

            defectLandingPage.verifyExcelSelectedByDefault();
            logger.info("Excel is been selected by default");

            logger.info("TC063 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }
    }
}
