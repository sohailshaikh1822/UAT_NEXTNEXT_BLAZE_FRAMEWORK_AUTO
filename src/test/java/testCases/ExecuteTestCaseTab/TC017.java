package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC017 extends BaseClass {

    @Test(dataProvider = "tc017", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyFunctionalityOfDefectButton(String projectName,
                                                  String release,
                                                  String cycle,
                                                  String suite,
                                                  String testRunId
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickOnProject();
            logger.info("Clicked on project Name ....");
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandRelease(release);
            logger.info("Expanded the release {}", release);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickArrowRightToExpandModule(cycle);
            logger.info("Expanded the cycle : {}", cycle);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickOnSuite(suite);
            logger.info("clicked on the suite : {}", suite);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.searchTestCase(testRunId);
            logger.info("Entered the test run Id : {}", testRunId);
            WaitUtils.waitFor1000Milliseconds();

//            executeLandingPage.clickTestRunById(testRunId);
            executeLandingPage.clickPlayActionById(testRunId);
            logger.info("clicked on test run Id {}", testRunId);
            WaitUtils.waitFor1000Milliseconds();

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());

            individualTestRun.clickLinkDefect();
            logger.info("Clicked on link defect ");
            WaitUtils.waitFor1000Milliseconds();

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());

            Assert.assertTrue(linkDefectPage.isSearchButtonVisible());
            logger.info("Verified Successfully");
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