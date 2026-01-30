package testCases.testPlanTabTestCase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.WaitUtils;
import utils.RetryAnalyzer;

import java.util.List;

public class TC034 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyOnlyOneItemSelectedAtATime() throws InterruptedException {
        logger.info("****** Starting TC034 *****************");

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

            List<String> headers = testPlanPage.getRecycleBinColumnHeaders();
            logger.info("Recycle Bin headers: " + headers);

            String firstId = testPlanPage.recycleBinRows.get(0)
                    .findElements(By.tagName("td")).get(1).getText().trim();
            testPlanPage.selectRadioById(firstId);
           WaitUtils.waitFor2000Milliseconds();
            logger.info("Selected first item with ID: " + firstId);
            WaitUtils.waitFor2000Milliseconds();

            String iCount = testPlanPage.getRecycleBinItemCount();
            logger.info("Item count after selecting first item: " + iCount);


            WaitUtils.waitFor2000Milliseconds();
            String secondId = testPlanPage.recycleBinRows.get(1)
                    .findElements(By.tagName("td")).get(1).getText().trim();
            testPlanPage.selectRadioById(secondId);
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Selected second item with ID: " + secondId);

            String itemCount = testPlanPage.getRecycleBinItemCount();
            logger.info("Item count after selecting second item: " + itemCount);

            logger.info("************ TC034 Executed Successfully *************************");

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
