package testCases.demo;

import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class testplan_execute_automation extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Demo_flow_Testplan_and_executetestcacse() throws InterruptedException {
        logger.info("****** Starting the TC001 :  Demo*****************");
        try {
            login();
            logger.info("Logged in successfully");
            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            IndividualTestCyclePage individualTestCyclePage = new IndividualTestCyclePage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandProjectSTG("STG- PulseCodeOnAzureCloud");
            logger.info("Expanded the project dropdown");

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite("New Release 12-09-2025");
            logger.info("Selected project: " + "New Release 12-09-2025");

            testPlanPage.clickNewTestCycle();
            logger.info("Clicked on the new testCycle");

            individualTestCyclePage.setTestCycleName("Testing 30-10-2025");
            logger.info("added the test cycle name" + "Testing 30-10-2025");

            individualTestCyclePage.setDescription("End-to-End Testing");
            logger.info("added the description for cycle" + "End-to-End Testing");

            individualTestCyclePage.clickSave();
            logger.info("clicked on save");

            individualTestCyclePage.getTestCycleCreatedSuccessMessage();
            logger.info("Test Cycle created sucessfully");

            Thread.sleep(3000);

            executeLandingPage.clickExecuteTab();
            logger.info("clicked on Executetestcase");

            executeLandingPage.clickOnProject();
            logger.info("Clicked on project Name ....");

            executeLandingPage.clickArrowRightPointingForExpandModule("New Release 12-09-2025");
            logger.info("Expanded the release:" + "New Release 12-09-2025");

            executeLandingPage.clickTestCycle("Testing 30-10-2025");
            logger.info("clicked on the cycle : " + "Testing 30-10-2025");

            executeLandingPage.clickCreateTestRunButton();
            logger.info("clicked on test run button");

            authorTestCasePage.selectEpic("Epic Mohit");
            logger.info("Epic selected from dropdown:" + "Epic Mohit");

            authorTestCasePage.selectFeature("Mohit Feature");
            logger.info("Feature selected from dropdown:" + "Mohit Feature");

            authorTestCasePage.clickRequirement("RQ-489");
            logger.info("Requirement selected :" + "RQ-489");

            executeLandingPage.selectTestCaseCheckbox("TC-427");
            logger.info("Selected Test Case ID:" + "TC-427");

            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked on Save button ");

            executeLandingPage.clickPlayActionById("TC-427");
            logger.info("clicked on action of TC:" + "TC-427");

            individualTestRun.EnterActualResultOfTheStep(Integer.parseInt("1"), "User able to navigate author tab");
            individualTestRun.EnterActualResultOfTheStep(Integer.parseInt("2"), "user able to click on release button");

            individualTestRun.SelectStatusOfTheStep(Integer.parseInt("1"), "Blocked");
            individualTestRun.SelectStatusOfTheStep(Integer.parseInt("2"), "Incomplete");

            individualTestRun.selectCheckAllStepsCheckbox();
            logger.info("clicked on all step check box");

            individualTestRun.selectDropdownStatusBesidesUpdate("Failed");
            logger.info("Select status:" + "Failed");

            individualTestRun.clickUpdate();
            logger.info("clicked on update button");

            Thread.sleep(2000);

//          individualTestRun.clickSaveButton();
//           logger.info("Clicked on save button");
            Thread.sleep(2000);
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandProjectSTG("STG- PulseCodeOnAzureCloud");
            logger.info("Expanded the project dropdown");

            testPlanPage.expandOnReleaseOrTestCycleOrTestSuite("New Release 12-09-2025");
            logger.info("Selected project: " + "New Release 12-09-2025");

            testPlanPage.clickOnReleaseOrTestCycleOrTestSuite("Testing 30-10-2025");
            testPlanPage.clickDelete();
            testPlanPage.clickOnConfirmDeleteYes("Testing 30-10-2025");

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
