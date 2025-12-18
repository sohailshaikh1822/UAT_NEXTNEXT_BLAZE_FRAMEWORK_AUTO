package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC013 extends BaseClass {
    @Test(dataProvider = "tc013", dataProviderClass = DefectTabTestCaseDataProvider.class)
    public void verifyNavigationToDefectPage(String affectedRelease, String assignTo, String severity) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Navigation to Defect Page ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());

            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());

            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectAffectedReleaseByIndex(Integer.parseInt(affectedRelease));
            logger.info("Status Selected from Dropdown");
            WaitUtils.waitFor2000Milliseconds();;
            createDefectPage.selectAssignToByIndex(Integer.parseInt(assignTo));
            logger.info("Filled the Summary field");

            WaitUtils.waitFor2000Milliseconds();;
            createDefectPage.selectSeverityByIndex(Integer.parseInt(severity));
            logger.info("Filled the Summary field");
            WaitUtils.waitFor2000Milliseconds();;
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
