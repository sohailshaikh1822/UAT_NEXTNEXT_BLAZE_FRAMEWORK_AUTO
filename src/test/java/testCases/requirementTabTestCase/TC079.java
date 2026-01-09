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

public class TC079 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyModuleCreation()
            throws InterruptedException {
        logger.info("****** Starting the TC077 *****************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            requirementTabPage.clickRequirementTab();

            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();;

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickEpic();
            logger.info("Clicked on Epic ");

            requirementTabPage.clickNewModule();
            logger.info("Clicked on create module");
            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.setModuleName("module creation");
            logger.info("Module name is set");

            individualModulePage.clickSave();
            logger.info("Clciked on save button");

            WaitUtils.waitFor1000Milliseconds();

            String rqId = individualModulePage.getModuleId();
            individualModulePage.getSuccessNotificationMessage();
            logger.info("Module created successfully");

            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickDeleteModule();

            requirementTabPage.clickYesBtn();

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
