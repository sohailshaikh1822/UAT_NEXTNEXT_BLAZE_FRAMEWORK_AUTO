package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC014 extends BaseClass {

    @Test(dataProvider = "tc014", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyTestRunButton(
            String projName,
            String releaseName,
            String testRun,
            String status
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Expand feature of sub test cycle *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickToSelectProject(projName);
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(projName).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + projName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandRelease(releaseName);
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);
            WaitUtils.waitFor1000Milliseconds();

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());

            executeLandingPage.clickPlayActionById(testRun);
            logger.info("Clicked on Action Play button");
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.selectStatus(status);
            logger.info("Selected status '" + status + "' from dropdown");
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.clickSaveButton();
            logger.info("Clicked on Save button to update test log");
            WaitUtils.waitFor1000Milliseconds();

            boolean isUpdated = individualTestrun.isTestLogUpdatedDisplayed();
            WaitUtils.waitFor1000Milliseconds();

            if (isUpdated) {
                logger.info("Notification verified: Test log updated successfully.");
            } else {
                logger.error("Notification not displayed or message mismatch.");
            }

            Assert.assertTrue(isUpdated, "Expected notification 'Test log updated successfully.' not found!");

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