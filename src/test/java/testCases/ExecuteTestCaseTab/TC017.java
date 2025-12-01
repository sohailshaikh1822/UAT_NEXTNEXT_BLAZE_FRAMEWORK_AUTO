package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC017 extends BaseClass {

    @Test(dataProvider = "tc007", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
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

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");

            executeLandingPage.clickOnProject();
            logger.info("Clicked on project Name ....");

            executeLandingPage.clickArrowRightPointingForExpandModule(release);
            logger.info("Expanded the release {}", release);

            executeLandingPage.clickArrowRightToExpandModule(cycle);
            logger.info("Expanded the cycle : {}", cycle);

            executeLandingPage.clickOnSuite(suite);
            logger.info("clicked on the suite : {}", suite);

            executeLandingPage.searchTestCase(testRunId);
            logger.info("Entered the test run Id : {}", testRunId);

//            executeLandingPage.clickTestRunById(testRunId);
            executeLandingPage.clickPlayActionById(testRunId);
            logger.info("clicked on test run Id {}", testRunId);

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            individualTestRun.clickLinkDefect();
            logger.info("Clicked on link defect ");

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());
            Assert.assertTrue(linkDefectPage.isSearchButtonVisible());
            logger.info("Verified Successfully");

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
