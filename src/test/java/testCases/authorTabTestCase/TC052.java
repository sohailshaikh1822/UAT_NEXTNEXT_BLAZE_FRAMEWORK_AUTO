package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC052 extends BaseClass {

    @Test(dataProvider = "tc052", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verifyRQSearchFunctionality(String rqName, String rqTitle) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully.");

            logger.info("Navigating to 'Author Test Case' tab.");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            // Search by RQ ID
            authorTestCasePage.searchRq(rqName);
            logger.info("Searched using RQ ID: " + rqName);

            String expectedRQId = authorTestCasePage.getRQId();
            Assert.assertEquals(expectedRQId, rqName, "Mismatch in RQ ID after search.");
            logger.info("Successfully verified RQ ID: " + expectedRQId);

            // Search by RQ Title
            logger.info("Starting search using RQ Title: " + rqTitle);
            authorTestCasePage.searchRq(rqTitle);
            Thread.sleep(3000);
            String expectedRQTitle = authorTestCasePage.getRQTitle();
            Assert.assertEquals(expectedRQTitle, rqTitle, "Mismatch in RQ Title after search.");
            logger.info("Successfully verified RQ Title: " + expectedRQTitle);

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Execution Completed ************");

    }
}
