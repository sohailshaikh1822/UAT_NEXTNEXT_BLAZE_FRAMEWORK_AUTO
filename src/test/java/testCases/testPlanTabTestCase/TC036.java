package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC036 extends BaseClass {
    @Test(dataProvider = "tc008", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verifyDeletedCycleRestoreSuccessFully(String projectName, String releaseName,  String testCycleName,
                                                      String testDescription) throws InterruptedException {
        logger.info("****** Starting TC036 *****************");

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
            logger.info("Clicked on the module ");

            testPlanPage.clickNewTestCycle();
            logger.info("Clicked on the new testCycle");

            IndividualTestCyclePage individualTestCyclePage = new IndividualTestCyclePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            individualTestCyclePage.setTestCycleName(testCycleName);
            logger.info("added the test cycle name");

            individualTestCyclePage.setDescription(testDescription);
            logger.info("added the description for cycle");

            Assert.assertEquals(individualTestCyclePage.getTargetRelease(), releaseName);
            logger.info("verified the targeted release ");
            WaitUtils.waitFor2000Milliseconds();
            individualTestCyclePage.clickSave();
            logger.info("Clicked on the save button");

            AddRequirementPage addRequirementPage=new AddRequirementPage(getDriver());
            String tcId = addRequirementPage.getModuleId();
            tcId = tcId.replaceAll("\\*$", "");
            tcId = tcId.replace("'", "");

            logger.info("new rl"+ tcId);
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.clickDelete();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(testCycleName);
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectObjectDropdownValue("TestCycle");
            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.smoothScrollRecycleBin();
            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.selectRadioById(tcId);
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickRestoreButton();


        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
