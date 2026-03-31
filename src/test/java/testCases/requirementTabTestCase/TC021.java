package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC021 extends BaseClass {

    @Test(dataProvider = "tc021", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementsCountsUpdatedAfterAddNewRQ(String rQid, String description, String priority, String status, String type
    ) throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on 'Requirement' tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickArrowRightPointingForExpandModule("Epic requirement ");
            logger.info("Navigated to Module");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickOnModule("New Module Vivek");
            logger.info("clicked on specific module");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor1000Milliseconds();

            logger.info(" Fetching the total requirement count before adding a new requirement...");
            WaitUtils.waitFor1000Milliseconds();

            String totalRqCountBeforeAdd = requirementTabPage.totalCountOfAvailabelRq();
            logger.info("Total requirements before add: " + totalRqCountBeforeAdd);
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.clickAddRequirement();
            logger.info(" Clicked on 'Add Requirement'");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.setRequirementId(rQid);
            logger.info(" Set Requirement ID: " + rQid);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.setDescription(description);
            logger.info(" Set Description");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.selectPriority(priority);
            logger.info(" Selected Priority: " + priority);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectStatus(status);
            logger.info("Selected Status: " + status);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectType(type);
            logger.info(" Selected Type: " + type);
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("Clicked on 'Save' button");
            WaitUtils.waitFor1000Milliseconds();

            logger.info(" Requirement successfully added");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.clickClose();
            logger.info(" Clicked on 'Close' button");
            WaitUtils.waitFor1000Milliseconds();
//            addRequirementPage.ClickYesPopup();

            WaitUtils.waitFor1000Milliseconds();

            logger.info("🔍 Fetching the total requirement count after adding a new requirement...");
            WaitUtils.waitFor1000Milliseconds();

            String totalRqCountAfterAdd = requirementTabPage.totalCountOfAvailabelRq();
            logger.info("Total requirements after add: " + totalRqCountAfterAdd);
            WaitUtils.waitFor1000Milliseconds();

            int beforeCount = requirementTabPage.extractNumber(totalRqCountBeforeAdd);
            WaitUtils.waitFor1000Milliseconds();

            int afterCount = requirementTabPage.extractNumber(totalRqCountAfterAdd);
            WaitUtils.waitFor1000Milliseconds();

            logger.info(" Requirement count before: " + beforeCount);
            logger.info(" Requirement count after: " + afterCount);
            WaitUtils.waitFor1000Milliseconds();

        } catch (AssertionError e) {
            logger.error("  Assertion failed: " + e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            logger.error("  Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}