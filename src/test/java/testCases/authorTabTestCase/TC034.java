package testCases.authorTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import DataProviders.AuthorTestCaseDataProvider;
import utils.RetryAnalyzer;

public class TC034 extends BaseClass {

    @Test(dataProvider = "tc034", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyPaginationReset(
            String epicName,
            String featureName,
            String nextFeature
    ) throws InterruptedException {
        logger.info("****** Starting TC034: Verify pagination resets properly ******");
        try {

            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");

            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);

            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);

            int initialPage = Integer.parseInt(authorTestCasePage.showPaginationOfRequirement());
            authorTestCasePage.clickNextArrow();
            int movedPage = Integer.parseInt(authorTestCasePage.showPaginationOfRequirement());

            Assert.assertNotEquals(initialPage, movedPage,
                    "Pagination did not move to the next page");
            logger.info("Successfully moved from page " + initialPage + " to " + movedPage);

            authorTestCasePage.selectFeature(nextFeature);
            logger.info("Re-applied Epic & Feature filters");

            int resetPage = Integer.parseInt(authorTestCasePage.showPaginationOfRequirement());
            Assert.assertEquals(resetPage, 1,
                    "Pagination did not reset to page 1 after filter re-apply");
            logger.info("Pagination reset successfully to page 1");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ TC034 Finished ************");
    }
}
