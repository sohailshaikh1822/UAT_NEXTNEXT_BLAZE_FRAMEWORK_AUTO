package testCases.authorTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC072 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyCloseFunctionalityDiscardsChanges() throws InterruptedException {
        logger.info("************ Starting the Test Case: Verify close functionality discards changes in test cases form *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            authorTestCasePage.clickAuthorTestcase();
            logger.info("Clicked Author Testcase tab");
            authorTestCasePage.clickRequirement("RQ-438");
            logger.info("Selected requirement RQ-438");

            String testCaseId = "TC-382";
            String originalName = authorTestCasePage.getTestCaseNameById(testCaseId);
            logger.info("Selected Test Case ID: " + testCaseId + ", original name: " + originalName);
            authorTestCasePage.clickTestCasesId(testCaseId);
            logger.info("Opened test case details for editing");

            String newName = originalName + "_MODIFIED";
            authorTestCasePage.enterTestCaseNameInEditModal(newName);
            logger.info("Modified test case name to: " + newName);

            authorTestCasePage.clickCloseButtonOnEditModal();
            logger.info("Clicked Close button");
            authorTestCasePage.confirmCloseDiscardChanges();
            logger.info("Confirmed discard changes");
            String nameAfterClose = authorTestCasePage.getTestCaseNameById(testCaseId);
            Assert.assertEquals(nameAfterClose, originalName, "Unsaved changes were not discarded!");
            logger.info("Verified that unsaved changes were discarded and original name is shown");

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
