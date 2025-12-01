package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC065 extends BaseClass {

    @Test(dataProvider = "tc042", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyCreateTestRun(
            String requirementId, String TestcaseId
    ) throws InterruptedException {
        logger.info("************ Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            authorTestCasePage.clickRequirement(requirementId);
            Assert.assertTrue(authorTestCasePage.isLinkedTestCaseTableVisible(), "Linked Test Case table not visible");
            logger.info("Verified linked test case table is displayed");
            authorTestCasePage.clickTestCase(TestcaseId);
            logger.info("Clicked on linked test case");
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            Assert.assertTrue(individualTestCasePage.isCreateTestRunVisible(), "Create Test Run button is not visible");
            logger.info("Create Test Run button is visible");

            Assert.assertTrue(individualTestCasePage.isCreateTestRunClickable(), "Create Test Run button is not clickable");
            logger.info("Create Test Run button is clickable");

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
