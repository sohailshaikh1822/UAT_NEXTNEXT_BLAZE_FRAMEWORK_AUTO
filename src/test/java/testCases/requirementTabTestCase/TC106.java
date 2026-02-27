package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC106 extends BaseClass {
    @Test(dataProvider = "tc106", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNavigationWorksCorrectlyOrForMixedNotifications(
            String epic,
            String description,
            String name,
            String name1
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage=new AddRequirementPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickOnModule(epic);
            logger.info("clicked on specific epic");
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            individualModulePage.setActualDescription(description);
            logger.info("entered the description");
            individualModulePage.enterName(name);
            logger.info("added the name");
            individualModulePage.clickSave();
            logger.info("Clicked the save button");
            individualModulePage.enterName(name1);
            logger.info("added the name");
            individualModulePage.clickSave();
            WaitUtils.waitFor3000Milliseconds();
            String ModuleID = individualModulePage.getModuleId();

            individualModulePage.clickAddRequirement();
            WaitUtils.waitFor2000Milliseconds();
            addRequirementPage.clickSave();
            WaitUtils.waitFor1000Milliseconds();
            addRequirementPage.selectType("Functional");
            WaitUtils.waitFor1000Milliseconds();
            addRequirementPage.clickSave();
            String RqID = addRequirementPage.getRqId();
            NotificationsListener notificationsListener = new NotificationsListener(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor3000Milliseconds();
            notificationsListener.clickUpdatedNotification(ModuleID);
            logger.info("ModuleId "+ ModuleID);
            WaitUtils.waitFor3000Milliseconds();
            notificationsListener.clickNotificationIcon();
            notificationsListener.clickUpdatedNotification(RqID);
            logger.info("RqId "+RqID);

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
