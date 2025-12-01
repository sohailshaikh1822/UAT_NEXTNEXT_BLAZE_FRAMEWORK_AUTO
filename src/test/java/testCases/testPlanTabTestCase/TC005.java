package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC005 extends BaseClass {

    @Test(dataProvider = "tc005", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyAddingNewReleaseUnderProject(String projectName, String releaseName) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Adding a New Release Under a Project *****************");

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

            testPlanPage.clickNewRelease();
            logger.info("Clicked Add Release button, release form appeared");

            testPlanPage.enterReleaseName(releaseName);
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

            Thread.sleep(3000);

            boolean isReleaseAdded = testPlanPage.isReleasePresentInList(releaseName);
            Assert.assertTrue(isReleaseAdded, "New release was not added successfully");
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
