package testCases.testPlanTabTestCase;

import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.Arrays;
import java.util.List;

public class TC033 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyRecycleBinGridColumns() throws InterruptedException {

        logger.info("****** Starting TC033 *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();

            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Recycle Bin opened");
            WaitUtils.waitFor2000Milliseconds();

            List<String> expectedHeaders = Arrays.asList("ID","NAME","TYPE","Deleted By", "Deleted Date");
            WaitUtils.waitFor2000Milliseconds();
            List<String> actualHeaders = testPlanPage.getRecycleBinColumnHeaders();
            logger.info("Actual column headers: " + actualHeaders);
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Expected column headers: " + expectedHeaders);

            boolean isGridDataValid = testPlanPage.validateRecycleBinGridData();
            logger.info("Recycle Bin grid data validation result: " + isGridDataValid);

            logger.info("************ TC033 Executed Successfully *************************");

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
