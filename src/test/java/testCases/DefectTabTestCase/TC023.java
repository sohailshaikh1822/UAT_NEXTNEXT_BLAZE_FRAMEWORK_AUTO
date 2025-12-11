package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;


public class TC023 extends BaseClass {

    @Test(dataProvider = "tc023", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void nonManditoryFieldsLeftBlank(String project, String summary, String status, String description) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify non-mandatory fields can be left blank ********");

        try {
            // Step 1: Login
            login();
            logger.info("Logged in successfully and dashboard loaded");
            WaitUtils.waitFor1000Milliseconds();;

            // Step 2: Navigate to Defect tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("defect tab clicked");
            WaitUtils.waitFor1000Milliseconds();;

            // Step 3: Select the project and click on create defect button to open new defect form.
            defectLandingPage.selectProject(project);
            logger.info("project selected");
            WaitUtils.waitFor2000Milliseconds();;

            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Test Case Button");

            // Step 4: Fill the mandatory fields only and create the defect.
            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            createDefectPage.enterSummary(summary);
            logger.info("Summary Entered");

            WaitUtils.waitFor2000Milliseconds();
//            createDefectPage.selectStatus(status);
            createDefectPage.selectStatusByIndex(1);
            logger.info("Defect status selected");

            WaitUtils.waitFor2000Milliseconds();
            createDefectPage.enterDescription(description);
            logger.info("Description Added");

            WaitUtils.waitFor2000Milliseconds();
            createDefectPage.scrollUp();
            WaitUtils.waitFor2000Milliseconds();
            createDefectPage.clickSave();
            logger.info("Save Button Clicked");
            Assert.assertTrue(createDefectPage.verifySuccessMessage("Defect created successfully."), "Failed to create the defect.");
            logger.info("New Defect Created");

            logger.info("***** Defect created successfully without any on-mandatory fields. *******");
        } catch (AssertionError ae) {
            logger.error("Assertion failed: {}", ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: {}", ex.getMessage());
            throw ex;
        }

    }

}
