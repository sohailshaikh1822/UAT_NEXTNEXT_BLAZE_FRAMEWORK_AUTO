package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC070 extends BaseClass {
    @Test(dataProvider = "tc070", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void VerifyTooltipIsShownWhenHoveringOverADisabledNotificationWhenATestSuitIsDeletedFromTestPlanTab(
            String projectName,
            String releaseName,
            String suiteName
    ) throws InterruptedException {
        logger.info("****** Starting TC057 *****************");
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
            RequirementTabPage requirementTabPage =new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage=new AddRequirementPage(getDriver());
            individualTestSuitePage.enterTestSuiteName(suiteName);
            logger.info("Entered the suite name");

            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.clickSaveButton();
            logger.info("clicked on save button");

            String tsId = addRequirementPage.getModuleId();
            logger.info("new rl"+ tsId);
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickDelete();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(suiteName);
            WaitUtils.waitFor1000Milliseconds();

            individualTestSuitePage.verifyDeletedTestSuiteTooltip(tsId);
            logger.info("tootltip verfied");


        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
