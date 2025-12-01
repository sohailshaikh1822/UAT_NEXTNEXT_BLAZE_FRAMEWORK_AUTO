package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

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
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
            executeLandingPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded the Release dropdown from left panel");

            executeLandingPage.clickRelease(releaseName);
            logger.info("Clicked on the release from the dropdown");
            executeLandingPage.clickCreateTestRunButton();
            logger.info("Clicked on Create Test Run button");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            authorTestCasePage.clickRequirement(reqId);
            logger.info("Selected a Requirement");

            executeLandingPage.selectTestCaseCheckbox(tCaseId);
            logger.info("Selected a Test Case ");

            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked on Save button from the Test Run popup");

            boolean isTestRunCreated = executeLandingPage.isTestRunCreatedMessageDisplayed();
            Assert.assertTrue(isTestRunCreated, "Test Run not created");
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
