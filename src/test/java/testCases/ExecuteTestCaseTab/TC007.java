package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC007 extends BaseClass {

    @Test(dataProvider = "tc007", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectSelectionFromDropdown(String projectName,
            String release,
            String cycle,
            String suite,
            String testRunId
    ) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");
        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Clicked on the execute test case tab ..");

            executeLandingPage.clickOnProject();
            logger.info("Clicked on project Name ....");
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickArrowRightToExpandModule(release);
            logger.info("Expanded the release {}", release);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickArrowRightToExpandModule(cycle);
            logger.info("Expanded the cycle : {}", cycle);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickOnSuite(suite);
            logger.info("clicked on the suite : {}", suite);
            WaitUtils.waitFor1000Milliseconds();

            String[] beforeFilter = executeLandingPage.getAllTestRunIds();
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.searchTestCase(testRunId);
            logger.info("Entered the test run Id : {}", testRunId);

            WaitUtils.waitFor1000Milliseconds();
            Assert.assertEquals(new ExecuteLandingPage(getDriver()).getTestRunIdCount(), 1);
            logger.info("Verification done after filtering");

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickOnClearButton();
            logger.info("Clicked on clear Button.");

            WaitUtils.waitFor1000Milliseconds();

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
