package testCases.requirementTabTestCase;

import org.apache.commons.codec.binary.Base16;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC103 extends BaseClass {

    @Test(dataProvider = "tc103", dataProviderClass = DataProviders.RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNotificationIsDisplayedWhenANewSubModuleIsUpdated(
            String mdName,
            String setMd,
            String desc
    ) throws InterruptedException
    {
        logger.info("****** Starting the TC0103 *****************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickEpicDropdown();
            logger.info("Clicked on Epic drop down");

            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickOnModule(mdName);
            requirementTabPage.clickNewModule();
            logger.info("Clicked on create module");
            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.setModuleName(setMd);
            logger.info("Module name is set");

            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.saveModule();
            logger.info("Clicked on save button");
            WaitUtils.waitFor3000Milliseconds();
            IndividualModulePage individualModulePage=new IndividualModulePage(getDriver());

            String mdid = individualModulePage.getModuleId();
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.enterDescription(desc);
            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.clickSave();
            logger.info("save");

            // Validate toast appears
            WaitUtils.waitFor3000Milliseconds();
            individualModulePage.verifyUpdateNotification(mdid);
            logger.info("Updated Notification");

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