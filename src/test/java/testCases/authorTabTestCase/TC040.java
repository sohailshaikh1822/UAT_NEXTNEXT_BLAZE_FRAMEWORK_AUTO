package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC040 extends BaseClass {

    @Test(dataProvider = "tc040", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyCloseButton(
            String requirementId, String TestcaseId
    ) throws InterruptedException {
        logger.info("************ Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            authorTestCasePage.clickRequirement(requirementId);
            authorTestCasePage.linkTestCaseIdFromId(TestcaseId).click();
            IndividualTestCasePage closebutton = new IndividualTestCasePage(getDriver());
            closebutton.closebutton();
            logger.info("Close button is been able to close the testcase successfully");
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
