package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC015 extends BaseClass {

    @Test(dataProvider = "tc015", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNewTestCycleCreationMessage(
            String projectName,
            String releaseName,
            String testCycleName,
            String testDescription
    )
            throws InterruptedException {
        logger.info(
                "****** Starting Test Case: Verify Release List Updates Based on Project Selection *****************");
        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.expandProjectSTG(projectName);
            logger.info("Expanded STG Project");

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(releaseName);
            logger.info("Clicked on the module ");

            testPlanPage.clickNewTestCycle();
            logger.info("Clicked on the new testCycle");

            IndividualTestCyclePage individualTestCyclePage = new IndividualTestCyclePage(getDriver());
            individualTestCyclePage.setTestCycleName(testCycleName);
            logger.info("added the test cycle name");

            individualTestCyclePage.setDescription(testDescription);
            logger.info("added the description for cycle");

            Assert.assertEquals(individualTestCyclePage.getTargetRelease(), releaseName);
            logger.info("verified the targeted release ");

            individualTestCyclePage.clickSave();
            logger.info("Clicked on the save button");

            String actualSuccessMsg = individualTestCyclePage.getTestCycleCreatedSuccessMessage();
            String expectedSuccessMsg = "Test Cycle created successfully.";

            logger.info("Validating success message...");
            logger.info("Expected: " + expectedSuccessMsg);
            logger.info("Actual: " + actualSuccessMsg);

            Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Validation message did not match!");
            logger.info("Verified success notification successfully");

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
