package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC076 extends BaseClass {
    @Test(
            dataProvider = "tc076",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyNavigationToTestRunDetailsPageOnClickingUpdatedNotification(
            String releaseName,
            String cycleName,
            String Epicname,
            String rq,
            String TC,
            String Status,
            String secondStatus
    ) throws InterruptedException {

        logger.info("****** Starting TC062: Verify Toast notification appears automatically when a Test Run is Updated ******");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());

            executeLandingPage.clickExecuteTab();

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickArrowRightToExpandModule(releaseName);

           WaitUtils.waitFor2000Milliseconds();
           executeLandingPage.clickTestCycle(cycleName);
           logger.info("clicked on test cycle");

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickCreateTestRunButton();
            logger.info("create test run");

            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.selectEpic(Epicname);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickRequirementById(rq);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.selectTestCaseCheckbox(TC);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickSaveInPopup();
            logger.info("test run creted");

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.openLatestTestRunFromTable();

            String trId = executeLandingPage.getOpenedTestRunId();
            logger.info("Opened Test Run ID: " + trId);

            executeLandingPage.selectStatus(Status);
            logger.info("Updated Test Run status to: " + Status);

            WaitUtils.waitFor2000Milliseconds();
            individualTestRun.clickSaveButton();
            logger.info("Clicked Save button for Test Run update");
            WaitUtils.waitFor2000Milliseconds();

            String trIdFromToast = executeLandingPage.verifyTestRunCreation_updationNotificationToast();

            logger.info("Test Run update ID from toast: " + trIdFromToast);

            individualTestRun.clickCreatedTestRunNotification(trIdFromToast);
            logger.info("Clicked on the Test run updated notification");


        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ Finished TC062 *************************");
    }
}
