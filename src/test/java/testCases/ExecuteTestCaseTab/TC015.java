package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC015 extends BaseClass {

    @Test(dataProvider = "tc015", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verify_upadate_actual_result_feature(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR,
            String status,
            String stepno,
            String actual_result
    ) throws InterruptedException {

        logger.info("****** Starting Test Case 015: verify update actual result feature *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickToSelectProject(projectName);
            logger.info("Expanded Project: " + projectName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandRelease(ReleaseName);
            logger.info("Expanded Release: " + ReleaseName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandSubTestCycle(CycleName);
            logger.info("Expanded Cycle: " + CycleName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);
            WaitUtils.waitFor1000Milliseconds();

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());

            individualTestrun.selectStatus(status);
            logger.info("Status changed to: " + status);
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.EnterActualResultOfTheStep(Integer.parseInt(stepno), actual_result);
            logger.info("Entered actual desc: " + actual_result + " -> in step: " + stepno);
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.clickSaveButton();
            WaitUtils.waitFor1000Milliseconds();

            individualTestrun.clickCloseButton();
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