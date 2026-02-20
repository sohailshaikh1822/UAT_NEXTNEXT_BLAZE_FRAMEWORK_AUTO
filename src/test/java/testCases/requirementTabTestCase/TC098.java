package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC098 extends BaseClass {
    @Test(dataProvider = "tc098", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_notification_navigation_when_a_new_module_is_created(
            String Mname,String rqname
    )
            throws InterruptedException {
        logger.info("****** Starting the testcases ******");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());

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

            WaitUtils.waitFor2000Milliseconds();

            String ModuleID = requirementTabPage.getModuleID();
            NotificationsListener notificationsListener = new NotificationsListener(getDriver());

            WaitUtils.waitFor3000Milliseconds();

            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor3000Milliseconds();
            notificationsListener.clickCreatedNotification(ModuleID);


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
