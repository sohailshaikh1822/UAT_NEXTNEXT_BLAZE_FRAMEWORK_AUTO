package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC050 extends BaseClass {

    @Test(dataProvider = "tc050", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_Execution_History_tab_is_updated_after_saving_new_test_log(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR,
            String status,
            String stepno,
            String actual_result
    ) throws InterruptedException {

        logger.info("****** Starting Test Case 050: Verify that Execution History tab is updated after saving new test log *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded Project: " + projectName);

            executeLandingPage.expandRelease(ReleaseName);
            logger.info("Expanded Release: " + ReleaseName);

            executeLandingPage.expandSubTestCycle(CycleName);
            logger.info("Expanded Cycle: " + CycleName);

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.clickTabExecutionHistory();
            WaitUtils.waitFor1000Milliseconds();
            int initialCount = individualTestrun.getExecutionHistoryCount();
            logger.info("Initial Execution History Count: " + initialCount);

            individualTestrun.clickTabTestLogs();
            logger.info("Clicked on Test Log tab");

            individualTestrun.clickCreateTestLog();
            logger.info("Clicked on Create Test Log Button");

            WaitUtils.waitFor1000Milliseconds();
            individualTestrun.selectStatus(status);
            logger.info("Status changed to: " + status);

            individualTestrun.EnterActualResultOfTheStep(Integer.parseInt(stepno), actual_result);
            logger.info("Entered actual result: " + actual_result + " in step: " + stepno);

            WaitUtils.waitFor1000Milliseconds();
            individualTestrun.clickSaveButton();
            logger.info("Clicked on Save Button");

            WaitUtils.waitFor1000Milliseconds();
            boolean notificationDisplayed = individualTestrun.isTestLogCreatedDisplayed();
            Assert.assertTrue(notificationDisplayed, "Test log creation notification was not displayed!");
            logger.info("Verified: 'Test log created successfully' notification displayed");

            individualTestrun.clickTabExecutionHistory();
            logger.info("Clicked on Execution History tab again");

            int updatedCount = 0;
            boolean countIncreased = false;

            for (int i = 0; i < 5; i++) {
                WaitUtils.waitFor2000Milliseconds();
                updatedCount = individualTestrun.getExecutionHistoryCount();
                if (updatedCount > initialCount) {
                    countIncreased = true;
                    break;
                }
            }

            logger.info("Final Updated Execution History Count: " + updatedCount);
            Assert.assertTrue(countIncreased,
                    "Execution history count did not increase! Before: " + initialCount + ", After: " + updatedCount);

            logger.info("Verified: Execution History tab updated successfully after saving new test log.");

            individualTestrun.clickCloseButton();
            logger.info("Closed the Test Log window");

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
