package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC002 extends BaseClass {

    @Test(dataProvider = "tc002", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectSelectionFromDropdown(String projectName) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");
        try {
            login();
            logger.info("Logged in successfully");
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            executeLandingPage.clickExecuteTab();
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");

            Assert.assertTrue(executeLandingPage.isMentionedProjectNameVisible(projectName));
            logger.info("Verified Successfully");

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
