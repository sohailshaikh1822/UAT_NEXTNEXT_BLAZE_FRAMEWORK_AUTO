package testCases.testPlanTabTestCase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC048 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyRestoreButtonDisabledWhenNoItemSelected() throws InterruptedException {

        logger.info("****** Starting TC048 *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();

            logger.info("No item selected in Recycle Bin");
            logger.info("Restore button enabled state before selecting any item: " + testPlanPage.restoreButton.isEnabled());

            String itemId = testPlanPage.recycleBinRows.get(0).findElements(By.tagName("td")).get(1).getText().trim();
            testPlanPage.selectRadioById(itemId);
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Selected item with ID: " + itemId);

            logger.info("Restore button enabled state after selecting any iteam is selected: " + testPlanPage.restoreButton.isEnabled());

            testPlanPage.clickRestoreButton();
            logger.info("Clicked on Restore button");

            logger.info("************ TC048 Executed Successfully *************************");

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
