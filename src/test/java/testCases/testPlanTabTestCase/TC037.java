package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC037 extends BaseClass {
    @Test(dataProvider = "tc010", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verifyDeletedReleaseRestoreSuccessFully(String projectName, String releaseName, String testCycleName,
                                                        String testDescription,
                                                        String suiteName,
                                                        String startDate,
                                                        String endDate,
                                                        String executionType) throws InterruptedException {
        logger.info("****** Starting TC037 *****************");

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

            String testSuiteNameWithTimestamp = suiteName;
            individualTestSuitePage.enterTestSuiteName(testSuiteNameWithTimestamp);
            logger.info("Entered the suite name");

            individualTestSuitePage.enterDescription(testDescription);
            logger.info("entered the description");
            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.setPlannedStartDate(startDate);
            individualTestSuitePage.setPlannedEndDate(endDate);
            logger.info("Entered the date");

            individualTestSuitePage.selectExecutionType(executionType);
            logger.info("selected the execution type");

            WaitUtils.waitFor1000Milliseconds();
            individualTestSuitePage.clickSaveButton();
            logger.info("clicked on save button");


            String expectedNotificationPopup= requirementTabPage.handleToastNotification();
            String tsId = addRequirementPage.getModuleId();
            tsId = tsId.replaceAll("\\*$", "");
            tsId = tsId.replace("'", "");

            logger.info("new rl"+ tsId);

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickDelete();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(suiteName);

            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectObjectDropdownValue("TestSuite");
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.smoothScrollRecycleBin();
            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.selectRadioById(tsId);
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickRestoreButton();

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
