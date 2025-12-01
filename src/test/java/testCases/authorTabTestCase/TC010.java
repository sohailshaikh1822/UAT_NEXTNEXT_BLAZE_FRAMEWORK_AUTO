package testCases.authorTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC010 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyFeatureVisibility() throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            boolean b = authorTestCasePage.getFeatureVisibility();
            Assert.assertTrue(b, "It is not visible..");
            logger.info("Verification successfully done");

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
