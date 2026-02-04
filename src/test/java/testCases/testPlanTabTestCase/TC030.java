package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.Arrays;
import java.util.List;

public class TC030 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyUserCanOpenRecycleBinFromTestplan()
            throws InterruptedException {
        logger.info("****** Starting TC030 *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualTestSuitePage individualTestSuitePage = new IndividualTestSuitePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");

            testPlanPage.clickOnObjectDropdown();
            logger.info("Clicked on Object(s) dropdown");

            List<String> actualValues = testPlanPage.getObjectDropdownValues();
            List<String> expectedValues = Arrays.asList("All", "TestSuite", "TestCycle", "Release");

            logger.info("Actual dropdown values: " + actualValues);

            logger.info("************ TC030 Executed Successfully *************************");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
