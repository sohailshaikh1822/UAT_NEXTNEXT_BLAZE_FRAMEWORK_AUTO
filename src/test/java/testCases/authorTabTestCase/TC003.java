package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC003 extends BaseClass {

    @Test( retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyAllAvailableEpicsDisplayed() throws InterruptedException {

        logger.info("****** Starting TC003: Verify all available Epics are displayed ******");

        try {
            login();
            logger.info("Logged in successfully");

            getDriver().manage().window().maximize();

            AuthorTestCasePage authorTestCasePage =
                    new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");

            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked Epic dropdown");
            WaitUtils.waitFor3000Milliseconds();

            int actualEpicCount = authorTestCasePage.getCountInEpic();
            logger.info("Total Epics displayed (excluding 'Please Select'): " + actualEpicCount);

            Assert.assertTrue(
                    actualEpicCount > 0,
                    "No Epics are displayed in the Epic dropdown"
            );

            logger.info("Verified that all available Epics are displayed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ TC003 Finished *************************");
    }
}
