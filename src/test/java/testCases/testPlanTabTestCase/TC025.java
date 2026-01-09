package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC025 extends BaseClass {

    @Test(dataProvider = "tc025", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verifying_the_notification_message_after_updating_an_existing_Cycle_in_the_Test_Plan_module(
            String projectName,
            String releaseName,
            String CycleName,
            String desc
    )
            throws InterruptedException {
        logger.info("****** Starting TC25: Verifying the notification message after updating an existing Cycle in the Test Plan module *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualTestCyclePage individualTestCyclePage = new IndividualTestCyclePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the existing release: " + releaseName);

            testPlanPage.clickNewTestCycle();
            logger.info("Clicked Add Cycle button, Cycle form appeared");


            individualTestCyclePage.setTestCycleName(CycleName);
            logger.info("Set Cycle name:"+CycleName);
            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");

            WaitUtils.waitFor3000Milliseconds();

            individualTestCyclePage.setDescription(desc);
            logger.info("Selected release status:"+ desc);

            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button again after update");

            WaitUtils.waitFor3000Milliseconds();

            String cycleId = individualTestCyclePage.getCycleId();
            logger.info("Release ID captured: " + cycleId);

            WaitUtils.waitFor9000Milliseconds();

            individualTestCyclePage.verifyCycleUpdateNotification(cycleId);
            logger.info("Release update notification successfully Verified");



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
