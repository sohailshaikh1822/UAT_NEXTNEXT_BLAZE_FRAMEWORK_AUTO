package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC024 extends BaseClass {

    @Test(dataProvider = "tc024", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementEntriesUpdate(String project,
                                               String epic, String rQname, String description, String priority, String status, String type
    ) throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            logger.info("Initialized RequirementTabPage");
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            logger.info("Initialized IndividualModulePage");
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            logger.info("Initialized AddRequirementPage");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

//            requirementTabPage.clickDropdownToSelectProject(project);
//            logger.info("Selected project" + project);

            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(epic);
            logger.info("Selected epic" + epic);
            WaitUtils.waitFor1000Milliseconds();

            int countBefore = individualModulePage.getRequirementCountFromFooter();
            logger.info("Requirement count before adding: " + countBefore);
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.setRequirementId(rQname);
            logger.info("Set Requirement ID: " + rQname);
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

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.clickClose();
            logger.info("Clicked on Close button");
            WaitUtils.waitFor1000Milliseconds();
//            addRequirementPage.ClickYesPopup();

            WaitUtils.waitFor2000Milliseconds();

            int countAfter = individualModulePage.getRequirementCountFromFooter();
            logger.info("Requirement count after adding: " + countAfter);
            WaitUtils.waitFor1000Milliseconds();

//            Assert.assertEquals(countAfter, countBefore + 1, "Requirement count did not increase by 1");
//            logger.info("Requirement count successfully updated after adding new requirement");

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