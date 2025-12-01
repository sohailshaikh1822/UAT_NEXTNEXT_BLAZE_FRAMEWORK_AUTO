package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC050 extends BaseClass {

    @Test(dataProvider = "tc040", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyMultipleRows(
            String requirementId, String TestcaseId
    ) throws InterruptedException {
        logger.info("************ Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            authorTestCasePage.clickRequirement(requirementId);
            Thread.sleep(3000);
            authorTestCasePage.linkTestCaseIdFromId(TestcaseId).click();
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            Thread.sleep(3000);
            individualTestCasePage.clickAddRow();
            String beforeCount = individualTestCasePage.getStepCount("1");
            logger.info("Step count before adding a row" + beforeCount);
            individualTestCasePage.clickAddRow();
            String afterCount = individualTestCasePage.getStepCount("2");
            logger.info("Step count before adding a row" + afterCount);

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
