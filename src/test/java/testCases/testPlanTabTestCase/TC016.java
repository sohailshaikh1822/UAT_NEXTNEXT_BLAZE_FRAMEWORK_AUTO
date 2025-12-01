package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC016 extends BaseClass {

    @Test(dataProvider = "tc010", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyCreationOfTestSuite(
            String projectName,
            String releaseName,
            String testCycleName,
            String testDescription,
            String suiteName,
            String startDate,
            String endDate,
            String executionType
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

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite(testCycleName);
            logger.info("navigated to the created cycle");

            testPlanPage.clickNewTestSuite();
            logger.info("Clicked on the new test suite icon ");

            IndividualTestSuitePage individualTestSuitePage = new IndividualTestSuitePage(getDriver());

            individualTestSuitePage.enterTestSuiteName(suiteName);
            logger.info("Entered the suite name");

            individualTestSuitePage.enterDescription(testDescription);
            logger.info("entered the description");

            individualTestSuitePage.setPlannedStartDate(startDate);
            individualTestSuitePage.setPlannedEndDate(endDate);
            logger.info("Entered the date");

            individualTestSuitePage.selectExecutionType(executionType);
            logger.info("selected the execution type");

            individualTestSuitePage.clickSaveButton();
            logger.info("clicked on save button");

            String actualSuccessMsg = individualTestSuitePage.getTestSuiteCreatedSuccessMessage();
            String expectedSuccessMsg = "Test Suite created successfully.";

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
