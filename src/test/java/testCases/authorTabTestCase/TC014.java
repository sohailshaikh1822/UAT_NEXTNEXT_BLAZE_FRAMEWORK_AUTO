package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC014 extends BaseClass {

    @Test(
            dataProvider = "tc011",
            dataProviderClass = AuthorTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyRQListBasedOnFeature(String epicName, String feature) throws InterruptedException {

        logger.info("****** Starting TC014: Verify RQ list based on Feature ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage =
                    new AuthorTestCasePage(getDriver());

            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.clickEpic();
            logger.info("Clicked Epic dropdown");

            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);

            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.selectFeature(feature);
            logger.info("Selected Feature: " + feature);

            WaitUtils.waitFor3000Milliseconds();

            int rqCount = authorTestCasePage.getCountRQInFeature();
            logger.info("Requirement count for selected feature: " + rqCount);

            Assert.assertTrue(
                    rqCount > 0,
                    "No Requirements are displayed for Feature: " + feature
            );

            logger.info("Verified Requirement list is displayed based on Feature");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ TC014 Finished *************************");
    }
}
