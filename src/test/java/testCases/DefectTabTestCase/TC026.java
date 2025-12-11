package testCases.DefectTabTestCase;

import org.testng.Assert;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import org.testng.annotations.Test;
import DataProviders.DefectTabTestCaseDataProvider;
import utils.RetryAnalyzer;
import utils.WaitUtils;


public class TC026 extends BaseClass {
    @Test(dataProvider = "tc026", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyDefaultValuesAreReset(String project, String expectedSummary, String expectedAffectedRelease, String expectedSeverity, String expectedFixedRelease, String expectedType, String expectedModule, String expectedReason, String expectedCategory, String expectedAssignTo, String expectedTargetRelease, String expectedStatus, String expectedPriority, String expectedDescription) throws InterruptedException {

        try {
            logger.info("****** Starting Test Case: Verify default values are reset on new Defect form ********");
            // Step 1: Login
            login();
            logger.info("Logged in successfully and dashboard loaded");
            WaitUtils.waitFor1000Milliseconds();;

            // Step 2: Navigate to Defect tab
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("defect tab clicked");
            WaitUtils.waitFor1000Milliseconds();;

            // Step 3: Select the project and open existing defect and close the defect
            defectLandingPage.selectProject(project);
            logger.info("project selected");

            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.clickSearchButton();
            logger.info("search button clicked");
            WaitUtils.waitFor1000Milliseconds();;
//            defectLandingPage.clickDefectByIndex(1);
//            defectLandingPage.ClickDefectbyID("DF-448");
//            logger.info("Opened the Existing Defect");

//            WaitUtils.waitFor1000Milliseconds();;
            CreateDefectPage defect = new CreateDefectPage(getDriver());
//            defect.scrollUp();
//            logger.info("Scrolled up the defect form");

//            WaitUtils.waitFor2000Milliseconds();;
//            defect.clickClose();
//            logger.info("defect closed");
//            WaitUtils.waitFor2000Milliseconds();;

            // Step 4: Click on create defect button to open new defect form and compare all the values with expected default values
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Test Case Button");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedSummary, defect.getSummary(), "Summary value is not reset to default");
            logger.info("Summary value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedAffectedRelease, defect.getSelectedAffectedRelease(), "Affected Release value is not reset to default");
            logger.info("Affected Release value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedSeverity, defect.getSelectedSeverity(), "Severity value is not reset to default");
            logger.info("Severity value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedFixedRelease, defect.getSelectedFixedRelease(), "Fixed Release value is not reset to default");
            logger.info("Fixed Release value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedType, defect.getSelectedType(), "Type value is not reset to default");
            logger.info("Type value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedModule, defect.getSelectedModule(), "Module value is not reset to default");
            logger.info("Module value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedReason, defect.getSelectedReason(), "Reason value is not reset to default");
            logger.info("Reason value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedCategory, defect.getSelectedCategory(), "Category value is not reset to default");
            logger.info("Category value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedAssignTo, defect.getSelectedAssignTo(), "Assign To value is not reset to default");
            logger.info("Assign To value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedTargetRelease, defect.getSelectedTargetRelease(), "Target Release value is not reset to default");
            logger.info("Target Release value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedStatus, defect.getSelectedStatus(), "Status value is not reset to default");
            logger.info("Status value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedPriority, defect.getSelectedPriority(), "Priority value is not reset to default");
            logger.info("Priority value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertEquals(expectedDescription, defect.getDescription(), "Description value is not reset to default");
            logger.info("Description value is reset to default");

            WaitUtils.waitFor2000Milliseconds();
            defect.scrollUp();
            WaitUtils.waitFor2000Milliseconds();
            defect.clickClose();
            logger.info("new defect form closed");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: {}", ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: {}", ex.getMessage());
            throw ex;
        }
    }
}
