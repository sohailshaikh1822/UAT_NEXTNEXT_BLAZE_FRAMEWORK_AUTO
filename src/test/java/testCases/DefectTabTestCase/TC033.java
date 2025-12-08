package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC033 extends BaseClass {
    @Test(dataProvider = "tc033", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifySavingWithOnlyMandatoryFieldsFilled(
            String Summary,
            String status,
            String description,
            String summary2
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            Thread.sleep(3000);

            logger.info("Defect Page loaded and form fields are visible.");
            defectLandingPage.clickCreateTestCaseButton();

            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(3000);
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);

            Thread.sleep(4000);

            createDefectPage.selectStatus(status);
            logger.info("status is selected");
            Thread.sleep(3000);

            createDefectPage.enterDescription(description);
            logger.info("Descrption filled:"+description);
            Thread.sleep(3000);

            createDefectPage.clickSave();
            logger.info("Clicked on save button");
            Thread.sleep(3000);
            defectLandingPage.enterSummary(summary2);
            Thread.sleep(3000);
            defectLandingPage.clickSearchButton();
            Thread.sleep(5000);
        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify Navigation to Defect Page *************");
    }
}