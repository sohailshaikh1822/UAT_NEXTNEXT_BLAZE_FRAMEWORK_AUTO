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

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            logger.info("Initialized RequirementTabPage");

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            logger.info("Initialized IndividualModulePage");

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            logger.info("Initialized AddRequirementPage");

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();;
//            requirementTabPage.clickOnTheProjectName();
            requirementTabPage.clickArrowRightPointingForExpandModule("STG- PulseCodeOnAzureCloude");
            logger.info("Navigate to the project");
            requirementTabPage.clickArrowRightPointingForExpandModule("Epic 039");
            logger.info("Navigated to Module");
            requirementTabPage.clickOnModule("feature 039");
            logger.info("clicked on specific module");
//            logger.info("Clicked on the Project Name");

            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");

            addRequirementPage.setRequirementId(rQid);
            logger.info("Set Requirement ID: " + rQid);

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

            addRequirementPage.clickSave();
            logger.info("Clicked Save button");

            logger.info("Requirement successfully added");

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
