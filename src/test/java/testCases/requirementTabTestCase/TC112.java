package testCases.requirementTabTestCase;

import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC112 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyBothCreationAndDeletionNotificationsAreDisabledAfterModuleDeletion()
            throws InterruptedException {
        logger.info("****** Starting the TC112 ******");
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

            requirementTabPage.setModuleName("module created");
            logger.info("Module name is set");

            requirementTabPage.saveModule();
            logger.info("Clciked on save button");

            WaitUtils.waitFor1000Milliseconds();

            String mdId = individualModulePage.getModuleId();


            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickDeleteModule();
            logger.info("deleted the module");

            requirementTabPage.clickYesBtn();

            NotificationsListener notificationsListener = new NotificationsListener(getDriver());

            WaitUtils.waitFor3000Milliseconds();

            notificationsListener.clickNotificationIcon();

            notificationsListener.verifyDeletedModuleNotificationNotClickable(mdId);
            WaitUtils.waitFor2000Milliseconds();

            notificationsListener.clickCreatedNotification(mdId);



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
