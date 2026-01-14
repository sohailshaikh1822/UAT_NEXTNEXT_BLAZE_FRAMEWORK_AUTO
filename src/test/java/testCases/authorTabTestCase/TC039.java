package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.LinkTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC039 extends BaseClass {

    @Test(
            dataProvider = "tc039",
            dataProviderClass = AuthorTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyPaginationReset(
            String epicName,
            String featureName,
            String rq_id,
            String tcId
    ) throws InterruptedException {

        logger.info("****** Starting TC039: Verify prevention of duplicate linking ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage =
                    new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.clickEpic();
            logger.info("Clicked Epic dropdown");
            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);
            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);
            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);
            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.clicklinktestcase();
            logger.info("Clicked Link Test Case");

            LinkTestCasePage linkTestCaseWindow =
                    new LinkTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();

            linkTestCaseWindow.searchTestCase(tcId);
            logger.info("Searched Test Case: " + tcId);
            WaitUtils.waitFor3000Milliseconds();

            linkTestCaseWindow.clickPid(tcId);
            logger.info("Clicked Test Case: " + tcId);

            String alertMsg = linkTestCaseWindow.getAlertMessage();
            logger.info("Alert message received: " + alertMsg);

            Assert.assertTrue(
                    linkTestCaseWindow.isTestCaseAlreadyLinked(),
                    "Duplicate TC link prevention alert not displayed"
            );

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }
        logger.info("************ TC039 Finished ************");
    }
}
