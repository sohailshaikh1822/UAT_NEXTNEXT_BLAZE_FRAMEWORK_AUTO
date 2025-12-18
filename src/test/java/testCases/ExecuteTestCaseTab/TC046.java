package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC046 extends BaseClass {

    @Test(dataProvider = "tc046", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNoChangesHappenedWhileClickCancelDuringTestCaseCreation(String projectname, String releaseName,
            String subTestCycle,
            String subTestsuit, String requirementID, String testCaseID) throws InterruptedException {

        logger.info(
                "****** Starting Test Case: Verify Save/Cancel buttons appear after selecting test case *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage page = new ExecuteLandingPage(getDriver());
            page.clickExecuteTab();
            logger.info("Navigated to Execute Test Case tab");

            page.clickToSelectProject(projectname);
            Assert.assertTrue(page.selectedModuleOrReleaseName(projectname).isDisplayed(), "Project not visible");
            logger.info("Expanded project: " + projectname);

            page.expandRelease(releaseName);
            Assert.assertTrue(page.isReleaseVisible(releaseName), "Release not visible");
            logger.info("Expanded release: " + releaseName);

            page.expandSubTestCycle(subTestCycle);
            Assert.assertTrue(page.isSubTestCycleVisible(subTestCycle), "Sub test cycle not visible");
            logger.info("Expanded sub test cycle: " + subTestCycle);

            page.isSuitVisible(subTestsuit);
            logger.info("Test suite visible: " + subTestsuit);

            page.clickCreateTestRunButton();
            logger.info("Clicked 'Create New Test Run'");

            WaitUtils.waitFor2000Milliseconds();

            page.clickRequirementById(requirementID);
            logger.info("Selected requirement ID: " + requirementID);

            page.selectTestCaseCheckbox(testCaseID);
            logger.info("Selected test case ID: " + testCaseID);
            WaitUtils.waitFor2000Milliseconds();

            page.clickCancelInPopup();
            logger.info("Verified that Save and Cancel buttons appear after selecting a test case.");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}
