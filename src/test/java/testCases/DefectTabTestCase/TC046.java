package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC046 extends BaseClass {
    @Test(dataProvider = "tc046", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyThatUserCanSearchCreatedDefectInListingAfterSave(
            String expectedUrlAfterClick,
            String Summary,
            String status,String description

    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            Thread.sleep(6000);
            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertNotNull(actualUrl);
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
                    "User did not navigate to the expected Defect Page URL.");
            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);
            logger.info("Defect Page loaded and form fields are visible.");
            defectLandingPage.clickCreateTestCaseButton();

            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(5000);
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);
            Thread.sleep(2000);

            createDefectPage.selectStatus(status);
            logger.info("status is selected");
            Thread.sleep(2000);

            createDefectPage.enterDescription(description);
            logger.info("Description filled");
            Thread.sleep(2000);

            createDefectPage.clickSave();
            logger.info("Clicked on save button");
            Thread.sleep(2000);

            defectLandingPage.enterSummary(Summary);
            logger.info("Summary filled");
            Thread.sleep(2000);

            defectLandingPage.clickSearchButton();

            logger.info("Clicked on search button");

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
