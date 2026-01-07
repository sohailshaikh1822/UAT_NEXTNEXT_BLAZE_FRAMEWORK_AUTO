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

import java.util.List;

public class TC065 extends BaseClass {
    @Test(dataProvider = "tc065", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNotificationIsDisplayedwhenARequirementIsUpdated(
            String moduleName,
            String Rqtitle,
            String description,
            String priority,
            String status,
            String type,
            String priority1)
            throws InterruptedException {
        logger.info("****** Starting the TC065 *****************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

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
            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectStatus(status);
            logger.info("Selected Status: " + status);
            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectType(type);
            logger.info("Selected Type: " + type);

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("Clicked Save button");


            WaitUtils.waitFor3000Milliseconds();

            addRequirementPage.selectPriority(priority1);
            logger.info("Selected Priority: " + priority1);
            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("Clicked on Save button again after changes");

            String rqId = addRequirementPage.getRqId();
            logger.info("Captured Requirement ID: " + rqId);

            WaitUtils.waitFor9000Milliseconds();

            requirementTabPage.verifyRequirementUpdateNotification(rqId);
            logger.info("Requirement Updated notification verified successfully");



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
