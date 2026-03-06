package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC009 extends BaseClass {

    @Test(dataProvider = "tc009", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyTestRunButton(
            String projName,
            String releaseName,
            String testRun
    ) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Expand feature of sub test cycle *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickToSelectProject(projName);
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(projName).isDisplayed(),
                    "Parent module not visible after expand");
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Expanded parent module: " + projName);

            executeLandingPage.expandRelease(releaseName);
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Expanded Release module: " + releaseName);

            WaitUtils.waitFor1000Milliseconds();
            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickPlayActionById(testRun);
            logger.info("clicked on Action Play button");
            WaitUtils.waitFor1000Milliseconds();
            individualTestrun.clickCloseButton();
            logger.info("testcase is closed");
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
