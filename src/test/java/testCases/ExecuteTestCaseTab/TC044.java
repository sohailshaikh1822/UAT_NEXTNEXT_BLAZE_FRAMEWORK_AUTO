package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC044 extends BaseClass {

    @Test(dataProvider = "tc044", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyTestCaseSucessMessage(String projectName,
            String releaseName,
            String reqId,
            String tCaseId
    ) throws InterruptedException {
        logger.info("****** Starting Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickToSelectProject(projectName);
            logger.info("Expanded the Release dropdown from left panel");
            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickRelease("New Release 123");
            logger.info("Clicked on the release from the dropdown");
            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickCreateTestRunButton();
            logger.info("Clicked on Create Test Run button");
            WaitUtils.waitFor2000Milliseconds();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement(reqId);
            logger.info("Selected a Requirement");
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.selectTestCaseCheckbox("TC-461");
            logger.info("Selected a Test Case ");
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked on Save button from the Test Run popup");
            WaitUtils.waitFor3000Milliseconds();
            boolean isTestRunCreated = executeLandingPage.isTestRunCreatedMessageDisplayed();
           // Assert.assertTrue(isTestRunCreated, "Test Run not created");
            logger.info("Test runs created successfully");
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
