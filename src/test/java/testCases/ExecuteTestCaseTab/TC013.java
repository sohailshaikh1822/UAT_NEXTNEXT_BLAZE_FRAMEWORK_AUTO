package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC013 extends BaseClass {

    @Test(dataProvider = "tc013", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verify_upadate_actual_result_feature(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR,
            String status,
            String stepno,
            String actual_result) throws InterruptedException {

        logger.info("****** Starting Test Case 013: verify upadate actual result feature *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded Project: " + projectName);

            executeLandingPage.expandRelease(ReleaseName);
            logger.info("Expanded Release: " + ReleaseName);

            executeLandingPage.expandSubTestCycle(CycleName);
            logger.info("Expanded Cycle: " + CycleName);
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            individualTestrun.selectStatus(status);
            logger.info("Status changed to: " + status);

            individualTestrun.EnterActualResultOfTheStep(Integer.parseInt(stepno), actual_result);
            logger.info("Entered actual desc:" + actual_result + "->in:" + stepno);
            WaitUtils.waitFor1000Milliseconds();
            individualTestrun.clickSaveButton();

            individualTestrun.clickCloseButton();

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
