package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

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
//            String actualUrl = getDriver().getCurrentUrl();
//            Assert.assertNotNull(actualUrl);
//            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
//                    "User did not navigate to the expected Defect Page URL.");
//            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);
//            logger.info("Defect Page loaded and form fields are visible.");
            defectLandingPage.clickCreateTestCaseButton();

            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(3000);
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);
            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.selectStatusByIndex(1);
            logger.info("status is selected");
            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.enterDescription(description);
            logger.info("Description filled");
            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.clickSave();
            logger.info("Clicked on save button");
            WaitUtils.waitFor2000Milliseconds();

            defectLandingPage.enterSummary(Summary);
            logger.info("Summary filled");
            WaitUtils.waitFor2000Milliseconds();

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
