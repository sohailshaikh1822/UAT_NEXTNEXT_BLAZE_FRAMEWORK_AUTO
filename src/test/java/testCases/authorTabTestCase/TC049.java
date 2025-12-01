package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC049 extends BaseClass {

    @Test(dataProvider = "tc049", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyCloseInTestcases(
            String requirementId, String TestcaseId
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case ****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            authorTestCasePage.clickRequirement(requirementId);
            authorTestCasePage.clickAddTestcase();
            AddTestcasePage addTestCase = new AddTestcasePage(getDriver());
            addTestCase.setTestCaseName("For unlink");
            addTestCase.clickSave();
            String testCaseId = addTestCase.getTestcaseId("For unlink");
            authorTestCasePage.clickActionIcon(testCaseId);
            logger.info("Clicked unlink icon for test case ");

            authorTestCasePage.confirmUnlink();
            logger.info("Clicked Yes to unlink the test case successfully");

            boolean isDelete = authorTestCasePage.isRowDeleted(testCaseId);
            Assert.assertTrue(isDelete, "Row not deleted");
            logger.info("Row deleted");

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
