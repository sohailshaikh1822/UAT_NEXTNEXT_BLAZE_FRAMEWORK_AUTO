package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC013 extends BaseClass {
    @Test(dataProvider = "tc013", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNavigationToDefectPage(String affectedRelease, String module, String severity) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            // Step 1: Login
            login();
            logger.info("Logged in successfully and dashboard loaded");

            // Step 2: Navigate to Defect Tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());

            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());

            Thread.sleep(3000);

            createDefectPage.selectAffectedRelease(affectedRelease);
            logger.info("Status Selected from Dropdown");
            Thread.sleep(3000);
            createDefectPage.selectModule(module);
            logger.info("Filled the Summary field");

            Thread.sleep(3000);
            createDefectPage.selectSeverity(severity);
            logger.info("Filled the Summary field");

            createDefectPage.clickSave();
            logger.info("Clicked the save button");

            Assert.assertTrue(createDefectPage.isSummaryBlankErrorDisplayed(),
                    "Error message for blank Summary not shown");


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
