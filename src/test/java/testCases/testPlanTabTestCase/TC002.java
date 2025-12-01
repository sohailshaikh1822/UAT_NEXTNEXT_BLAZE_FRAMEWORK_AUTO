package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import DataProviders.TestPlanDataProvider;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC002 extends BaseClass {

    @Test(dataProvider = "tc002", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectSelectionFromDropdown(String projectName,
            String releaseName) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");
        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.expandProjectSTG(projectName);
            logger.info("Expanded the project dropdown");

            testPlanPage.selectProjectByName(releaseName);
            logger.info("Selected project: " + releaseName);

            Assert.assertTrue(testPlanPage.isProjectSelected(releaseName),
                    "Project '" + releaseName + "' was not selected successfully!");
            logger.info("Project '" + releaseName + "' is selected and active");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
