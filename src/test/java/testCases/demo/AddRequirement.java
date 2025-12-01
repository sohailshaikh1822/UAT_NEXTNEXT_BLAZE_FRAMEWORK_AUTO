package testCases.demo;

import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class AddRequirement extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementFlow() throws InterruptedException {
        logger.info("****** Starting the Filter Requirement Test Case *****************");
        try {
            login();
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            requirementTabPage.clickOnTheProjectName();
            requirementTabPage.clickNewModule();
            requirementTabPage.setModuleName("Epic Mayukhjit");
            requirementTabPage.saveModule();
            requirementTabPage.clickNewModule();
            requirementTabPage.setModuleName("Feature Mohit");
            requirementTabPage.saveModule();
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            individualModulePage.clickAddRequirement();
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            addRequirementPage.setRequirementId("RQ-114");
            addRequirementPage.setDescription("Hello new requirement");
            addRequirementPage.clickSave();

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
