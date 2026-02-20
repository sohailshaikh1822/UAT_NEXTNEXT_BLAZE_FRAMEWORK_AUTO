package testCases.requirementTabTestCase;

import org.apache.commons.codec.binary.Base16;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC101 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyUpdatingAnExistingSubModule()
            throws InterruptedException {
        logger.info("****** Starting the TC0101 *****************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();;

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickEpicDropdown();
            logger.info("Clicked on Epic drop down");

            requirementTabPage.clickOnModule("SET- DRIV");
            requirementTabPage.clickNewModule();
            logger.info("Clicked on create module");

            requirementTabPage.setModuleName("Module20");
            logger.info("Module name is set");

            requirementTabPage.saveModule();
            logger.info("Clicked on save button");

            WaitUtils.waitFor1000Milliseconds();
            IndividualModulePage individualModulePage=new IndividualModulePage(getDriver());

            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.enterDescription("module");
            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.clickSave();
            logger.info("save");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");

    }
}