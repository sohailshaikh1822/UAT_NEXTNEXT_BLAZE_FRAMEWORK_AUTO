package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC042 extends BaseClass {

    @Test(dataProvider = "tc042", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyVisibilityOfAllFieldsInAddTestCase(String requirementId, String TestcaseId
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            authorTestCasePage.clickRequirement(requirementId);
            authorTestCasePage.clickAddTestcase();
            AddTestcasePage addTestCase = new AddTestcasePage(getDriver());
            addTestCase.setTestCaseName("For unlink");
            addTestCase.clickSave();
            String testCaseId = addTestCase.getTestcaseId("For unlink");
             WaitUtils.waitFor1000Milliseconds();
            AuthorTestCasePage authorTestCasePage1 = new AuthorTestCasePage(getDriver());
            authorTestCasePage1.clickActionIcon(testCaseId);
            logger.info("Clicked unlink icon for test case ");
            authorTestCasePage1.confirmUnlink();
            logger.info("Clicked Yes to unlink the test case successfully");

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
