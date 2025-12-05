package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC007 extends BaseClass {
    @Test(dataProvider = "tc007", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDefaultsPlaceholderValueInDropdown(String expectedUrlAfterClick) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            // Step 1: Login
            login();
            logger.info("Logged in successfully and dashboard loaded");

            // Step 2: Navigate to Defect Tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            // Step 3: Assert current URL contains expected Defect page URL
            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
                    "User did not navigate to the expected Defect Page URL.");
            Thread.sleep(3000);

            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Successfully navigated to create Defect page");
            Thread.sleep(3000);

            createDefectPage.clickSeverityDropdown();
            logger.info("Severity dropdown expanded successfully.");

            createDefectPage.clickTypeDropdown();
            logger.info("Type dropdown expanded successfully.");

            createDefectPage.clickModuleDropdown();
            logger.info("Module dropdown expanded successfully.");

            createDefectPage.clickReasonDropdown();
            logger.info("Reason dropdown expanded successfully.");

            createDefectPage.clickCategoryDropdown();
            logger.info("Category dropdown expanded successfully.");

            createDefectPage.clickTargetReleaseDropdown();
            logger.info("Target Release dropdown expanded successfully.");

            createDefectPage.clickStatusDropdown();
            logger.info("Status dropdown expanded successfully.");

            createDefectPage.clickPriorityDropdown();
            logger.info("Priority dropdown expanded successfully.");



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
