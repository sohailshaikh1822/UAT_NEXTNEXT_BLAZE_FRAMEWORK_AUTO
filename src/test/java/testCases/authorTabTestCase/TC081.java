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

public class TC081 extends BaseClass {

    @Test(dataProvider = "tc056", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyConfirmationMessageWhileCreatingNewTR(
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
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor1000Milliseconds();
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

            individualTestCasePage.setTestCaseName("NewTc");
            individualTestCasePage.clickSaveButton();
            WaitUtils.waitFor1000Milliseconds();
            individualTestCasePage.clickApproveButton();
            WaitUtils.waitFor3000Milliseconds();
            individualTestCasePage.clickCreateTestRunButton();
            logger.info("Clicked the createTestRun button");
            WaitUtils.waitFor3000Milliseconds();

            executeLandingPage.expandRelease("Release 039");
            logger.info("");
            WaitUtils.waitFor3000Milliseconds();

//           executeLandingPage.expandSubTestCycle("New TestCycle7");
//            WaitUtils.waitFor3000Milliseconds();
//
//            executeLandingPage.expandTestSuit("New TestSuite 8");
            logger.info("Testsuite Expanded");

            individualTestCasePage.clickSaveButton();
            logger.info("Clicked Save button");
            WaitUtils.waitFor3000Milliseconds();
            String actualMessage=individualTestCasePage.getConfirmationMessage();
            String expectedMessage="Test runs saved successfully.";
            Assert.assertEquals(actualMessage,expectedMessage,"Confirmation message has not matched");
            logger.info("Assertion matched");
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
