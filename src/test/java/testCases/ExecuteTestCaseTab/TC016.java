package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;

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

        logger.info("****** Starting Test Case 015: verify update overall status feature *****************");

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
            Thread.sleep(3000);
            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            Thread.sleep(3000);
            individualTestrun.selectStatus(status);
            logger.info("Status changed to: " + status);

            individualTestrun.clickShowAllStepsRadio();
            logger.info("All steps were selected");

            individualTestrun.selectDropdownStatusBesidesUpdate(update);
            logger.info("status changed for all steps to:" + update);

            individualTestrun.clickSaveButton();
            logger.info("Clicked on Save button");

            boolean isUpdated = individualTestrun.isTestLogUpdatedDisplayed();
            if (isUpdated) {
                logger.info("Notification verified: Test log updated successfully.");
            } else {
                logger.error("Notification not displayed or message mismatch.");
            }

            Assert.assertTrue(isUpdated, "Expected notification 'Test log updated successfully.' not found!");

            individualTestrun.clickCloseButton();
            logger.info("Clicked on Close button");

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
