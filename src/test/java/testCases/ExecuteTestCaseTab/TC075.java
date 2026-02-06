package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC075 extends BaseClass {

    @Test(
            dataProvider = "tc075",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyNavigationToTestRunDetailsPageOnClickingCreatedNotification(
            String releaseName,
            String cycleName,
            String Epicname,
            String rq,
            String TC
    ) throws InterruptedException {

        logger.info("****** Starting TC061: Verify that a notification appears automatically when a Test Run is created ******");

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
            executeLandingPage.clickTestCycle(cycleName);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickCreateTestRunButton();
            logger.info("Clicked Create Test Run");

            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.selectEpic(Epicname);



            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickRequirementById(rq);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.selectTestCaseCheckbox(TC);

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked Save in Test Run popup");
            WaitUtils.waitFor2000Milliseconds();


            String trIdFromToast = executeLandingPage.verifyTestRunCreation_updationNotificationToast();

            logger.info("Test Run Create ID from toast: " + trIdFromToast);
            WaitUtils.waitFor2000Milliseconds();

            IndividualTestRun individualTestRun=new IndividualTestRun(getDriver());
            individualTestRun.clickCreatedTestRunNotification(trIdFromToast);
            logger.info("Clicked on the Test run updated notification");




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
