package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC100 extends BaseClass {

    @Test(dataProvider = "tc100", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNavigationToModuleDetailsPageOnClickingSubModuleCreatedNotification(
            String mName,String epicName
    )
            throws InterruptedException {
        logger.info("****** Starting the TC100 ******");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage addRequirementPage = new IndividualModulePage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");
            WaitUtils.waitFor3000Milliseconds();;
            requirementTabPage.clickEpic();
            logger.info("Clicked on Epic ");
            requirementTabPage.clickOnModule(epicName);
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.clickNewModule();
            logger.info("Clicked on create module");
            requirementTabPage.setModuleName(mName);
            logger.info("Module name is set:"+mName);
            requirementTabPage.saveModule();
            logger.info("Clicked on save button");
            WaitUtils.waitFor1000Milliseconds();
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
