package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC037 extends BaseClass {

    @Test(dataProvider = "tc037", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyTestCaseCreation(
            String epic,
            String feature,
            String requirementId
    ) throws InterruptedException {
        logger.info("****** Starting the Log in Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Clicked the Author test case");
            authorTestCasePage.selectEpic(epic);
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Selected the epic");
            authorTestCasePage.selectFeature(feature);
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Selected the feature");
            authorTestCasePage.clickRequirement(requirementId);
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Clicked on requirement id " + requirementId);
            WaitUtils.waitFor2000Milliseconds();;
            Assert.assertTrue(authorTestCasePage.isAllTestIdSorted());
            logger.info("Verified Successfully");

        } catch (Exception | AssertionError e) {
            e.printStackTrace();
            logger.error("Test case failed ...");
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
