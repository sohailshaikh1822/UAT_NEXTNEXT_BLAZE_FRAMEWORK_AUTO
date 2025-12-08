package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC012 extends BaseClass {
    @Test(, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNavigationToDefectPage() throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {

            login();
            logger.info("Logged in successfully and dashboard loaded");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());

            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            Thread.sleep(3000);
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());

            Thread.sleep(3000);

            Assert.assertTrue(createDefectPage.isSummaryMandatoryStarVisible(),
                    "Summary mandatory star not visible");

            Assert.assertTrue(createDefectPage.isDescriptionMandatoryStarVisible(),
                    "Description mandatory star not visible");

            Assert.assertTrue(createDefectPage.isStatusMandatoryStarVisible(),
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
