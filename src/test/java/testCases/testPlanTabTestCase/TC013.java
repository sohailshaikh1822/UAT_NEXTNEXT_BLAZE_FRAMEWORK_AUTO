package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC013 extends BaseClass {

    @Test(dataProvider = "tc013", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verifycollapsefunctionalityforreleaseandcycle(
            String projectName,
            String releaseName,
            String testcycle) throws InterruptedException {
        logger.info("****** TC013: Verify collapse functionality for release and cycle *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandProjectSTG(projectName);
            logger.info("Expanded project: " + projectName);

            testPlanPage.expandRelease(releaseName);
            logger.info("Expanded release: " + releaseName);

            boolean isCycleVisible = testPlanPage.isTestingCycleVisible(releaseName, testcycle);
            Assert.assertTrue(isCycleVisible, "Testing Cycle is not visible after expanding release!");
            logger.info("Verified Testing Cycle is visible: " + testcycle);

            testPlanPage.expandArrow(testcycle).click();
            logger.info("Expanded test cycle: " + testcycle);

            boolean isSuiteVisible = testPlanPage.isTestingSuiteVisible(testcycle, "Testing suite");
            Assert.assertTrue(isSuiteVisible, "Testing Suite is not visible after expanding cycle!");
            logger.info("Verified Testing Suite is visible under cycle: " + testcycle);

            if (isCycleVisible && isSuiteVisible) {
                logger.info("Release and Cycle are expandable");
                System.out.println("Release and Cycle are expandable");
            }

            logger.info("************ Test Case Finished Successfully *************************");

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
