package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC017 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectDropdownVisibility() throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Dropdown Visibility *****************");
        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.clickCollapseToggle();
            logger.info("Clicked collapse toggle icon");
            Assert.assertFalse(testPlanPage.getSidebarVisibility(), "Sidebar should not be visible after collapse");
            testPlanPage.clickExpandToggle();
            logger.info("Clicked expand toggle icon");
            Assert.assertTrue(testPlanPage.getSidebarVisibility(), "Sidebar should be visible after expand");

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
