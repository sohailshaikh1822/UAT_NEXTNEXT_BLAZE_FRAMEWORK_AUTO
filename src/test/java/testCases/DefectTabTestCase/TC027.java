package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;


public class TC027 extends BaseClass {
    @Test(dataProvider = "tc027", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyDataCorrectnessForSummaryAndDescription(String project, String summary, String status, String description) throws InterruptedException {
        try{
            logger.info("****** Starting Test Case: Verify field-level data correctness for Summary and Description ********");
            //Step 1: Login
            login();
            logger.info("Login Completed");
            WaitUtils.waitFor1000Milliseconds();;

            //Step 2: Click on Defect Tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("defect tab clicked");
            WaitUtils.waitFor1000Milliseconds();;

            //Step 3: Select Project
            defectLandingPage.selectProject(project);
            logger.info("project selected");

            //Step 4: Click Search Button
            defectLandingPage.clickSearchButton();
            logger.info("search button clicked");
            WaitUtils.waitFor2000Milliseconds();;

            //Step 5: click on create new defect
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Create New Defect button clicked");
            WaitUtils.waitFor1000Milliseconds();;

            //Step 6: Enter Summary
            CreateDefectPage defect = new CreateDefectPage(getDriver());
            defect.enterSummary(summary);
            logger.info("Summary Entered");

            //Step 7: Select Status
            WaitUtils.waitFor2000Milliseconds();
//            defect.selectStatus(status);
            defect.selectStatusByIndex(1);
            logger.info("Status Selected");

            //Step 8: Select Description
            WaitUtils.waitFor2000Milliseconds();
            defect.enterDescription(description);
            logger.info("Description entered");

            //Step 9: Scroll up
            WaitUtils.waitFor2000Milliseconds();
            defect.scrollUp();
            logger.info("Create Defect page scrolled up");
            WaitUtils.waitFor2000Milliseconds();

            //Step 10: Click Save button
            defect.clickSave();
            logger.info("Save Button Clicked");

            //Step 11: Assert that defect Saved or not
            Assert.assertTrue(defect.verifySuccessMessage("Defect created successfully."), "Failed to create the defect.");
            logger.info("New Defect Created");
            WaitUtils.waitFor2000Milliseconds();

            //Step 12: Open the created defect with help of summary
            defectLandingPage.enterSummary(summary);
            logger.info("Summary Entered to search the created defect");
            WaitUtils.waitFor2000Milliseconds();

            //Step 13: Click Search button
            defectLandingPage.clickSearchButton();
            logger.info("Search button clicked");
            WaitUtils.waitFor1000Milliseconds();;

            //Step 14: Select the first defect
//            defectLandingPage.clickDefectByIndex(1);
//            defectLandingPage.ClickDefectbyID("DF-448");
//            WaitUtils.waitFor1000Milliseconds();;

            //Step 15: Get the summary and compare with expected summary
//            Assert.assertFalse(defect.getSummary().contains("\n"), "Summary contains the new line character");
//            logger.info("Summary field does not accepts new line character");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: {}", ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: {}", ex.getMessage());
            throw ex;
        }

    }
}
