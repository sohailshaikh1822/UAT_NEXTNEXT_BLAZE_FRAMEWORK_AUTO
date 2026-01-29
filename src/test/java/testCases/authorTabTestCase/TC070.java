package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC070 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyMandatoryFieldValidationInAddTestCase() throws InterruptedException {
        logger.info("************ Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Clicked Author Testcase tab");
            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.clickRequirement("RQ-438");
            logger.info("Selected requirement RQ-438");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked Add Testcase button");

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickSubmitButtonOnAddTestCaseModal();
            logger.info("Clicked Save/Submit on Add Test Case modal");

            Assert.assertTrue(authorTestCasePage.isErrorDisplayedForField("Name"), "Error for Name field not displayed");
            logger.info("Validation notification appeared as expected for missing mandatory fields");
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
