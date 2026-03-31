package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC079 extends BaseClass {

    @Test(dataProvider = "tc076", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_navigation_to_release_history_tab(
            String projectName,
            String releaseName,
            String CycleName,
            String desc
    )
            throws InterruptedException {
        logger.info("****** Starting TC079:  *******");
        try {
            login();
            logger.info("Logged in successfully");
            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualTestCyclePage individualTestCyclePage = new IndividualTestCyclePage(getDriver());
            IndividualReleasePage individualReleasePage = new IndividualReleasePage(getDriver());
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");
            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");
            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);
            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the existing release: " + releaseName);
            WaitUtils.waitFor1000Milliseconds();

            individualReleasePage.clickOnReleaseHistoryTab();

            logger.info("Test cycle History Tab");
            WaitUtils.waitFor1000Milliseconds();
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
