package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC092 extends BaseClass {
    @Test(dataProvider = "tc088", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_version_increases_by_when_test_cycle_is_updated_and_saved(
            String projectName,
            String releaseName,
            String CycleName
    )
            throws InterruptedException {
        logger.info("****** Starting TC092 *****************");

        try {
            login();
            logger.info("Logged in successfully");
            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualTestSuitePage individualTestSuitePage = new IndividualTestSuitePage(getDriver());
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.expandOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the existing release: " + releaseName);
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(CycleName);
            logger.info("Clicked on the existing Cycle: " + CycleName);
            IndividualTestCyclePage individualTestCyclePage =new IndividualTestCyclePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            individualTestCyclePage.clickOnTestCycleHistoryTab();
            logger.info("Test cycle History Tab");
            WaitUtils.waitFor1000Milliseconds();
            individualTestCyclePage.verifyHistorySectionIsDisplayed();
            logger.info("Verified history section is displayed");
            WaitUtils.waitFor1000Milliseconds();
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
