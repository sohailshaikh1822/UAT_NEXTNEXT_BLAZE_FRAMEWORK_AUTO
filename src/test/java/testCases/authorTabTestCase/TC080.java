package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC080 extends BaseClass {

    @Test(dataProvider = "tc056", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyConfirmationMessageWhileApproveTCVersion(
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

            individualTestCasePage.selectPriority("New");
            logger.info("Select priority: " + T_id);

            individualTestCasePage.clickSaveButton();
            logger.info("Save button clicked");
            individualTestCasePage.clickApproveButton();
            String confirmationMessage=individualTestCasePage.getConfirmationMessage();
            String actualMessage="Test Case approved successfully.";
            Assert.assertEquals(confirmationMessage,actualMessage,"Message has not matched");
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
