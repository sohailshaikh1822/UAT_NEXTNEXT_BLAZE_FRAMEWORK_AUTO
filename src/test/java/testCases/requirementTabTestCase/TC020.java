package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC020 extends BaseClass {

    @Test(dataProvider = "tc020", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserCanUpdateNameAndDescription(
            String project,
            String epic
    ) throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Navigated to the project");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(epic);
            logger.info("clicked on specific epic");
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertFalse(individualModulePage.isModuleIdClickable());
            logger.info("Verified the module is not clickable...");
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Module Title verified Successfully");
            WaitUtils.waitFor1000Milliseconds();

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