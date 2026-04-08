package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC099 extends BaseClass {
    @Test(dataProvider = "tc099", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyTestSuiteHistorytabDisplaysVersionDetails(
            String projectName,
            String releaseName,
            String suitName
    )
            throws InterruptedException {
        logger.info("****** Starting TC098 *****************");

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
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(suitName);
            logger.info("Clicked on the existing release: " + suitName);
            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.clickOnTestSuiteHistoryTab();
            logger.info("Test Suite History Tab");
            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.verifyHistoryTableColumns();
            logger.info("Verified history section is displayed");
            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.verifyDefaultVersionIsOne();
            logger.info("history table displayed columns");
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
