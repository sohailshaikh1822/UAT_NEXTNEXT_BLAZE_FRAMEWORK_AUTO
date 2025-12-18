package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;

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

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickToSelectProject(projName);
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(projName).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + projName);

            executeLandingPage.expandRelease(releaseName);
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            executeLandingPage.clickPlayActionById(testRun);
            logger.info("clicked on Action Play button");
            individualTestrun.selectStatus(status);
            logger.info("Selected status 'Passed' from dropdown");
            individualTestrun.clickSaveButton();
            logger.info("Clicked on Save button to update test log");
            boolean isUpdated = individualTestrun.isTestLogUpdatedDisplayed();
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
