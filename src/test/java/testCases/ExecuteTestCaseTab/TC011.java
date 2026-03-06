package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC011 extends BaseClass {

    @Test(dataProvider = "tc011", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyusercanselectatestcasebyclickingontestrunID(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String SearchTR) throws InterruptedException {

        logger.info(
                "****** Starting Test Case: verify user can select a testcase by clicking on test run ID *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickToSelectProject(projectName);
            logger.info("Expanded Project: " + projectName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandRelease(ReleaseName);
            logger.info("Expanded Release: " + ReleaseName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandSubTestCycle(CycleName);
            logger.info("Expanded Cycle: " + CycleName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickTestRunById(SearchTR);
            logger.info("Clicked on Test Run ID: " + SearchTR);
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