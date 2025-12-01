package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import DataProviders.TestPlanDataProvider;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC018 extends BaseClass {

    @Test(dataProvider = "tc018", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNavigationToTestPlanBySelectingProject(String projectName) throws InterruptedException {
        logger.info(
                "****** Starting Test Case: Verify navigation to Test Plan by selecting a project *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            String displayedProjectName = testPlanPage.getLeftPanelProjectName();
            Assert.assertEquals(displayedProjectName, projectName,
                    "Selected project is not displayed correctly in left panel!");
            logger.info("Verified project is displayed in left panel: " + displayedProjectName);

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info(
                "************ Test Case Finished: Verify navigation to Test Plan by selecting a project *****************");
    }
}
