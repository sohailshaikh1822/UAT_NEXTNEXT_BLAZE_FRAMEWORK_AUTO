package testCases.authorTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC054 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyVisibilityOfPaginationControls() throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.clickCollapseToggle();
            logger.info("Clicked collapse toggle icon");
            Assert.assertFalse(authorTestCasePage.getFeatureVisibility(), "Feature dropdown should not be visible after collapse");
            authorTestCasePage.clickExpandToggle();
            logger.info("Clicked expand toggle icon");
            Assert.assertTrue(authorTestCasePage.getFeatureVisibility(), "Feature dropdown should be visible after expand");

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
