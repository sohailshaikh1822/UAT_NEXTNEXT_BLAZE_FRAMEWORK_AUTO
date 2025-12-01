package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC012 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyNavigationToDefectPage() throws InterruptedException {

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

            Assert.assertTrue(createDefectPage.isMandatoryStarVisible("Summary"),
                    "Summary mandatory star not visible");

            Assert.assertTrue(createDefectPage.isMandatoryStarVisible("Description"),
                    "Description mandatory star not visible");

            Assert.assertTrue(createDefectPage.isMandatoryStarVisible("Status"),
                    "Status mandatory star not visible");

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
