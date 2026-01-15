package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC012 extends BaseClass {

    @Test(
            dataProvider = "tc012",
            dataProviderClass = AuthorTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyClickFunctionOfFeaturesDropdown(String epicName) throws InterruptedException {

        logger.info("****** Starting TC012: Verify Feature dropdown ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic dropdown");

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);

            List<String> features = authorTestCasePage.getAllFeatures();

            logger.info("Total features loaded: " + features.size());
            System.out.println("Features: " + features);

            Assert.assertFalse(features.isEmpty(), "Feature dropdown is empty after selecting Epic");

            logger.info("Feature dropdown is clickable and populated");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ TC012 Finished *************************");
    }
}
