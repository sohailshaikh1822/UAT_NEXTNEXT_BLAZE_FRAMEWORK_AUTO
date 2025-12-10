package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC019 extends BaseClass {

    @Test(dataProvider = "tc019", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifythesearchfeature(
            String projectName,
            String releaseName,
            String cycleName,
            String suiteName,
            String testRun
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: verify the search feature *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded Project: " + projectName);

            executeLandingPage.expandRelease(releaseName);
            logger.info("Expanded Release: " + releaseName);

            executeLandingPage.expandSubTestCycle(cycleName);
            logger.info("Expanded Cycle: " + cycleName);
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickOnSuite(suiteName);
            logger.info("Clicked on Suite: " + suiteName);

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            executeLandingPage.clickPlayActionById(testRun);
            logger.info("clicked on Action Play button");
            Assert.assertTrue(individualTestrun.isLinkDefectButtonClickable(), "Create Test Run button is not clickable");
            logger.info("Create Test Run button is clickable");

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
