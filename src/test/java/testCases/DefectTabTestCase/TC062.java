package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC062 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyExportedFileContainsAllDefectsDisplayedOnThePage() throws InterruptedException {

        logger.info("****** Starting TC065 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectPage= new DefectLandingPage(getDriver());
            defectPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            logger.info("Count the defects");
            int defectCount = defectPage.getVisibleDefectCount();
            System.out.println("Visible defects: " + defectCount);

            Assert.assertTrue(defectCount > 0, "No defects displayed in grid");

            defectPage.clickExportButton();
            logger.info("Click on Export button");
            defectPage.selectExcelFileType();
            logger.info("select excel from the dropdown");
            defectPage.clickSaveButton();
            logger.info("Clicked on save button");

            logger.info("TC062 executed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }
    }
}
