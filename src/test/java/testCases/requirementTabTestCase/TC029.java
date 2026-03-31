package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

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
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Navigated to the project");
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.clickOnModule(epic);
            logger.info("clicked on specific epic");
            WaitUtils.waitFor1000Milliseconds();
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            individualModulePage.setActualDescription(description);
            logger.info("Description is added ....");
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickOnModule(epic2);
            logger.info("Clicked on another module without saving it");
            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.ClickYesPopup();
            logger.info("Clicked yes in the alert");
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertEquals(individualModulePage.getModuleName(), epic2);
            logger.info("Verified successfully that we can navigate ");
            WaitUtils.waitFor1000Milliseconds();
            getDriver().navigate().refresh();
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickDropdownToSelectProject(project);
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickOnModule(epic);
            logger.info("Clicked on that epic again");
            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.setActualDescription(description);

            logger.info("Description is added ....");

            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickOnModule(epic2);
            logger.info("Clicked on another module without saving it");
            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.clickButtonConfirmationNoForUnsavedChanges();
            logger.info("Clicked No in the alert");
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertEquals(individualModulePage.getModuleName(), epic);
            logger.info("Verification done");

            WaitUtils.waitFor1000Milliseconds();
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
