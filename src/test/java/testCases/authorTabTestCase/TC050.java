package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC050 extends BaseClass {

    @Test(dataProvider = "tc050", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyMultipleRows(
            String requirementId, String TestcaseId
    ) throws InterruptedException {
        logger.info("************ Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("clicked on author tab");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement(requirementId);
            logger.info("add requirement");
            WaitUtils.waitFor3000Milliseconds();;
            authorTestCasePage.clickTestCase(TestcaseId);
            logger.info("clicked on a testcase");
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            WaitUtils.waitFor2000Milliseconds();;
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
