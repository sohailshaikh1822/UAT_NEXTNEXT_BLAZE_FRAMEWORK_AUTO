package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC094 extends BaseClass {

    @Test(dataProvider = "tc094", dataProviderClass = DataProviders.RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyRecycleBinCountUpdatesAfterRestoringAModule(
            String Mname)
            throws InterruptedException {
        logger.info("************ Starting Test Case *****************");

        try {

            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage =
                    new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage =
                    new IndividualModulePage(getDriver());
            AddRequirementPage addRequirementPage =
                    new AddRequirementPage(getDriver());

            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickRequirementTab();
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.clickRecycleBinButton();
            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.selectObjectFromDropdown("Module");
            WaitUtils.waitFor3000Milliseconds();
            String countText = requirementTabPage.getItemCountText();
            logger.info("Initial Count Text: " + countText);

            int initialCount = requirementTabPage.extractCount(countText);

            requirementTabPage.closeRecycleBin();

            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.clickEpic();
            logger.info("Clicked on Epic ");

            requirementTabPage.clickNewModule();
            logger.info("Clicked on create module");
            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.setModuleName(Mname);
            logger.info("Module name is set");
            logger.info("Module name is set:" + Mname);
            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.saveModule();
            logger.info("module saved");

            WaitUtils.waitFor1000Milliseconds();

            String rqId = individualModulePage.getModuleId();

            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickDeleteModule();
            logger.info("deleted the module");

            requirementTabPage.clickYesBtn();
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Clicked Close button");

            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.clickRecycleBinButton();
            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.selectObjectFromDropdown("Module");
            WaitUtils.waitFor3000Milliseconds();

            String updatedText = requirementTabPage.getItemCountText();
            logger.info("Updated Count Text: " + updatedText);

            int updatedCount = requirementTabPage.extractCount(countText);
            logger.info("****** TC094 Completed Successfully ********");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}
