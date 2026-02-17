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

public class TC077 extends BaseClass {
    @Test(dataProvider = "tc077", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNavigationToTestSuitDetailsPageOnClickingTestSuiteCreatedNotification(
            String projectName,
            String releaseName,
            String testCycleName,
            String testDescription,
            String suiteName,
            String startDate,
            String endDate,
            String executionType)
            throws InterruptedException {
        logger.info(
                "****** Starting Test Case: Verify navigation to Test suit details page on clicking Test suit created notification ****************");
        try {

            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the module ");
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickNewTestCycle();
            logger.info("Clicked on the new testCycle");

            IndividualTestCyclePage individualTestCyclePage = new IndividualTestCyclePage(getDriver());
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String testCycleNameWithTimestamp = testCycleName + "_" + timestamp;
            individualTestCyclePage.setTestCycleName(testCycleNameWithTimestamp);
            logger.info("added the test cycle name");

            individualTestCyclePage.setDescription(testDescription);
            logger.info("added the description for cycle");

            Assert.assertEquals(individualTestCyclePage.getTargetRelease(), releaseName);
            logger.info("verified the targeted release ");

            individualTestCyclePage.clickSave();
            logger.info("Clicked on the save button");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(testCycleNameWithTimestamp);
            logger.info("navigated to the created cycle");

            testPlanPage.clickNewTestSuite();
            logger.info("Clicked on the new test suite icon ");

            IndividualTestSuitePage individualTestSuitePage = new IndividualTestSuitePage(getDriver());
            RequirementTabPage requirementTabPage =new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage=new AddRequirementPage(getDriver());

            String testSuiteNameWithTimestamp = suiteName + "_" + timestamp;
            individualTestSuitePage.enterTestSuiteName(testSuiteNameWithTimestamp);
            logger.info("Entered the suite name");

            individualTestSuitePage.enterDescription(testDescription);
            logger.info("entered the description");

            individualTestSuitePage.setPlannedStartDate(startDate);
            individualTestSuitePage.setPlannedEndDate(endDate);
            logger.info("Entered the date");

            individualTestSuitePage.selectExecutionType(executionType);
            logger.info("selected the execution type");

            individualTestSuitePage.clickSaveButton();
            logger.info("clicked on save button");
            String tsId = addRequirementPage.getModuleId();
            tsId = tsId.replaceAll("\\*$", "");
            tsId = tsId.replace("'", "");
            logger.info("new rl"+ tsId);

            NotificationsListener notificationsListener=new NotificationsListener(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickCreatedNotification(tsId);

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
