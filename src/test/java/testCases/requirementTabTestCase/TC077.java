package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC077 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNotificationIsDisplayedWhenaSubModuleisDeleted()
            throws InterruptedException {
        logger.info("****** Starting the TC077 *****************");
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

            requirementTabPage.clickOnModule("module testing 08");
            requirementTabPage.clickNewModule();
            logger.info("Clicked on create module");

            requirementTabPage.setModuleName("Module for testing");
            logger.info("Module name is set");

            requirementTabPage.saveModule();
            logger.info("Clciked on save button");

            WaitUtils.waitFor1000Milliseconds();

            String rqId = addRequirementPage.getModuleId();

            requirementTabPage.clickDeleteModule();
            logger.info("deleted the module");

            requirementTabPage.clickYesBtn();

            WaitUtils.waitFor9000Milliseconds();
            requirementTabPage.verifyDeleteNotification(rqId);
            logger.info("Delete notification verified");

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
