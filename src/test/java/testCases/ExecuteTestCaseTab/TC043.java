package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC043 extends BaseClass {

    @Test(dataProvider = "tc043", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifySearchForNonExistingTestRun(
            String release,
            String cycle,
            String suite,
            String testRunId) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");
        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickOnProject();
            logger.info("Clicked on project Name ....");

            executeLandingPage.expandRelease(release);
            logger.info("Expanded the release {}", release);

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickArrowRightToExpandModule(cycle);
            logger.info("Expanded the cycle : {}", cycle);

            executeLandingPage.clickOnSuite(suite);
            logger.info("clicked on the suite : {}", suite);

            executeLandingPage.searchTestCase(testRunId);
            logger.info("Entered the test run Id : {}", testRunId);

            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(executeLandingPage.isNoMatchingTabVisible());
            logger.info("Verified no matching tab is visible");

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
