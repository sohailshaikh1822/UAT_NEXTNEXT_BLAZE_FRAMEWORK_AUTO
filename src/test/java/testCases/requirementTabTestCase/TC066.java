package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC066 extends BaseClass {
    @Test(dataProvider = "tc066", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNotificationisGeneratedwhenTestCasesareAddedToARequirement(
            String moduleName,
            String Rqtitle,
            String description)
            throws InterruptedException {
        logger.info("****** Starting the TC066 *****************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AddTestcasePage addTestcasePage=new AddTestcasePage(getDriver());
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
            addRequirementPage.clickSave();
            logger.info("Clicked on Save button again after changes");


            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked 'Add Test Case' button.");
            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.setTestCaseName(moduleName);
            logger.info("Test case name set to: " + moduleName);
            WaitUtils.waitFor1000Milliseconds();

            addTestcasePage.clickSave();
            logger.info("New test case saved successfully.");

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
