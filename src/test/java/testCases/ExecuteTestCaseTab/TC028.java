package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

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
            executeLandingPage.clickToSelectProject(projectName);
            logger.info("Expanded the Release dropdown from left panel");

            executeLandingPage.clickRelease(releaseName);
            logger.info("Clicked on the desired release from the dropdown");
            executeLandingPage.clickCreateTestRunButton();
            logger.info("Clicked on Create Test Run button");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            authorTestCasePage.clickRequirement(reqId);
            logger.info("Selected a Requirement from left panel");

            executeLandingPage.selectTestCaseCheckbox(testCaseId1);
            logger.info("Selected Test Case ID: TC_001");

            executeLandingPage.selectTestCaseCheckbox(testCaseId2);
            logger.info("Selected Test Case ID: TC_002");

            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked on Save button in popup");

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
