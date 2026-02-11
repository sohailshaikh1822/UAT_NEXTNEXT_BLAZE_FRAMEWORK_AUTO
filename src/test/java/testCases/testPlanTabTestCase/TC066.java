package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC066 extends BaseClass {
    @Test(dataProvider = "tc066", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyBothCreationAndDeletionNotificationsAreDisabledAfterTestSuitDeletion(
            String projectName,
            String releaseName,
            String cycleName,
            String suitName
    )throws InterruptedException
    {
        logger.info("****** Starting TC66 **********");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualTestSuitePage individualTestSuitePage=new IndividualTestSuitePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the existing release: " + releaseName);
            testPlanPage.expandOnReleaseOrTestCycleOrTestSuite(releaseName);

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(cycleName);
            logger.info("Clicked on test cycle");
            testPlanPage.expandOnReleaseOrTestCycleOrTestSuite(cycleName);

            testPlanPage.clickNewTestSuite();
            logger.info("Clicked on Add test suit");

            individualTestSuitePage.enterTestSuiteName(suitName);
            logger.info("TestSuit Name is entered");

            WaitUtils.waitFor2000Milliseconds();

            individualTestSuitePage.clickSaveButton();
            logger.info("Clicked on Save button");

            WaitUtils.waitFor9000Milliseconds();

            String testSuitId= individualTestSuitePage.getTestSuiteId();
            logger.info("Suite ID captured: " + testSuitId);
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickDelete();
            logger.info("Test Suit is deleted");

            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(cycleName);

            WaitUtils.waitFor1000Milliseconds();


            individualTestSuitePage.verifyCreationAndDeletionTestSuiteNotificationsAreDisabled(testSuitId);
            logger.info("Verified that both the creation and deletion notification are disabled ");

            logger.info("************ Test Case Finished Successfully *******************");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
