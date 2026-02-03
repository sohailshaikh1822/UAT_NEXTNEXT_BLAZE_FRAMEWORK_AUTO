package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC043 extends BaseClass {
    @Test(dataProvider = "tc043", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void VerifyANotificationIsDisplayedWhenADeletedTestSuitIsRestored(
            String projectName,
            String releaseName,
            String suiteName,
            String objectType
    ) throws InterruptedException {
        logger.info("****** Starting TC043 *****************");
        try {
            login();
            logger.info("Logged in successfully");
            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");
            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");
            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked ReleaseName");
            testPlanPage.clickNewTestSuite();
            logger.info("Clicked on the new test suite icon ");
            IndividualTestSuitePage individualTestSuitePage = new IndividualTestSuitePage(getDriver());

            individualTestSuitePage.enterTestSuiteName(suiteName);
            logger.info("Entered the suite name");

            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.clickSaveButton();
            logger.info("clicked on save button");

            String tsId = individualTestSuitePage.getTestSuiteId();

            logger.info("new rl"+ tsId);
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickDelete();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(suiteName);
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectObjectDropdownValue(objectType);
            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.smoothScrollRecycleBin();
            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.selectRadioById(tsId);
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickRestoreButton();
            testPlanPage.verifyRestoreToastMessage(tsId);
            logger.info("Toast message");
            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.verifyTestSuiteRestoreNotification(tsId);
            logger.info("Success Notification");

        }catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}
