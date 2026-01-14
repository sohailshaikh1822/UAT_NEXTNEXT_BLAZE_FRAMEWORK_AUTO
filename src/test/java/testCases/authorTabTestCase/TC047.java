package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC047 extends BaseClass {

    @Test(dataProvider = "tc047", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyTypeDropdown(String requirementId
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement(requirementId);
            logger.info("Selected Requirement: " + requirementId);
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on Add Test Case button");
            WaitUtils.waitFor1000Milliseconds();
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            Assert.assertTrue(addTestcasePage.isAllTypeOptionsVisible());
            logger.info("Verified Successfully....");
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
