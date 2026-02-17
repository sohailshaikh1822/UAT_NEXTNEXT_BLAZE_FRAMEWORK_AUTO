package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC075 extends BaseClass {

    @Test(dataProvider = "tc075", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNavigationToTestCycleDetailsPageOnClickingTestCycleCreatedNotification(
            String projectName,
            String releaseName,
            String testCycleName,
            String testDescription)
            throws InterruptedException {
        logger.info(
                "****** Starting Test Case: Verify navigation to Test cycle details page on clicking Test cycle created notification *****************");
        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            RequirementTabPage requirementTabPage =new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage=new AddRequirementPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            WaitUtils.waitFor2000Milliseconds();

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            WaitUtils.waitFor2000Milliseconds();

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);
            logger.info("Expanded STG Project");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the module ");

            testPlanPage.clickNewTestCycle();
            logger.info("Clicked on the new testCycle");

            IndividualTestCyclePage individualTestCyclePage = new IndividualTestCyclePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            individualTestCyclePage.setTestCycleName(testCycleName);
            logger.info("added the test cycle name");

            individualTestCyclePage.setDescription(testDescription);
            logger.info("added the description for cycle");
            WaitUtils.waitFor3000Milliseconds();
            Assert.assertEquals(individualTestCyclePage.getTargetRelease(), releaseName);
            logger.info("verified the targeted release ");

            individualTestCyclePage.clickSave();
            logger.info("Clicked on the save button");

            String expectedNotificationPopup= requirementTabPage.handleToastNotification();
            String tcId = addRequirementPage.getModuleId();
            tcId = tcId.replaceAll("\\*$", "");
            tcId = tcId.replace("'", "");

            logger.info("new rl"+ tcId);



            NotificationsListener notificationsListener=new NotificationsListener(getDriver());

            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickCreatedNotification(tcId);


        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}
