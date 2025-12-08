package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC013 extends BaseClass {
    @Test(dataProvider = "tc013", dataProviderClass = DefectTabTestCaseDataProvider.class)
    public void verifyErrorMessage(String affectedRelease, String reason, String severity) throws InterruptedException {

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

            createDefectPage.selectAffectedRelease(affectedRelease);
            logger.info("Status Selected from Dropdown");
            Thread.sleep(3000);
            createDefectPage.selectReason(reason);
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
