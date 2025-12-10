package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;


public class TC047 extends BaseClass {

    @Test(dataProvider = "tc047", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verify_filter_by_status_feature(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String Epic,
            String Feature,
            String rq,
            String Tc_ID
    ) throws InterruptedException {

        logger.info("****** Starting Test Case 047: verify filter by status feature. *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded Project: " + projectName);

            executeLandingPage.expandRelease(ReleaseName);
            logger.info("Expanded Release: " + ReleaseName);

            executeLandingPage.expandSubTestCycle(CycleName);
            logger.info("Expanded Cycle: " + CycleName);

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);
            WaitUtils.waitFor1000Milliseconds();;

            int initialCount = executeLandingPage.getTotalEntriesCount();
            logger.info("Initial Entries Count: " + initialCount);

            executeLandingPage.clickCreateTestRunButton();
            logger.info("Clicked on Create Test Run Button");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            authorTestCasePage.selectEpic(Epic);
            logger.info("Epic selected from dropdown:" + Epic);

            authorTestCasePage.selectFeature(Feature);
            logger.info("Feature selected from dropdown:" + Feature);

            authorTestCasePage.clickRequirement(rq);
            logger.info("Requirement selected :" + rq);

            executeLandingPage.selectTestCaseCheckbox(Tc_ID);
            logger.info("Selected Test Case ID:" + Tc_ID);

            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked on Save button ");

            WaitUtils.waitFor1000Milliseconds();;

            int updatedCount = executeLandingPage.getTotalEntriesCount();
            logger.info("Updated Entries Count: " + updatedCount);

            Assert.assertTrue(updatedCount > initialCount,
                    "Entry count did not increase after adding test case! Expected > " + initialCount + ", but found " + updatedCount);

            logger.info("Verified that entry count increased successfully.");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }

}
