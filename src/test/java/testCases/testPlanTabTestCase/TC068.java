package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC068 extends BaseClass {
    @Test(dataProvider = "tc036", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void VerifyDeletedReleaseNotificationIsNotClickable(
            String projectName, String releaseName,  String testCycleName,
            String testDescription ,String objectType
    )throws InterruptedException {
        logger.info("****** Starting TC60 **********");

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
            logger.info("Clicked on the module ");
            testPlanPage.clickNewTestCycle();
            logger.info("Clicked on the new testCycle");
            IndividualTestCyclePage individualTestCyclePage = new IndividualTestCyclePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            individualTestCyclePage.setTestCycleName(testCycleName);
            logger.info("added the test cycle name");
            individualTestCyclePage.setDescription(testDescription);
            logger.info("added the description for cycle");
            Assert.assertEquals(individualTestCyclePage.getTargetRelease(), releaseName);
            logger.info("verified the targeted release ");
            WaitUtils.waitFor2000Milliseconds();
            individualTestCyclePage.clickSave();
            logger.info("Clicked on the save button");
            AddRequirementPage addRequirementPage=new AddRequirementPage(getDriver());
            String tcId = addRequirementPage.getModuleId();
            tcId = tcId.replaceAll("\\*$", "");
            tcId = tcId.replace("'", "");
            logger.info("new rl"+ tcId);
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.clickDelete();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(testCycleName);
            WaitUtils.waitFor3000Milliseconds();
            NotificationsListener notificationsListener=new NotificationsListener(getDriver());
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor3000Milliseconds();
            notificationsListener.verifyNotificationHoverText(tcId,"This item no longer exists");
            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectObjectDropdownValue(objectType);
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.smoothScrollRecycleBin();
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.selectRadioById(tcId);
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.clickRestoreButton();
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.clickCloseButtonOfRecycleBinPage();
            WaitUtils.waitFor3000Milliseconds();
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor3000Milliseconds();
            notificationsListener.clickCreatedNotification(tcId);
            WaitUtils.waitFor2000Milliseconds();

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
