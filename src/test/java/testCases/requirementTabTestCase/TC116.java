package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC116 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDeletedModuleNotificationIsNotClickable()
            throws InterruptedException {
        logger.info("****** Starting the TestCase *****************");
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
            logger.info("Clicked on save button");

            WaitUtils.waitFor1000Milliseconds();

            String rqId = individualModulePage.getModuleId();

            requirementTabPage.verifyModuleCreationNotification(rqId);
            logger.info("Module creation notification verified");

            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickDeleteModule();
            logger.info("deleted the module");

            requirementTabPage.clickYesBtn();
            logger.info("deleted the module");
            WaitUtils.waitFor1000Milliseconds();
            NotificationsListener notificationsListener=new NotificationsListener(getDriver());
            notificationsListener.verifyDeletedModuleNotificationNotClickable(rqId);
            logger.info("Tooltip Verified");
            WaitUtils.waitFor1000Milliseconds();

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
