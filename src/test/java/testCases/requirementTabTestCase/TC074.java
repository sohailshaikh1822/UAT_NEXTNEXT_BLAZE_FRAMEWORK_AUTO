package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC074 extends BaseClass {
    @Test(dataProvider = "tc074", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_notification_is_displayed_when_a_new_module_is_created(
            String Mname
    )
            throws InterruptedException {
        logger.info("****** Starting the TC074:Verify notification is displayed when a new module is created *****************");
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

            requirementTabPage.clickEpic();
            logger.info("Clicked on Epic ");

            requirementTabPage.clickNewModule();
            logger.info("Clicked on create module");

            requirementTabPage.setModuleName(Mname);
            logger.info("Module name is set:"+Mname);

            requirementTabPage.saveModule();
            logger.info("Clicked on save button");

            WaitUtils.waitFor1000Milliseconds();

            String ModuleID = requirementTabPage.getModuleID();

            WaitUtils.waitFor9000Milliseconds();
            requirementTabPage.verifyModuleCreationNotification(ModuleID);
            logger.info("Module Creation notification verified");

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
