package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC016 extends BaseClass {

    @Test(dataProvider = "tc016", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verify_update_overall_status_feature(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR,
            String status,
            String update
    ) throws InterruptedException {

        logger.info("****** Starting Test Case 016: verify update overall status feature *****************");

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

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);
            WaitUtils.waitFor1000Milliseconds();

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());

            individualTestrun.selectStatus(status);
            logger.info("Status changed to: " + status);
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.clickShowAllStepsRadio();
            logger.info("All steps were selected");
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.selectDropdownStatusBesidesUpdate(update);
            logger.info("Status changed for all steps to: " + update);
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.clickSaveButton();
            logger.info("Clicked on Save button");
            WaitUtils.waitFor1000Milliseconds();

            boolean isUpdated = individualTestrun.isTestLogUpdatedDisplayed();
            WaitUtils.waitFor1000Milliseconds();

            if (isUpdated) {
                logger.info("Notification verified: Test log updated successfully.");
            } else {
                logger.error("Notification not displayed or message mismatch.");
            }

            Assert.assertTrue(isUpdated, "Expected notification 'Test log updated successfully.' not found!");

            individualTestrun.clickCloseButton();
            logger.info("Clicked on Close button");
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