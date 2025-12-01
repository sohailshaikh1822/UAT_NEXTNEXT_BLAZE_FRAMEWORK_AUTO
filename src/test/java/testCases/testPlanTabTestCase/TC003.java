package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.TestPlanDataProvider;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.util.List;

public class TC003 extends BaseClass {

    @Test(dataProvider = "tc003", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyListingOfAllConfiguredProjects(String projectName) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Listing of All Configured Projects *****************");
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

            List<String> actualProjects = testPlanPage.getAllProjectNames();
            logger.info("Projects listed in dropdown: " + actualProjects);

            List<String> expectedProjects = testPlanPage.getExpectedProjectsFromSidebar();
            logger.info("Expected Projects fetched dynamically: " + expectedProjects);

            Assert.assertEquals(actualProjects, expectedProjects,
                    "Project list in dropdown does not match expected configuration!");
            logger.info("Verified all configured projects are listed in expected order");

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
