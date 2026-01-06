package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC055 extends BaseClass {

    @Test(dataProvider = "tc058", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyTestSuitHighlightingForTRID(String projectName,String releaseName,String tid) throws InterruptedException {
        logger.info("****** Starting Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
//            executeLandingPage.clickToSelectProject(projectName);

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickRelease(releaseName);
            logger.info("Clicked on the desired release from the dropdown");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.searchTestCase(tid);
            logger.info("Searched the testcase");

            executeLandingPage.clickTestRunById(tid);
            logger.info("Clicked on test run id ");

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
