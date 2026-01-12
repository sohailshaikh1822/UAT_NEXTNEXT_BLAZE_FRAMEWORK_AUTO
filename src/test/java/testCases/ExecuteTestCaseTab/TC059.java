package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC059 extends BaseClass {

    @Test(
            dataProvider = "tc059",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void Verify_popup_notification_appears_automatically_when_a_Test_Run_is_created(
            String releaseName,
            String cycleName,
            String subcycleName,
            String suiteName,
            String Epicname,
            String Featurename,
            String rq,
            String TC
    ) throws InterruptedException {

        logger.info("****** Starting TC059: Verify Test Run creation notification ******");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on Execute Test Case tab");

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickArrowRightToExpandModule(releaseName);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickArrowRightToExpandModule(cycleName);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickArrowRightToExpandModule(subcycleName);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickOnSuite(suiteName);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickCreateTestRunButton();
            logger.info("Clicked Create Test Run");

            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.selectEpic(Epicname);

            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.selectFeature(Featurename);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickRequirementById(rq);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.selectTestCaseCheckbox(TC);

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked Save in Test Run popup");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.openLatestTestRunFromTable();

            WaitUtils.waitFor9000Milliseconds();

            String trId = executeLandingPage.getOpenedTestRunId();
            logger.info("Latest Test Run opened: " + trId);

            WaitUtils.waitFor9000Milliseconds();

            executeLandingPage.verifyTestRunCreationNotification(trId);



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
