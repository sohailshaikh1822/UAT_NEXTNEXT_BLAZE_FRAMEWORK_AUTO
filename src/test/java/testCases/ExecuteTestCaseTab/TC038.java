package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;

import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC038 extends BaseClass {

    @Test(dataProvider = "tc038", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verify_filter_by_status_feature(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String status,
            String Exstatus) throws InterruptedException {

        logger.info("****** Starting Test Case 038: verify filter by status feature. *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickToSelectProject(projectName);
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Expanded Project: " + projectName);

            executeLandingPage.expandRelease(ReleaseName);
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Expanded Release: " + ReleaseName);

            executeLandingPage.expandSubTestCycle(CycleName);
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Expanded Cycle: " + CycleName);

            executeLandingPage.clickOnSuite(SuiteName);
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Clicked on Suite: " + SuiteName);

            executeLandingPage.selectStatus(status);
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Selected status filter: " + status);

            for (String actualStatus : executeLandingPage.getAllDisplayedStatuses()) {
                logger.info("Captured status: " + actualStatus);
                Assert.assertEquals(actualStatus, Exstatus,
                        "Mismatch found! Expected status: " + Exstatus + " but got: " + actualStatus);
            }

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