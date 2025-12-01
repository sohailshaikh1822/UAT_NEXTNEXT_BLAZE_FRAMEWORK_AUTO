package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC029 extends BaseClass {

    @Test(dataProvider = "tc029", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserCanUpdateNameAndDescription(
            String project,
            String epic,
            String description,
            String epic2
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            requirementTabPage.clickArrowRightPointingForExpandModule(project);
            logger.info("Navigated to the project");
            requirementTabPage.clickOnModule(epic);
            logger.info("clicked on specific epic");
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            individualModulePage.setActualDescription(description);
            logger.info("Description is added ....");
            requirementTabPage.clickOnModule(epic2);
            logger.info("Clicked on another module without saving it");
            individualModulePage.clickButtonConfirmationYesForUnsavedChanges();
            logger.info("Clicked yes in the alert");

            Assert.assertEquals(individualModulePage.getModuleName(), epic2);
            logger.info("Verified successfully that we can navigate ");
            getDriver().navigate().refresh();
            requirementTabPage.clickArrowRightPointingForExpandModule(project);
            requirementTabPage.clickOnModule(epic);
            logger.info("Clicked on that epic again");
            individualModulePage.setActualDescription(description);

            logger.info("Description is added ....");
            requirementTabPage.clickOnModule(epic2);
            logger.info("Clicked on another module without saving it");
            individualModulePage.clickButtonConfirmationNoForUnsavedChanges();
            logger.info("Clicked No in the alert");
            Assert.assertEquals(individualModulePage.getModuleName(), epic);
            logger.info("Verification done");

            logger.info("Module Title verified Successfully");
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
