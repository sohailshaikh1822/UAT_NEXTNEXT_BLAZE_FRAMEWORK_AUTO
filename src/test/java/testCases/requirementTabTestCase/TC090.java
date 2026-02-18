package testCases.requirementTabTestCase;

import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC090 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyCancelButtonClosesRecycleBin() throws InterruptedException {
        logger.info("****** Starting TC081 ********");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage =
                    new RequirementTabPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement tab");
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.clickRecycleBinButton();
            logger.info("Clicked on Recycle Bin button");
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.verifyRecycleBinPageOpened();
            logger.info("Recycle Bin page opened successfully");
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.verifyHeaderSectionDisplayed();
            logger.info("Header section");
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.verifyItemCounterDisplayed();
            logger.info("Item counter");
            logger.info("****** TC081 Completed Successfully ********");
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
