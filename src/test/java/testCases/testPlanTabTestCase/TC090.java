package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC090 extends BaseClass {
    @Test(dataProvider = "tc090", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDefaultVersionIs1WhenATestCycleIsCreated(
            String projectName,
            String releaseName
    )
            throws InterruptedException {
        logger.info("****** Starting TC090 *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualTestCyclePage individualTestCyclePage =new IndividualTestCyclePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.expandProjectSTG(projectName);
            logger.info("Expanded STG Project");

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the module ");

            testPlanPage.clickNewTestCycle();
            logger.info("Clicked on the new testCycle");

            individualTestCyclePage.clickSave();
            logger.info("Clicked on the save button");

            individualTestCyclePage.clickOnTestCycleHistoryTab();
            logger.info("Test cycle History Tab");
            WaitUtils.waitFor1000Milliseconds();

            individualTestCyclePage.clickOnTestCycleHistoryTab();
            logger.info("go to on test cycle history tab ");
            WaitUtils.waitFor1000Milliseconds();

            individualTestCyclePage.verifyHistoryTableColumns();
            logger.info("Verified Test case history columns");
            WaitUtils.waitFor1000Milliseconds();

            individualTestCyclePage.verifyDefaultVersionIsOne();
            logger.info("verified version numbers");
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
