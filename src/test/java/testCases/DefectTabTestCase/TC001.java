package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC001 extends BaseClass {

    @Test(dataProvider = "tc001", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNavigationToDefectPage(String expectedUrlAfterClick) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            // Step 1: Login
            login();
            logger.info("Logged in successfully and dashboard loaded");

            // Step 2: Navigate to Defect Tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());

            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            // Step 3: Assert current URL contains expected Defect page URL
            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
                    "User did not navigate to the expected Defect Page URL.");

            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);

            Assert.assertTrue(defectLandingPage.summary.isDisplayed(),
                    "Defect Page did not load properly – Summary field not visible.");

            logger.info("Defect Page loaded and form fields are visible.");

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
