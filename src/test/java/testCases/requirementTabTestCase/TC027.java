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

public class TC027 extends BaseClass {

    @Test(
            dataProvider = "tc027",
            dataProviderClass = RequirementDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyRequirementSavedWithoutName(
            String project,
            String epic,
            String description,
            String priority,
            String status,
            String type
    ) throws InterruptedException {

        logger.info("************ TC027 Started: Verify requirement can be saved without name ************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Selected project: " + project);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(epic);
            logger.info("Selected epic: " + epic);
            WaitUtils.waitFor1000Milliseconds();

            int countBefore = individualModulePage.getRequirementCountFromFooter();
            logger.info("Requirement count before adding: " + countBefore);
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            // ❗ Name is intentionally NOT set
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

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.clickClose();
            logger.info("Clicked Close button");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            int countAfter = individualModulePage.getRequirementCountFromFooter();
            logger.info("Requirement count after adding: " + countAfter);
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertEquals(
                    countAfter,
                    countBefore + 1,
                    "Requirement count should increase after saving without name"
            );
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Verified requirement saved successfully without name");
            WaitUtils.waitFor1000Milliseconds();

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ TC027 Finished ************");
    }
}