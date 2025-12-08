package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;

import org.testng.annotations.Test;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC004 extends BaseClass {
    @Test(dataProvider = "tc004", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_visibility_of_all_main_fields_on_Defect_page(String project, String summary, String priority, String type, String reason, String assignedTo, String status) throws InterruptedException{
        logger.info("****** Starting Test Case: Verify visibility of all main fields on Defect page ********");

        try {
            // Step1: Login
            login();
            logger.info("Logged in successfully and dashboard loaded");

            // Step 2: Navigate to Defect Tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            Thread.sleep(10000);

            // Step 3: Create Defect
//             createDefect(defectLandingPage, summary, priority, type, reason, assignedTo, status);

            // Step 4: Select a project click search button and open defect
            defectLandingPage.selectProject(project);
            logger.info("Project Selected");

            Thread.sleep(2000);
            defectLandingPage.selectAssignedTo(assignedTo);
            logger.info("Assigned to Selected");
            defectLandingPage.clickSearchButton();
            logger.info("Search button clicked");

            Thread.sleep(4000);
//            defectLandingPage.clickDefectByIndex(2);
            defectLandingPage.ClickDefectbyID("DF-448");
            logger.info("Create defect selected");

            Thread.sleep(4000);




            // Step 5: Check all necessary fields are displayed or not
            CreateDefectPage defect = new CreateDefectPage(getDriver());

            defect.scrollUp();
            Thread.sleep(3000);
            Assert.assertTrue(defect.buttonSaveIsDisplayed(),"Button Save is not displayed");
            logger.info("Button Save is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.buttonCloseIsDisplayed(),"Button Close is not displayed");
            logger.info("Button Close is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.textAreaSummaryIsDisplayed(), "Text area for summary is not displayed.");
            logger.info("Summary Text area is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownAffectedReleaseIsDisplayed(),"Dropdown Affected Release/Build is not displayed");
            logger.info("Dropdown Affected Release/Build is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownSeverityIsDisplayed(), "Dropdown Severity is not displayed");
            logger.info("Dropdown Severity is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownFixedReleaseIsDisplayed(), "Dropdown Fixed Release/Build is not displayed");
            logger.info("Dropdown Fixed Release/Build is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownTypeIsDisplayed(), "Dropdown Type is not displayed");
            logger.info("Dropdown Type is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownModuleIsDisplayed(), "Dropdown Module is not displayed");
            logger.info("Dropdown Module is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownReasonIsDisplayed(), "Dropdown Reason is not displayed");
            logger.info("Dropdown Reason is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownCategoryIsDisplayed(),"Dropdown Category is not displayed");
            logger.info("Dropdown Category is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownAssignToIsDisplayed(), "Dropdown Assigned To is not displayed");
            logger.info("Dropdown Assigned To is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownTargetReleaseIsDisplayed(), "Dropdown Target Release/Build is not displayed");
            logger.info("Dropdown Target Release/Build is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownStatusIsDisplayed(), "Dropdown Status is not displayed");
            logger.info("Dropdown Status is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.dropdownPriorityIsDisplayed(), "Dropdown Priority is not displayed");
            logger.info("Dropdown Priority is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.textAreaDescriptionIsDisplayed(), "Text area for description is not displayed.");
            logger.info("Description Text area is displayed");

            Thread.sleep(2000);
            Assert.assertTrue(defect.buttonBrowseFileIsDisplayed(),"Button Browse File is not displayed");
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

    public void createDefect(DefectLandingPage defectLandingPage, String summary, String priority, String type, String reason, String assignedTo, String status){
        defectLandingPage.clickCreateTestCaseButton();
        logger.info("Clicked on Create Test Case Button");

        CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
        createDefectPage.enterSummary(summary);
        logger.info("Summary Entered");
        createDefectPage.selectPriority(priority);
        logger.info("Priority selected");
        createDefectPage.selectType(type);
        logger.info("Defect type selected ");
        createDefectPage.selectReason(reason);
        logger.info("Reason selected");
        createDefectPage.selectAssignTo(assignedTo);
        logger.info("Assigned to selected");
        createDefectPage.selectStatus(status);
        logger.info("Defect status selected");
        createDefectPage.clickSave();
        logger.info("Save Button Clicked");
        Assert.assertTrue(createDefectPage.verifySuccessMessage("Defect created successfully."), "Failed to create the defect.");
        logger.info("New Defect Created");
    }

    public void open_defect(DefectLandingPage defectLandingPage,  String project , String assignedTo) throws InterruptedException {

//        defectLandingPage.selectProject(project);
//        logger.info("Project Selected");
//        defectLandingPage.selectAssignedTo(assignedTo);
//        logger.info("Assigned to Selected");
//        defectLandingPage.clickSearchButton();
//        logger.info("Search button clicked");
//
//        // sleep thread for 10 sec
//        Thread.sleep(10000);
//
//        defectLandingPage.clickDefectByIndex(1);
    }

    public void checkAllNecessaryFields (CreateDefectPage defect) throws InterruptedException{
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.buttonSaveIsDisplayed(),"Button Save is not displayed");
//        logger.info("Button Save is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.buttonCloseIsDisplayed(),"Button Close is not displayed");
//        logger.info("Button Close is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.textAreaSummaryIsDisplayed(), "Text area for summary is not displayed.");
//        logger.info("Summary Text area is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownAffectedReleaseIsDisplayed(),"Dropdown Affected Release/Build is not displayed");
//        logger.info("Dropdown Affected Release/Build is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownSeverityIsDisplayed(), "Dropdown Severity is not displayed");
//        logger.info("Dropdown Severity is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownFixedReleaseIsDisplayed(), "Dropdown Fixed Release/Build is not displayed");
//        logger.info("Dropdown Fixed Release/Build is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownTypeIsDisplayed(), "Dropdown Type is not displayed");
//        logger.info("Dropdown Type is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownModuleIsDisplayed(), "Dropdown Module is not displayed");
//        logger.info("Dropdown Module is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownReasonIsDisplayed(), "Dropdown Reason is not displayed");
//        logger.info("Dropdown Reason is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownCategoryIsDisplayed(),"Dropdown Category is not displayed");
//        logger.info("Dropdown Category is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownAssignToIsDisplayed(), "Dropdown Assigned To is not displayed");
//        logger.info("Dropdown Assigned To is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownTargetReleaseIsDisplayed(), "Dropdown Target Release/Build is not displayed");
//        logger.info("Dropdown Target Release/Build is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownStatusIsDisplayed(), "Dropdown Status is not displayed");
//        logger.info("Dropdown Status is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.dropdownPriorityIsDisplayed(), "Dropdown Priority is not displayed");
//        logger.info("Dropdown Priority is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.textAreaDescriptionIsDisplayed(), "Text area for description is not displayed.");
//        logger.info("Description Text area is displayed");
//
//        Thread.sleep(2000);
//        Assert.assertTrue(defect.buttonBrowseFileIsDisplayed(),"Button Browse File is not displayed");
//        logger.info("Button Browse File is displayed");
    }


}

