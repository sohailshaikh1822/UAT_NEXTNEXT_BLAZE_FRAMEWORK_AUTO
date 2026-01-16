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
            requirementTabPage.clickEpic();
            logger.info("Navigate to the project");
            requirementTabPage.clickArrowRightPointingForExpandModule("Epic 039");
            logger.info("Navigated to Module");
            requirementTabPage.clickOnModule("feature 039");
            logger.info("clicked on specific module");
            requirementTabPage.clickNewModule();
            requirementTabPage.setModuleName(uniqueModuleName);
            requirementTabPage.saveModule();
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");
            requirementTabPage.clickEpic();
            requirementTabPage.clickArrowRightPointingForExpandModule("Epic 039");
            logger.info("Navigated to Module");
            requirementTabPage.clickArrowRightPointingForExpandModule("feature 039");
            requirementTabPage.clickOnModule(uniqueModuleName);
            logger.info("Opened module: " + uniqueModuleName);
            individualModulePage.clickDeleteModuleIcon();
            logger.info("Clicked on delete icon");
            String expectedMsg = "Deleting a module will also delete its associated requirements and test cases. Are you sure you want to delete?";
            String actualMsg = individualModulePage.getDeleteConfirmationMessage();
            Assert.assertEquals(actualMsg, expectedMsg, "Delete confirmation message mismatch!");
            logger.info("Verified confirmation popup message");
            individualModulePage.confirmDelete();
            logger.info("Clicked YES on confirmation popup");
            boolean isPageReloaded = addRequirementPage.isModulePageReopened();
            Assert.assertTrue(isPageReloaded, "Requirements page not reloaded after deletion!");
            logger.info("Verified requirements page is reloaded after deletion");
            String actualSuccessMsg = individualModulePage.getSuccessNotificationMessage();
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
