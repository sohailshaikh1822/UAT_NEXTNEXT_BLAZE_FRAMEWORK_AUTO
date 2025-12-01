package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.LinkTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC039 extends BaseClass {

    @Test(dataProvider = "tc039", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
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

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");

            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);

            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);

            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);

            authorTestCasePage.clicklinktestcase();

            LinkTestCasePage linkTestCasewindow = new LinkTestCasePage(getDriver());

            linkTestCasewindow.searchTestCase(tcId);
            logger.info("Searched Test Case with ID......: " + tcId);

            linkTestCasewindow.clickPid(tcId);
            logger.info("select Test case" + tcId);

            String alertMsg = linkTestCasewindow.getAlertMessage();
            logger.info("Alert message: " + alertMsg);
            Assert.assertTrue(linkTestCasewindow.isTestCaseAlreadyLinked(),
                    "Expected alert not shown after linking TC");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ TC039 Finished ************");
    }
}
