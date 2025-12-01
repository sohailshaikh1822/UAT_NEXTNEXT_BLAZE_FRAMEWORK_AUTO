package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC008 extends BaseClass {

    @Test(dataProvider = "tc007", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementsCreation(String rQid, String description, String priority, String status, String type
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
            logger.info("Clicked on Requirement Tab");

            Thread.sleep(6000);
            requirementTabPage.clickOnTheProjectName();
            logger.info("Clicked on the Project Name");

            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");

            addRequirementPage.setRequirementId(rQid);
            logger.info("Set Requirement ID: " + rQid);
            Thread.sleep(2000);

            addRequirementPage.setDescription(description);
            logger.info("Set Description");

            addRequirementPage.selectPriority(priority);
            logger.info("Selected Priority: " + priority);
            Thread.sleep(3000);

            addRequirementPage.selectStatus(status);
            logger.info("Selected Status: " + status);
            Thread.sleep(2000);

            addRequirementPage.selectType(type);
            logger.info("Selected Type: " + type);

            addRequirementPage.clickSave();
            logger.info("Clicked Save button");

            logger.info("Requirement successfully added");
            Thread.sleep(4000);
            String newRqIdText = addRequirementPage.getRequirementIdName();
            logger.info("Captured new Requirement ID from popup: " + newRqIdText);
            Thread.sleep(1000);
            addRequirementPage.clickClose();
            logger.info("Closed the Add Requirement popup");

            Thread.sleep(3000);

            individualModulePage.clickLastPageArrowBtn();
            logger.info("Navigated to the last page");
            Thread.sleep(3000);

            String expectedRqId = requirementTabPage.getNewCreatedRqIdText();
            logger.info("Fetched last row Requirement ID: " + expectedRqId);

            Assert.assertEquals(newRqIdText, expectedRqId,
                    "Mismatch in newly added Requirement ID: expected '" + newRqIdText + "', but found '" + expectedRqId + "'");

            logger.info("Requirement ID successfully verified: " + newRqIdText);

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
