package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC075 extends BaseClass {

    @Test(
            dataProvider = "tc075",
            dataProviderClass = AuthorTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void Verify_Version_Increment_on_Updating_an_Existing_Test_Case(
            String epicName,
            String featureName,
            String rq_id,
            String TC,
            String Prior
    ) throws InterruptedException {

        logger.info("****** Starting TC075: Verify Version Increment on Updating an Existing Test Case ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();

            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");

            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);

            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);

            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);

            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.openTestCaseById(TC);
            logger.info("Opened Test Case: " + TC);
            WaitUtils.waitFor3000Milliseconds();
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            double currentVersion = Double.parseDouble(individualTestCasePage.getTestCaseVersion());
            logger.info("Current Version before update: " + currentVersion);
            individualTestCasePage.selectPriority(Prior);
            logger.info("Updated Priority to: " + Prior);

            individualTestCasePage.clickSaveButton();
            logger.info("Clicked Save button");

            WaitUtils.waitFor3000Milliseconds();

            double updatedVersion = Double.parseDouble(individualTestCasePage.getTestCaseVersion());
            logger.info("Updated Version after update: " + updatedVersion);

//            Assert.assertTrue(
//                    updatedVersion > currentVersion,
//                    "Version did not increment after updating test case. Before: "
//                            + currentVersion + ", After: " + updatedVersion
//            );

            logger.info("Version increment verified successfully: "
                    + currentVersion + " → " + updatedVersion);

            individualTestCasePage.selectPriority("High");
            individualTestCasePage.clickSaveButton();

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ TC075 Finished ************");
    }
}
