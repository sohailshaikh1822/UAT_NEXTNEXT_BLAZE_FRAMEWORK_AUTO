package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC092 extends BaseClass {


    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyANotificationIsDisplayedInNotificationPanelWhenDeletedModuleIsRestored()
            throws InterruptedException {
        logger.info("****** Starting the TC092 *****************");
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
            logger.info("Clicked on save button");
            WaitUtils.waitFor1000Milliseconds();
            String mdId = individualModulePage.getModuleId();
            individualModulePage.getSuccessNotificationMessage();
            logger.info("Module created successfully");
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickDeleteModule();
            requirementTabPage.clickYesBtn();
            logger.info("Module deleted successfully");
            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectObjectDropdownValue("Module");
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.smoothScrollRecycleBin();
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.selectRadioById(mdId);
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.clickRestoreButton();
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.clickCloseButtonOfRecycleBinPage();
            NotificationsListener notificationsListener = new NotificationsListener(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor3000Milliseconds();
            String actual=notificationsListener.getRestoredNotificationText(mdId);
            String expected="'"+mdId+"'"+" is restored by Julie Kumari.";

            Assert.assertEquals(actual,expected,"Notification Not Matched");

        }
        catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }
        logger.info("************ Test Case Finished *************************");

    }
}
