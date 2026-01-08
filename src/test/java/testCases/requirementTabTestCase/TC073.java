package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC073 extends BaseClass {
    @Test(dataProvider = "tc073", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_notification_is_displayed_when_a_requirement_is_deleted(
            String moduleName,
            String Rqtitle,
            String description,
            String priority,
            String status,
            String type)
            throws InterruptedException {
        logger.info("****** Starting the TC073: Verify notification is displayed when a requirement is deleted *****************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();;

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickEpicDropdown();
            logger.info("Clicked on Epic drop down");

            requirementTabPage.clickOnModule(moduleName);
            logger.info("Opened module: " + moduleName);


            WaitUtils.waitFor3000Milliseconds();
            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");

            WaitUtils.waitFor3000Milliseconds();
            addRequirementPage.setRequirementId(Rqtitle);
            logger.info("Enter Rqtitle:"+Rqtitle);

            WaitUtils.waitFor2000Milliseconds();
            addRequirementPage.setDescription(description);
            logger.info("Set Description");

            addRequirementPage.selectPriority(priority);
            logger.info("Selected Priority: " + priority);
            WaitUtils.waitFor2000Milliseconds();;

            addRequirementPage.selectStatus(status);
            logger.info("Selected Status: " + status);
            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectType(type);
            logger.info("Selected Type: " + type);

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("Clicked Save button");

            WaitUtils.waitFor3000Milliseconds();

            String rqId = addRequirementPage.getRqId();
            logger.info("Captured Requirement ID: " + rqId);
            WaitUtils.waitFor3000Milliseconds();

            addRequirementPage.clickClose();
            logger.info("Clicked Close button");

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.DeleteRequirementById(rqId);

            WaitUtils.waitFor9000Milliseconds();

            requirementTabPage.verifyDeleteNotification(rqId);
            logger.info("Requirement deletion notification verified successfully");



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
