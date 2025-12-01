package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC001 extends BaseClass {

    @Test(dataProvider = "tc001", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementModuleDetails(
            String project,
            String epic,
            String feature,
            String moduleName,
            String moduleId
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
            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            logger.info("Navigated to Module");
            requirementTabPage.clickOnModule(feature);
            logger.info("clicked on specific module");
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            Assert.assertEquals(individualModulePage.getModuleId(), moduleId);
            logger.info("Module Id Verified Successfully");
            Assert.assertEquals(individualModulePage.getModuleName(), moduleName);
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
