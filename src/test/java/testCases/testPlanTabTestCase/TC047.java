package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC047 extends BaseClass {

    @Test(dataProvider = "tc047", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verify_Recycle_Bin_count_updates_after_restoring_a_TestSuite(String projectName, String releaseName) throws InterruptedException {
        logger.info("****** Starting TC047 *****************");

        try {
            login();
            logger.info("Logged in successfully");
            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");
            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");
            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnRecycleBinIcon();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.selectObjectDropdownValue("TestSuite");

            WaitUtils.waitFor3000Milliseconds();
            String ActualCount= testPlanPage.getRecycleBinItemCount();
            int count = Integer.parseInt(ActualCount.trim().split("\\s+")[0]);
            logger.info("Actual Count " + count);
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.selectFirstRadioBtn();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickRestoreButton();
            WaitUtils.waitFor1000Milliseconds();
            String UpdatedCount=testPlanPage.getRecycleBinItemCount();
            int updatedCount = Integer.parseInt(UpdatedCount.trim().split("\\s+")[0]);

            logger.info("Updted count "+ updatedCount);
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertEquals(updatedCount, count - 1);


        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
