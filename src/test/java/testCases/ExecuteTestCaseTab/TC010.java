package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC010 extends BaseClass {

    @Test(dataProvider = "tc010", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verify_the_visibilty_of_test_run_details(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR) throws InterruptedException {

        logger.info("****** Starting Test Case 010: verify the visibilty of test run details *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded Project: " + projectName);

            executeLandingPage.expandRelease(ReleaseName);
            logger.info("Expanded Release: " + ReleaseName);

            executeLandingPage.expandSubTestCycle(CycleName);
            logger.info("Expanded Cycle: " + CycleName);

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            boolean allVisible = individualTestrun.areAllElementsVisible();
            if (allVisible) {
                logger.info("All Test Run elements are visible.");
            } else {
                logger.error("Some Test Run elements are NOT visible.");
            }

            Assert.assertTrue(allVisible, "Not all Test Run elements are visible.");

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
