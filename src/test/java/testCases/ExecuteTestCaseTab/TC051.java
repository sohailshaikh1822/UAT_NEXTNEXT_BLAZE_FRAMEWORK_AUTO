package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC051 extends BaseClass {

    @Test(dataProvider = "tc018", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyEmptyTestLogSave(String projectName,
            String release,
            String cycle,
            String suite,
            String testRunId,
            String dfId) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");
        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");

            executeLandingPage.clickOnProject();
            logger.info("Clicked on project Name ....");

            executeLandingPage.clickArrowRightPointingForExpandModule(release);
            logger.info("Expanded the release {}", release);

            executeLandingPage.clickArrowRightToExpandModule(cycle);
            logger.info("Expanded the cycle : {}", cycle);

            executeLandingPage.clickOnSuite(suite);
            logger.info("clicked on the suite : {}", suite);

            executeLandingPage.searchTestCase(testRunId);
            logger.info("Entered the test run Id : {}", testRunId);

            executeLandingPage.clickPlayActionById(testRunId);
            logger.info("clicked on test run Id {}", testRunId);

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            individualTestRun.clickCreateTestLog();
            logger.info("Clicked on create ");

            individualTestRun.clickSaveButton();
            logger.info("Saved the test log without editing");

            Assert.assertTrue(individualTestRun.isErrorAfterEmptyTestLogSaveVisible());
            logger.info("error message is visible .....");

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
