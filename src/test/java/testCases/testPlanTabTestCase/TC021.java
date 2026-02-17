package testCases.testPlanTabTestCase;

import org.apache.commons.math3.ode.nonstiff.AdamsBashforthFieldIntegrator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC021 extends BaseClass {

    @Test(dataProvider = "tc021", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNotificationPopupAfterCameAddingNewReleaseUnderProject(String projectName, String releaseName) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Adding a New Release Under a Project *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualModulePage individualModulePage=new IndividualModulePage(getDriver());
            RequirementTabPage requirementTabPage =new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage=new AddRequirementPage(getDriver());
WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickNewRelease();
            logger.info("Clicked Add Release button, release form appeared");

            testPlanPage.enterReleaseName(releaseName);
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectReleaseStatus("Planned");
            testPlanPage.selectStartDate("05-01-2025");
            testPlanPage.selectEndDate("10-01-2025");
            String releaseDescription = "Automated description for new release";
            testPlanPage.enterDescription(releaseDescription);
            String releaseNotes = "Test Notes for the new release";
            testPlanPage.enterReleaseNotes(releaseNotes);

            logger.info("Entered all release details");

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");

           String expectedNotificationPopup= requirementTabPage.handleToastNotification();
            String rlId = addRequirementPage.getModuleId();
            rlId = rlId.replaceAll("\\*$", "");
            rlId = "'" + rlId.replace("'", "") + "'";

            logger.info("new rl"+ rlId);

            Assert.assertEquals(rlId +" is created by Julie Kumari.",expectedNotificationPopup,"not matched");

            WaitUtils.waitFor2000Milliseconds();

            logger.info("New release added successfully and verified in the list");

            logger.info("************ Test Case Finished Successfully *************************");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info(
                "************ Test Case Finished: Verify Deleting a Release Removes It from the UI *****************");
    }
}
