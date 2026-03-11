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

public class TC042 extends BaseClass {

    @Test(dataProvider = "tc042", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyFileAbove5MBInDefect(String projectName,
            String release,
            String cycle,
            String suite,
            String testRunId,
            String fileAddress
    ) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");
        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickOnProject();
            logger.info("Clicked on project Name ....");

            WaitUtils.waitFor3000Milliseconds();
            executeLandingPage.clickArrowRightToExpandModule(release);
            logger.info("Expanded the release {}", release);

            WaitUtils.waitFor3000Milliseconds();
            executeLandingPage.clickArrowRightToExpandModule(cycle);
            logger.info("Expanded the cycle : {}", cycle);

            executeLandingPage.clickOnSuite(suite);
            logger.info("clicked on the suite : {}", suite);

            executeLandingPage.searchTestCase(testRunId);
            logger.info("Entered the test run Id : {}", testRunId);
            WaitUtils.waitFor1000Milliseconds();
//            executeLandingPage.clickTestRunById(testRunId);
            executeLandingPage.clickPlayActionById(testRunId);
            logger.info("clicked on test run Id {}", testRunId);

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            individualTestRun.clickLinkDefect();
            logger.info("Clicked on link defect ");

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());

            linkDefectPage.clickNew();
            logger.info("Clicked On the New ");
            WaitUtils.waitFor3000Milliseconds();

            linkDefectPage.uploadFile(fileAddress);
            logger.info("more than 5 mb uploaded");

            Assert.assertTrue(linkDefectPage.isErrorMessageVisibleForSizeExceed());
            logger.info("Error message is visible as desired ....");

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
