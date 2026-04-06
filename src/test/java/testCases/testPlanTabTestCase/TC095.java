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

public class TC095 extends BaseClass {
    @Test(dataProvider = "tc095", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyVersionNumberMatchesInTestCycleAndVersionHistory(
            String projectName,
            String releaseName,
            String CycleName
    )
            throws InterruptedException {
        logger.info("****** Starting TC094 *****************");

        try {
            login();
            logger.info("Logged in successfully");
            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
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

            Assert.assertTrue(
                    individualTestCyclePage.verifyDateTimeInVersionHistory(),
                    "Date and Time (IST) not displayed correctly in version history"
            );
            WaitUtils.waitFor2000Milliseconds();
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
