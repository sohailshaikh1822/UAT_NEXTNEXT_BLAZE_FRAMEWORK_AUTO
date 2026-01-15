package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC077 extends BaseClass {

    @Test(dataProvider = "tc056", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyVersionIncrementOnUpdatingTc(
            String epicName,
            String featureName,
            String rq_id,
            String T_id,
            String testname
    ) throws InterruptedException {
        logger.info("****** Starting TC056: Verify Test Case Name field is updatable in TestCase Form ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            IndividualTestCasePage individualTestCasePage=new IndividualTestCasePage(getDriver());
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

            String oldVersionText= individualTestCasePage.getVersion();

            logger.info("Current version"+ oldVersionText);

            individualTestCasePage.setTestCaseName(testname);

            individualTestCasePage.clickSaveButton();
            logger.info("Save button clicked");
            WaitUtils.waitFor3000Milliseconds();
            String newVersionText = individualTestCasePage.getVersion();

            double oldVersion = Double.parseDouble(oldVersionText);
            double newVersion = Double.parseDouble(newVersionText);
            logger.info("old"+ oldVersion);
            logger.info("new"+ newVersion);
            double expectedIncrement = 0.1;
            double actualIncrement = newVersion - oldVersion;

            double delta = 0.0001;

            Assert.assertEquals(actualIncrement, expectedIncrement, delta,
                    "Version did not increment by 0.1");

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
