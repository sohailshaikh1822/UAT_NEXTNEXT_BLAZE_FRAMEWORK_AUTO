package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC056 extends BaseClass {
    @Test(dataProvider = "tc036", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verifyNotificationAfterDeletedTestCycle(String projectName, String releaseName,  String testCycleName,
                                                          String testDescription ,String objectType) throws InterruptedException {
        logger.info("****** Starting TC056 *****************");

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
            String actualMessage =testPlanPage.getToastNotificationMessage();
            String expectedMessage="'" + tcId+ "' is deleted by Julie Kumari.";
            Assert.assertEquals(actualMessage,expectedMessage,"Confirmation message has not matched");


        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
