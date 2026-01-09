package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.logging.Logger;

public class TC024 extends BaseClass {
    @Test(dataProvider = "tc024", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyEmptyReleaseName(
            String projectName,
            String releaseName,
            String status
    )
            throws InterruptedException {
        logger.info("****** Starting TC24 : Verify Adding a New Release Under a Project *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualReleasePage individualReleasePage = new IndividualReleasePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            testPlanPage.clickNewRelease();
            logger.info("Clicked Add Release button, release form appeared");

            logger.info("Release detailed form opens");

            individualReleasePage.setReleaseName(releaseName);
            logger.info("Empty release name:"+releaseName);
            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");

            WaitUtils.waitFor3000Milliseconds();

            individualReleasePage.setReleaseStatus(status);
            logger.info("Selected release status:"+ status);

            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button again after update");

            WaitUtils.waitFor3000Milliseconds();

            String releaseId = individualReleasePage.getReleaseId();
            logger.info("Release ID captured: " + releaseId);

            WaitUtils.waitFor9000Milliseconds();

            individualReleasePage.verifyReleaseUpdateNotification(releaseId);
            logger.info("Release update notification successfully Verified");



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
