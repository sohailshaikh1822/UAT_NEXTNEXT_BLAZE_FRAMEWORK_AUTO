package testCases.ExecuteTestCaseTab;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.ExecuteTestCaseDataProvider;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC012 extends BaseClass {

    @Test(dataProvider = "tc004", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyExpandSubTestCycleAndFilterFeature(String parentModule, String releaseName, String subTestCycle,
            String subTestsuit) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Expand & Filter feature in Test Run page *****************");

        try {

            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(parentModule);
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(parentModule).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + parentModule);

            executeLandingPage.expandRelease(releaseName);
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            executeLandingPage.expandSubTestCycle(subTestCycle);
            Assert.assertTrue(executeLandingPage.isSubTestCycleVisible(subTestCycle), "Sub test cycle not visible");
            logger.info("Expanded Test Cycle: " + subTestCycle);

            executeLandingPage.clickOnTestSuite(subTestsuit);
            logger.info("Clicked on the TestSuite: " + subTestsuit);

            executeLandingPage.clickOnAnyPlayButton();
            executeLandingPage.waitForTestRunInterfaceToLoad();
            logger.info("Clicked on the Play button of a test run");

            WaitUtils.waitFor1000Milliseconds();
            logger.info("Waited for 1.5 seconds for Test Run page to load");

            individualTestRun.clickShowOnlyBusinessStepsRadio();
            logger.info("Clicked on 'Show Only Business Test Steps' radio button");

            WaitUtils.waitFor1000Milliseconds();

            individualTestRun.clickShowAllStepsRadio();
            logger.info("Clicked on 'Show All Steps' radio button");

            logger.info("Filter feature executed successfully in Individual Test Run page");

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
