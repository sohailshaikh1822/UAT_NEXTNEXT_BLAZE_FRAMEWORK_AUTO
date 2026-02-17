package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC076 extends BaseClass {

    @Test(dataProvider = "tc076", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verifying_the_notification_navigation_after_updating_an_existing_Cycle_in_the_Test_Plan_module(
            String projectName,
            String releaseName,
            String CycleName,
            String desc
    )
            throws InterruptedException {
        logger.info("****** Starting TC076: Verify navigation to Test cycle details page on clicking Test cycle Updated notification *****************");
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
          //  cycleId = "'"+cycleId+"'";
            logger.info("Release ID captured: " + cycleId);
            NotificationsListener notificationsListener=new NotificationsListener(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickUpdatedNotification(cycleId);
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
