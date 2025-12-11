package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

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
            WaitUtils.waitFor2000Milliseconds();;

            logger.info("Defect Page loaded and form fields are visible.");
            defectLandingPage.clickCreateTestCaseButton();

            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            WaitUtils.waitFor2000Milliseconds();;
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);

            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectStatusByIndex(Integer.parseInt(status));
            logger.info("status is selected");
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.enterDescription(description);
            logger.info("Descrption filled:"+description);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.clickSave();
            logger.info("Clicked on save button");
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.enterSummary(summary2);
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.clickSearchButton();
            WaitUtils.waitFor2000Milliseconds();;
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