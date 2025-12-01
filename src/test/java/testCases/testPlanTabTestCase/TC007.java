package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC007 extends BaseClass {

    @Test(dataProvider = "tc007", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyCyclesUnderExpandedRelease(String projectName, String releaseName) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify cycles are listed under expanded release *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.expandProjectSTG(projectName);
            logger.info("Expanded the project: " + projectName);

            testPlanPage.expandProjectSTG(releaseName);
            logger.info("Expanded the release: " + releaseName);

            Assert.assertTrue(testPlanPage.areCyclesDisplayedUnderRelease(releaseName),
                    "Cycles are not displayed under the release: " + releaseName);
            logger.info("Verified cycles are displayed under release: " + releaseName);

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info(
                "************ Test Case Finished: Verify cycles are listed under expanded release *****************");
    }
}
