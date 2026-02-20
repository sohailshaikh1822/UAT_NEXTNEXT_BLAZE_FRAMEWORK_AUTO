package testCases.requirementTabTestCase;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC095 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyRecycleBinCountUpdatesAfterRestoringAModule()
            throws InterruptedException
    {
        logger.info("************ Starting Test Case *****************");

        try {

            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage=new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.clickRecycleBinButton();
            WaitUtils.waitFor2000Milliseconds();

            logger.info("Recycle Bin window opened successfully");

            WebElement restoreBtn = requirementTabPage.getRestoreButton();

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertFalse(
                    restoreBtn.isEnabled(),
                    "Restore button should be disabled when no item is selected"
            );

            logger.info("Restore button is disabled when no item is selected");
            requirementTabPage.selectFirstDeletedItem();
            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(
                    restoreBtn.isEnabled(),
                    "Restore button should be enabled after selecting an item"
            );

            logger.info("Restore button becomes enabled after selecting an item");

            restoreBtn.click();
            logger.info("Restore button clicked successfully");

            logger.info("****** TC095 Completed Successfully ********");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}