package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC004 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyUpdateDescription() throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            login();
            logger.info("Login successful.");
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Navigated to Requirements tab");
            RequirementTabPage requirementsPage = new RequirementTabPage(getDriver());
            requirementsPage.clickRequirementTab();
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Clicked on the Project from left panel to open the module");
            requirementsPage.clickDropdownToSelectProject("STG- SPARK Modernization");
            logger.info("Navigate to the project");
            WaitUtils.waitFor1000Milliseconds();

            requirementsPage.clickArrowRightPointingForExpandModule("Epic j17");
            logger.info("Navigated to Module");
            WaitUtils.waitFor1000Milliseconds();

            requirementsPage.clickOnModule("feature 039");
            logger.info("clicked on specific module");
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Clicked on the Requirement ID from the requirements list");
            individualModulePage.clickRequirement("RQ-1600");
            logger.info("Successfully navigated inside the selected Requirement");
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.setDescription("Updated desc ");
            logger.info("Entered description for the requirement");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("Clicked on Save button");
            WaitUtils.waitFor1000Milliseconds();

            String actualSuccessMsg = addRequirementPage.getRequirementUpdatedSuccessMessage();
            String expectedSuccessMsg = "Requirement updated successfully.";
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Validating success message...");
            logger.info("Expected: " + expectedSuccessMsg);
            logger.info("Actual: " + actualSuccessMsg);

            Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Validation message did not match!");
            logger.info("Verified warning notification successfully");
            WaitUtils.waitFor1000Milliseconds();

        } catch (Exception e) {
            logger.error("Exception occurred during test execution: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Execution Completed ************");
    }
}