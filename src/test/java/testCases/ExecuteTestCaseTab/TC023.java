package testCases.ExecuteTestCaseTab;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.ExecuteTestCaseDataProvider;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC023 extends BaseClass {

    @Test(dataProvider = "tc023", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifySearchTestRun(String parentModule, String releaseName, String searchTestRunName)
            throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Search in Test Run table *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Navigated to Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(parentModule);
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(parentModule).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + parentModule);

            executeLandingPage.expandRelease(releaseName);
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            individualTestRun.enterSearchTerm(searchTestRunName);
            logger.info("Entered search term: " + searchTestRunName);
            individualTestRun.clickSearchButton();
            logger.info("Clicked on the Search button");

            WaitUtils.waitFor1000Milliseconds();
            logger.info("Waited for 2 seconds for search results to load");

            boolean found = individualTestRun.isTestRunVisible(searchTestRunName);
            Assert.assertTrue(found, "Search result not found for test run: " + searchTestRunName);
            logger.info("Search result found successfully for test run: " + searchTestRunName);

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
