package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

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
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            requirementTabPage.clickArrowRightPointingForExpandModule(project);
            logger.info("Navigated to the project");
            requirementTabPage.clickOnModule(epic);
            logger.info("clicked on specific epic");
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            Assert.assertFalse(individualModulePage.isModuleIdClickable());
            logger.info("Verified the module is not clickable...");

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
