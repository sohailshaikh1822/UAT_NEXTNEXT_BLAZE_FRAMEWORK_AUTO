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

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());

            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Selected project: " + project);

            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            requirementTabPage.clickOnModule(epic);
            logger.info("Selected epic: " + epic);

            int countBefore = individualModulePage.getRequirementCountFromFooter();
            logger.info("Requirement count before adding: " + countBefore);

            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");

            WaitUtils.waitFor2000Milliseconds();

            // ❗ Name is intentionally NOT set
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

            addRequirementPage.clickSave();
            logger.info("Clicked Save button");

            WaitUtils.waitFor2000Milliseconds();
            addRequirementPage.clickClose();
            logger.info("Clicked Close button");

            WaitUtils.waitFor2000Milliseconds();
            int countAfter = individualModulePage.getRequirementCountFromFooter();
            logger.info("Requirement count after adding: " + countAfter);

            Assert.assertEquals(
                    countAfter,
                    countBefore + 1,
                    "Requirement count should increase after saving without name"
            );

            logger.info("Verified requirement saved successfully without name");

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
