package testCases.ExecuteTestCaseTab;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.ExecuteTestCaseDataProvider;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC025 extends BaseClass {

    @Test(dataProvider = "tc025", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyExpandSubTestCycleAndFilterFeature(String parentModule, String releaseName, String subTestCycle,
                                                         String subTestsuit) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Expand & Filter feature in Test Run page *****************");

        try {

            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor2000Milliseconds();

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickToSelectProject(parentModule);
            WaitUtils.waitFor2000Milliseconds();

            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(parentModule).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + parentModule);

            executeLandingPage.expandRelease(releaseName);
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            executeLandingPage.expandSubTestCycle(subTestCycle);
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(executeLandingPage.isSubTestCycleVisible(subTestCycle), "Sub test cycle not visible");
            logger.info("Expanded Test Cycle: " + subTestCycle);

            executeLandingPage.clickOnTestSuite(subTestsuit);
            logger.info("Clicked on the TestSuite: " + subTestsuit);
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickOnAnyPlayButton();
            logger.info("Clicked on the Play button of a test run");

            executeLandingPage.waitForTestRunInterfaceToLoad();
            WaitUtils.waitFor2000Milliseconds();

            logger.info("Test Run interface loaded successfully");

            individualTestRun.clickCloseButton();
            logger.info("Clicked on Close button to close the Individual Test Run page");

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