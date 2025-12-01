package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.util.Random;

public class TC006 extends BaseClass {
    @Test(dataProvider = "tc006", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyDeletingReleaseRemovesItFromUI(String projectName, String releaseName1)
            throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Deleting a Release Removes It from the UI *****************");
        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");
            testPlanPage.clickOnTheProjectName();
            testPlanPage.clickNewRelease();
            String releaseName = releaseName1 + String.valueOf((100 + new Random().nextInt(900)));
            testPlanPage.enterReleaseName(releaseName);
            testPlanPage.clickSaveRelease();

            testPlanPage.selectProjectByName(releaseName);
            logger.info("Selected project: " + releaseName);

            Assert.assertTrue(testPlanPage.isReleasePresentInList(releaseName),
                    "Pre-condition failed: Release is not present before deletion!");
            logger.info("Verified release exists: " + releaseName);

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            testPlanPage.clickDelete();
            logger.info("Clicked delete icon for release: " + releaseName);

            testPlanPage.clickOnConfirmDeleteYes(releaseName);
            logger.info("Confirmed delete action for release: " + releaseName);

            Thread.sleep(1000); // Wait for deletion to process

            Assert.assertFalse(testPlanPage.isReleasePresentInList(releaseName),
                    "Release still present in the list after deletion!");
            logger.info("Verified release is successfully removed from UI: " + releaseName);

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