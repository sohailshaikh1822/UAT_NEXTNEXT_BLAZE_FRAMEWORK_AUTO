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

public class TC078 extends BaseClass {

    @Test(dataProvider = "tc056", dataProviderClass =AuthorTestCaseDataProvider.class, retryAnalyzer =RetryAnalyzer.class)
    public void VerifyErrorMessageWhileCreatingNewTR(
            String epicName,
            String featureName,
            String rq_id,
            String T_id
    ) throws InterruptedException {
        logger.info("****** Starting TC056: Verify Test Case Name field is updatable in TestCase Form ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            IndividualTestCasePage individualTestCasePage=new IndividualTestCasePage(getDriver());
            ExecuteLandingPage executeLandingPage=new ExecuteLandingPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);

            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);
            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickTestCase(T_id);
            logger.info("Clicked on the Testcase: " + T_id);

           individualTestCasePage.clickCreateTestRunButton();
           logger.info("Clicked the createtestrun button");
            WaitUtils.waitFor3000Milliseconds();

           executeLandingPage.expandRelease("Release 039");
           logger.info("");
            WaitUtils.waitFor3000Milliseconds();


           logger.info("Testsuite Expanded");

           individualTestCasePage.clickSaveButton();
           WaitUtils.waitFor1000Milliseconds();
            String errorMessage=individualTestCasePage.getConfirmationMessage();
            String actualMessage="Error: Test case is not approved.";
            Assert.assertEquals(errorMessage,actualMessage,"Message has not matched");
            logger.info("Assertion has matched");


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
