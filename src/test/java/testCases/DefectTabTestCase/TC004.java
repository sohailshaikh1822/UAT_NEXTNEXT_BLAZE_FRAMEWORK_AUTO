package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;

import org.testng.annotations.Test;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC004 extends BaseClass {
    @Test(dataProvider = "tc004", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_visibility_of_all_main_fields_on_Defect_page(String project, String summary, String priority,
            String type, String reason, String assignedTo, String status) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify visibility of all main fields on Defect page ********");

        try {
            // Step1: Login
            login();
            logger.info("Logged in successfully and dashboard loaded");

            // Step 2: Navigate to Defect Tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            WaitUtils.waitFor1000Milliseconds();

            // Step 3: Create Defect
            // createDefect(defectLandingPage, summary, priority, type, reason, assignedTo,
            // status);

            // Step 4: Select a project click search button and open defect
            defectLandingPage.selectProject(project);
            logger.info("Project Selected");

            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.selectAssignedTo(assignedTo);
            logger.info("Assigned to Selected");
            defectLandingPage.clickSearchButton();
            logger.info("Search button clicked");

            Thread.sleep(4000);
            // defectLandingPage.clickDefectByIndex(2);
            defectLandingPage.ClickDefectbyID("DF-448");
            logger.info("Create defect selected");

            Thread.sleep(4000);

            // Step 5: Check all necessary fields are displayed or not
            CreateDefectPage defect = new CreateDefectPage(getDriver());

            defect.scrollUp();
            Thread.sleep(3000);
            Assert.assertTrue(defect.buttonSaveIsDisplayed(), "Button Save is not displayed");
            logger.info("Button Save is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.buttonCloseIsDisplayed(), "Button Close is not displayed");
            logger.info("Button Close is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.textAreaSummaryIsDisplayed(), "Text area for summary is not displayed.");
            logger.info("Summary Text area is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownAffectedReleaseIsDisplayed(),
                    "Dropdown Affected Release/Build is not displayed");
            logger.info("Dropdown Affected Release/Build is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownSeverityIsDisplayed(), "Dropdown Severity is not displayed");
            logger.info("Dropdown Severity is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownFixedReleaseIsDisplayed(),
                    "Dropdown Fixed Release/Build is not displayed");
            logger.info("Dropdown Fixed Release/Build is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownTypeIsDisplayed(), "Dropdown Type is not displayed");
            logger.info("Dropdown Type is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownModuleIsDisplayed(), "Dropdown Module is not displayed");
            logger.info("Dropdown Module is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownReasonIsDisplayed(), "Dropdown Reason is not displayed");
            logger.info("Dropdown Reason is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownCategoryIsDisplayed(), "Dropdown Category is not displayed");
            logger.info("Dropdown Category is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownAssignToIsDisplayed(), "Dropdown Assigned To is not displayed");
            logger.info("Dropdown Assigned To is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownTargetReleaseIsDisplayed(),
                    "Dropdown Target Release/Build is not displayed");
            logger.info("Dropdown Target Release/Build is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownStatusIsDisplayed(), "Dropdown Status is not displayed");
            logger.info("Dropdown Status is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.dropdownPriorityIsDisplayed(), "Dropdown Priority is not displayed");
            logger.info("Dropdown Priority is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.textAreaDescriptionIsDisplayed(), "Text area for description is not displayed.");
            logger.info("Description Text area is displayed");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(defect.buttonBrowseFileIsDisplayed(), "Button Browse File is not displayed");
            logger.info("Button Browse File is displayed");

            logger.info("All Necessary Fields checked");

            // Step 6: Delete the created defect

        } catch (AssertionError ae) {
            logger.error("Assertion failed: {}", ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: {}", ex.getMessage());
            throw ex;
        }

    }
}
