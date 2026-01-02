package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC079 extends BaseClass {

    @Test(dataProvider = "tc056", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyApproveButtonDisabled(
            String epicName,
            String featureName,
            String rq_id,
            String T_id
    ) throws InterruptedException {
        logger.info("****** Starting TC079: Verify Test Case Name field is updatable in TestCase Form ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            IndividualTestCasePage individualTestCasePage=new IndividualTestCasePage(getDriver());
            ExecuteLandingPage executeLandingPage=new ExecuteLandingPage(getDriver());
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

            authorTestCasePage.clickTestCase(T_id);
            logger.info("Clicked on the Testcase: " + T_id);
            WaitUtils.waitFor3000Milliseconds();
          boolean abDisabled= individualTestCasePage.isApproveBtnDisplayed();
                 logger.info(""+abDisabled);
            Assert.assertFalse(abDisabled, "Approve button should be disabled but it is enabled");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC056 Finished ************");
    }
}
