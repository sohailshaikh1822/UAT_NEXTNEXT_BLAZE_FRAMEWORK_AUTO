package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TC051 extends BaseClass {
    @Test(dataProvider = "tc051", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyingTheToastNotificationMessageAfterCreatingANewSuiteInTheTestPlanModule(
            String projectName,
            String releaseName,
            String suiteName
    )throws InterruptedException
    {
        logger.info("****** Starting TC051 *****************");
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
            logger.info("Clicked ReleaseName");
            testPlanPage.clickNewTestSuite();
            logger.info("Clicked on the new test suite icon ");
            IndividualTestSuitePage individualTestSuitePage = new IndividualTestSuitePage(getDriver());

            individualTestSuitePage.enterTestSuiteName(suiteName);
            logger.info("Entered the suite name");

            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.clickSaveButton();
            logger.info("clicked on save button");

            String tsId = individualTestSuitePage.getTestSuiteId();

            logger.info("new rl"+ tsId);
            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.verifyRestoreToastMessage(tsId);
            logger.info("Toast message");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}
