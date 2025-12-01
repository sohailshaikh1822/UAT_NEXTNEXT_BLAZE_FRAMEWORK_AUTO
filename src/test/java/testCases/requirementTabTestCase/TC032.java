package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC032 extends BaseClass {

    @Test(dataProvider = "tc032", dataProviderClass = DataProviders.RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyModuleDeletionFlow(String projectName, String moduleName) {
        logger.info("************ Starting Test Case: Verify module deletion flow *****************");

        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded project: " + projectName);

            requirementTabPage.clickOnModule(moduleName);
            logger.info("Opened module: " + moduleName);

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
            logger.info("Verified module is deleted and requirements page is reloaded");

        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
        }

        logger.info("************ Test Case Finished *************************");

    }
}
