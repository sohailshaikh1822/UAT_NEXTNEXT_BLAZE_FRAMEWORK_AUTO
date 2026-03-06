package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC067 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyFileTypeDropdownContainsAllThreeFormatsinExportAll() throws InterruptedException {

        logger.info("****** Starting TC067 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectPage= new DefectLandingPage(getDriver());
            defectPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectPage.verifyFileTypeDropdownContainsAllFormatsforExportAll();

            logger.info("TC067 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }
    }
}
