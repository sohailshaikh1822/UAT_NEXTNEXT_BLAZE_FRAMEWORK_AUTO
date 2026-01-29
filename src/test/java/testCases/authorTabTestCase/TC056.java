package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC056 extends BaseClass {

    @Test(dataProvider = "tc056", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyTestCaseNamefieldisupdatableinTestCaseForm(
            String epicName,
            String featureName,
            String rq_id,
            String T_id,
            String NewTcname
    ) throws InterruptedException {
        logger.info("****** Starting TC056: Verify Test Case Name field is updatable in TestCase Form ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);

            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);

            authorTestCasePage.clickTestCase(T_id);
            logger.info("Clicked on the Testcase: " + T_id);

            IndividualTestCasePage testCasePage = new IndividualTestCasePage(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            testCasePage.setTestCaseName(NewTcname);
            logger.info("Changed Test Case Name to: " + NewTcname);

            testCasePage.clickSaveButton();

            Assert.assertEquals(
                    testCasePage.getTestCaseName(),
                    NewTcname,
                    "Test Case name not updated to " + NewTcname
            );
            logger.info("Verified Test Case Name field is updated successfully to: " + NewTcname);

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
