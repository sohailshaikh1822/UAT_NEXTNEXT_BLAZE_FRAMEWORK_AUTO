package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC048 extends BaseClass {

    @Test(dataProvider = "tc048", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_Create_Test_Log_button_is_working_properly(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR
    ) throws InterruptedException {

        logger.info("****** Starting Test Case 048: Verify that Create Test Log button is working properly *****************");

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
            Thread.sleep(3000);

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            Thread.sleep(3000);

            individualTestrun.clickCreateTestLog();
            logger.info("Create Test Log button is clickable");

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
