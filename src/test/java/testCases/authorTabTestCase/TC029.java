package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC029 extends BaseClass {

    @Test(dataProvider = "tc029", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNextButtonFunctionality(
            String expectedPagination
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            new RequirementTabPage(getDriver()).clickRequirementTab();
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            authorTestCasePage.clickNextArrow();
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickFeature();
            logger.info("Clicked on forward arrow in the requirement");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickRequirementPagination();
            logger.info(authorTestCasePage.showPaginationOfRequirement());
            logger.info("Expected pagination verified ....");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
