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

public class TC021 extends BaseClass {

    @Test(dataProvider = "tc007", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementsCountsUpdatedAfterAddNewRQ(String rQid, String description, String priority, String status, String type
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on 'Requirement' tab");

            Thread.sleep(6000);

            requirementTabPage.clickOnTheProjectName();
            logger.info("Clicked on the project name");
            WaitUtils.waitFor2000Milliseconds();

            logger.info(" Fetching the total requirement count before adding a new requirement...");
            String totalRqCountBeforeAdd = requirementTabPage.totalCountOfAvailabelRq();
            logger.info("Total requirements before add: " + totalRqCountBeforeAdd);

            individualModulePage.clickAddRequirement();
            logger.info(" Clicked on 'Add Requirement'");

            addRequirementPage.setRequirementId(rQid);
            logger.info(" Set Requirement ID: " + rQid);

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.setDescription(description);
            logger.info(" Set Description");

            addRequirementPage.selectPriority(priority);
            logger.info(" Selected Priority: " + priority);

            WaitUtils.waitFor2000Milliseconds();;

            addRequirementPage.selectStatus(status);
            logger.info("Selected Status: " + status);

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectType(type);
            logger.info(" Selected Type: " + type);

            addRequirementPage.clickSave();
            logger.info("Clicked on 'Save' button");
            logger.info(" Requirement successfully added");

            Thread.sleep(4000);

            addRequirementPage.clickClose();
            logger.info(" Clicked on 'Close' button");
            WaitUtils.waitFor2000Milliseconds();

            logger.info("🔍 Fetching the total requirement count after adding a new requirement...");
            String totalRqCountAfterAdd = requirementTabPage.totalCountOfAvailabelRq();
            logger.info("Total requirements after add: " + totalRqCountAfterAdd);

            int beforeCount = requirementTabPage.extractNumber(totalRqCountBeforeAdd);
            int afterCount = requirementTabPage.extractNumber(totalRqCountAfterAdd);

            logger.info(" Requirement count before: " + beforeCount);
            logger.info(" Requirement count after: " + afterCount);

            Assert.assertEquals(afterCount, beforeCount + 1, " Requirement count did not increase after adding a new requirement.");

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
