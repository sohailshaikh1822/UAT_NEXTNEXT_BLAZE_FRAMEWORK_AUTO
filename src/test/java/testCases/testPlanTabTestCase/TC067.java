package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC067 extends BaseClass {
    @Test(dataProvider = "tc067", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyBothCreationAndDeletionNotificationsAreDisabledAfterTestCycleDeletion(
            String projectName,
            String releaseName,
            String cycleName
    )throws InterruptedException
    {
        logger.info("****** Starting TC67 **********");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualTestCyclePage individualTestCyclePage= new IndividualTestCyclePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the existing release: " + releaseName);

            testPlanPage.clickNewTestCycle();
            logger.info("Clicked on Add test cycle");

            individualTestCyclePage.setTestCycleName(cycleName);
            logger.info("enter cycle name");

            WaitUtils.waitFor2000Milliseconds();

            individualTestCyclePage.clickSave();
            logger.info("Clicked Save button");

            WaitUtils.waitFor9000Milliseconds();

            String testCycleId= individualTestCyclePage.getCycleId();
            logger.info("Suite ID captured: " + testCycleId);
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickDelete();
            logger.info("Test Cycle is deleted");

            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(cycleName);

            WaitUtils.waitFor1000Milliseconds();

            individualTestCyclePage.verifyCreationAndDeletionTestCycleNotificationsAreDisabled(testCycleId);
            logger.info("Test Cycle successfully Deleted");

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
