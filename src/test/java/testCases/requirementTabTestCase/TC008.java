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

public class TC008 extends BaseClass {

    @Test(dataProvider = "tc008", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementsCreation(String rQid, String description, String priority, String status, String type)
            throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementsPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            requirementsPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();
//            requirementTabPage.clickOnTheProjectName();

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
//            logger.info("Clicked on the Project Name");

            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");
            WaitUtils.waitFor1000Milliseconds();

//            addRequirementPage.setRequirementId(rQid);
//            logger.info("Set Requirement ID: " + rQid);

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

            String newRqIdText = addRequirementPage.getRequirementIdName();
            logger.info("Captured new Requirement ID from popup: " + newRqIdText);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickClose();
            logger.info("Closed the Add Requirement popup");
            WaitUtils.waitFor1000Milliseconds();
//            addRequirementPage.ClickYesPopup();

            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.clickLastPageArrowBtn();
            logger.info("Navigated to the last page");
            WaitUtils.waitFor1000Milliseconds();

            String expectedRqId = requirementsPage.getNewCreatedRqIdText();
            logger.info("Fetched last row Requirement ID: " + expectedRqId);
            WaitUtils.waitFor1000Milliseconds();

//            Assert.assertEquals(newRqIdText, expectedRqId,
//                    "Mismatch in newly added Requirement ID: expected '" + newRqIdText + "', but found '" + expectedRqId
//                            + "'");
//
//            logger.info("Requirement ID successfully verified: " + newRqIdText);

            if (newRqIdText == null || expectedRqId.isEmpty()) {
                Assert.fail("Requirement ID is null or empty");
            }

            if (!newRqIdText.startsWith("RQ-")) {
                Assert.fail("Requirement ID not generated properly. Found: " + newRqIdText);
            }

            logger.info("✅ Requirement created successfully with ID: " + newRqIdText);
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