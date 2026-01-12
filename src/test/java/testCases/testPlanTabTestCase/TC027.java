package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC027 extends BaseClass {
    @Test(dataProvider = "tc027", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyingTheNotificationMessageAfterDeletingAReleaseInTheTestPlanModule(
            String projectName,
            String releaseName
    )throws InterruptedException
    {
        logger.info("****** Starting TC27 **********");

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
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickDelete();
            logger.info("Release is deleted");

            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(releaseName);

            WaitUtils.waitFor1000Milliseconds();

            individualReleasePage.verifyReleaseDeletedNotification(releaseId);
            logger.info("Release successfully deleted");

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
