package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC026 extends BaseClass {
    @Test(dataProvider = "tc026", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verifying_the_notification_message_after_updating_an_existing_Suite_in_the_Test_Plan_module(
            String projectName,
            String releaseName,
            String CycleName,
            String SuiteName,
            String desc
    )
            throws InterruptedException {
        logger.info("****** Starting TC26: Verifying the notification message after updating an existing Suite in the Test Plan module *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualTestSuitePage individualTestSuitePage = new IndividualTestSuitePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            testPlanPage.expandOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the existing release: " + releaseName);

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(CycleName);
            logger.info("Clicked on the existing Cycle: " + CycleName);

            testPlanPage.clickNewTestSuite();
            logger.info("Clicked Add Test Suite button, Suite form appeared");


            individualTestSuitePage.enterTestSuiteName(SuiteName);
            logger.info("Set Suite name:"+SuiteName);
            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");

            WaitUtils.waitFor3000Milliseconds();

            individualTestSuitePage.enterDescription(desc);
            logger.info("Filled description by user in suite:"+ desc);

            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button again after update");

            WaitUtils.waitFor3000Milliseconds();

            String suiteId = individualTestSuitePage.getTestSuiteId();
            logger.info("Suite ID captured: " + suiteId);

            WaitUtils.waitFor9000Milliseconds();

            individualTestSuitePage.verifySuiteUpdateNotification(suiteId);
            logger.info("Suite update notification successfully Verified");



            logger.info("************ Test Case Finished Successfully *************************");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
