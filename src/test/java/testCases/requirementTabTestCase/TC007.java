package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC007 extends BaseClass {

    @Test(dataProvider = "tc007", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyAddRequirements(String rQid, String description, String priority, String status, String type
    ) throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementsPage = new RequirementTabPage(getDriver());
            logger.info("Initialized RequirementTabPage");
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            logger.info("Initialized IndividualModulePage");
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            logger.info("Initialized AddRequirementPage");
            WaitUtils.waitFor1000Milliseconds();

            requirementsPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();
//            requirementTabPage.clickOnTheProjectName();
//            logger.info("Clicked on the Project Name");

            logger.info("Clicked on the Project from left panel to open the module");
            requirementsPage.clickDropdownToSelectProject("STG- SPARK Modernization");
            logger.info("Navigate to the project");
            WaitUtils.waitFor1000Milliseconds();

            requirementsPage.clickArrowRightPointingForExpandModule("Epic j17");
            logger.info("Navigated to Module");
            WaitUtils.waitFor1000Milliseconds();

            requirementsPage.clickOnModule("feature 039");
            logger.info("clicked on specific module");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.setRequirementId(rQid);
            logger.info("Set Requirement ID: " + rQid);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.setDescription(description);
            logger.info("Set Description");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.selectPriority(priority);
            logger.info("Selected Priority: " + priority);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectStatus(status);
            logger.info("Selected Status: " + status);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectType(type);
            logger.info("Selected Type: " + type);
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("Clicked Save button");
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Requirement successfully added");
            WaitUtils.waitFor1000Milliseconds();

        } catch (AssertionError e) {
            logger.error("❌ Assertion failed: " + e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            logger.error("❌ Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}