package testCases.authorTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC027 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyVisibilityOfPaginationControls() throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");

            boolean isPaginationAligned = authorTestCasePage.verifyPaginationAlignment();
            Assert.assertTrue(isPaginationAligned, "Pagination is either not visible or not horizontally aligned");
            logger.info("Pagination controls are visible and horizontally aligned");

            Assert.assertTrue(authorTestCasePage.showPaginationOfRequirement().contains("1"));
            logger.info("Verified user is on Page 1");

            Assert.assertNotEquals(authorTestCasePage.checkIfPreviousButtonIsClickable(), "pointer");
            logger.info("Verified Previous button is disabled on Page 1");

            Assert.assertEquals(authorTestCasePage.checkIfNextButtonIsClickable(), "pointer");
            logger.info("Verified Next button is enabled on Page 1");

            logger.info("Pagination controls verified successfully");
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
