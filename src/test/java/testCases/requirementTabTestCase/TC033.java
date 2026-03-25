package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC033 extends BaseClass {

    @Test(dataProvider = "tc033", dataProviderClass = DataProviders.RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyModuleDeletionWithSuccessMessage(
            String projectName,
            String moduleName)
            throws InterruptedException {
        logger.info("************ Starting Test Case: Verify module deletion with success message *****************");

        try {

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());

            String uniqueModuleName = moduleName + "_" + System.currentTimeMillis();
            logger.info("Unique module name generated: " + uniqueModuleName);
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();
//            requirementTabPage.clickEpic();
            logger.info("Navigate to the project");
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickArrowRightPointingForExpandModule("Epic 039");
            logger.info("Navigated to Module");
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickOnModule("feature 039");
            WaitUtils.waitFor1000Milliseconds();
            logger.info("clicked on specific module");
            requirementTabPage.clickNewModule();
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.setModuleName(uniqueModuleName);
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.saveModule();
            WaitUtils.waitFor1000Milliseconds();
            WaitUtils.waitFor1000Milliseconds();
//            requirementTabPage.clickArrowRightPointingForExpandModule("Epic 039");
//            logger.info("Navigated to Module");
//            WaitUtils.waitFor1000Milliseconds();
//            requirementTabPage.clickOnModule("feature 039");
//            WaitUtils.waitFor1000Milliseconds();
//            requirementTabPage.clickOnModule(uniqueModuleName);
//            logger.info("Opened module: " + uniqueModuleName);
//            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.clickDeleteModuleIcon();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Clicked on delete icon");
            String expectedMsg = "Deleting a module will also delete its associated requirements and test cases. Are you sure you want to delete?";
            String actualMsg = individualModulePage.getDeleteConfirmationMessage();
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertEquals(actualMsg, expectedMsg, "Delete confirmation message mismatch!");
            logger.info("Verified confirmation popup message");
            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.confirmDelete();
            logger.info("Clicked YES on confirmation popup");
            WaitUtils.waitFor1000Milliseconds();
            boolean isPageReloaded = addRequirementPage.isModulePageReopened();
            Assert.assertTrue(isPageReloaded, "Requirements page not reloaded after deletion!");
            logger.info("Verified requirements page is reloaded after deletion");
            WaitUtils.waitFor1000Milliseconds();
            String actualSuccessMsg = individualModulePage.getSuccessNotificationMessage();
            WaitUtils.waitFor1000Milliseconds();
            String expectedSuccessMsg = "Module deleted successfully.";
            Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Success message mismatch!");
            logger.info("Verified success notification: " + actualSuccessMsg);

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}
