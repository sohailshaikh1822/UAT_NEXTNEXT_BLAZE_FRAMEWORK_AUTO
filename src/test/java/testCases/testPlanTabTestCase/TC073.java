package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC073 extends BaseClass {
    @Test(dataProvider = "tc073", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyBothCreationAndDeletionNotificationsAreDisabledAfterReleaseDeletion(
            String projectName,
            String releaseName
    )throws InterruptedException
    {
        logger.info("****** Starting TC61 **********");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualReleasePage individualReleasePage=new IndividualReleasePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickNewRelease();
            logger.info("Clicked Add Release button, release form appeared");

            testPlanPage.enterReleaseName(releaseName);
            WaitUtils.waitFor2000Milliseconds();

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");
            WaitUtils.waitFor9000Milliseconds();

            String releaseId = individualReleasePage.getReleaseId();
            logger.info("Suite ID captured: " + releaseId);

            WaitUtils.waitFor2000Milliseconds();
            individualReleasePage.clickCreatedReleaseNotification(releaseId);
            logger.info("click on the create  release notification");


            WaitUtils.waitFor2000Milliseconds();

            testPlanPage.clickDelete();
            logger.info("Release is deleted");

            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(releaseName);

            logger.info("Release is deleted successfully");
            WaitUtils.waitFor2000Milliseconds();


            logger.info("************ Test Case Finished Successfully ***********************");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
