package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC019 extends BaseClass {

    @Test(dataProvider = "tc019", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserCanUpdateNameAndDescription(
            String epic,
            String description,
            String name,
            String name1
    ) throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();


            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Navigate to the project");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            logger.info("Navigated to Module");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickOnModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickNewModule();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("clicked on create new module");
            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.enterName(epic);
            logger.info("entered the name of the module");
            WaitUtils.waitFor1000Milliseconds();
            individualModulePage.clickSave();
            logger.info("module saved successfully");
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.setActualDescription(description);
            logger.info("entered the description");
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.enterName(name);
            logger.info("added the name");
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.clickSave();
            logger.info("Clicked the save button");
            WaitUtils.waitFor1000Milliseconds();

            boolean check = individualModulePage.isModuleUpdatedSuccessfully();
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(check);
            logger.info("Updation saved successfully...");
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.enterName(name1);
            logger.info("added the name");
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.clickSave();
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Module Title verified Successfully");
            WaitUtils.waitFor1000Milliseconds();

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}