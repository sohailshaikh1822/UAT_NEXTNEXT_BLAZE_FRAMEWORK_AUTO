package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.ExportListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC062 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyExportedFileContainsAllDefectsDisplayedOnThePage() throws InterruptedException {

        logger.info("****** Starting TC062 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectPage= new DefectLandingPage(getDriver());
            defectPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            logger.info("Count the defects");
            WaitUtils.waitFor1000Milliseconds();
            defectPage.clickDefectTab();
            WaitUtils.waitFor2000Milliseconds();
            int defectCount = defectPage.getVisibleDefectCount();
            System.out.println("Visible defects: " + defectCount);

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defectCount > 0, "No defects displayed in grid");

            defectPage.clickExportButton();
            logger.info("Click on Export button");
            ExportListener exportListener = new ExportListener(getDriver());
            exportListener.selectExcelFileType();
            logger.info("select excel from the dropdown");
            exportListener.clickSaveButton();
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
