package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC034 extends BaseClass {

    @Test(dataProvider = "tc034", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_behavior_when_an_extremely_long_actual_result_is_entered(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR,
            String status,
            String stepno,
            String actual_result,
            String step
    ) throws InterruptedException {

        logger.info("****** Starting Test Case 034: Verify behavior when an extremely long actual result is entered. *****************");

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
            Thread.sleep(3000);
            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            Thread.sleep(3000);
            individualTestrun.selectStatus(status);
            logger.info("Status changed to: " + status);

            individualTestrun.EnterActualResultOfTheStep(Integer.parseInt(stepno), actual_result);
            logger.info("Entered actual result:" + actual_result + "->in:" + stepno);

            String actualFieldValue = individualTestrun.getActualResultOfStep(Integer.parseInt(step));
            logger.info("Fetched actual result value from field: " + actualFieldValue);
            Thread.sleep(3000);
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
