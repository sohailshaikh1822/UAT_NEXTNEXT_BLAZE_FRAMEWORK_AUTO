package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC028 extends BaseClass {

    @Test(dataProvider = "tc028", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectSelectionFromDropdown(String projectName,
                                                   String releaseName,
                                                   String reqId,
                                                   String testCaseId1,
                                                   String testCaseId2) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickToSelectProject(projectName);
            logger.info("Expanded the Release dropdown from left panel");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickRelease(releaseName);
            logger.info("Clicked on the desired release from the dropdown");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickCreateTestRunButton();
            logger.info("Clicked on Create Test Run button");
            WaitUtils.waitFor2000Milliseconds();

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            authorTestCasePage.clickRequirement(reqId);
            logger.info("Selected a Requirement from left panel");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.selectTestCaseCheckbox(testCaseId1);
            logger.info("Selected Test Case ID: TC_001");

            executeLandingPage.selectTestCaseCheckbox(testCaseId2);
            logger.info("Selected Test Case ID: TC_002");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked on Save button in popup");
            WaitUtils.waitFor2000Milliseconds();

            boolean isTestRunCreated = executeLandingPage.isTestRunCreatedMessageDisplayed();
            Assert.assertTrue(isTestRunCreated, "Test Run creation success message not displayed!");

            logger.info("Assertion passed — 'Test runs created successfully.' message is displayed");
            logger.info("******** Test Run created successfully ********");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}