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

public class TC049 extends BaseClass {

    @Test(dataProvider = "tc049", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyingTheToastNotificationMessageAfterCreatingANewReleaseInTheTestPlanModule(
            String projectName,
            String releaseName
    ) throws InterruptedException
    {
        logger.info("****** Starting Test Case *****************");

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

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");

            String expectedNotificationPopup= requirementTabPage.handleToastNotification();
            String rlId = addRequirementPage.getModuleId();

            logger.info("new rl"+ rlId);
            WaitUtils.waitFor2000Milliseconds();

            testPlanPage.verifyRestoreToastMessage(rlId);
            logger.info("Toast message");

            logger.info("************ Test Case Finished Successfully *************************");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info(
                "************ Test Case Finished ****************");
    }
}
