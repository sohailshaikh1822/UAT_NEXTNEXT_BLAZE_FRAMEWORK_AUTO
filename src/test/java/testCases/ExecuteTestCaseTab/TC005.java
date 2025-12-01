package testCases.ExecuteTestCaseTab;

import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC005 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyExpandAndCollapseLeftPenal() throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Expand feature of sub test cycle *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");
            executeLandingPage.clickHamburgerMenu();
            logger.info("Successfully Clicked the hamburger Btn");

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
