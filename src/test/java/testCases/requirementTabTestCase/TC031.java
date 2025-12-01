package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC031 extends BaseClass {

    @Test(dataProvider = "tc035", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyFunctionalityWhenNoIsClickedOnDeleteConfirmation(String project, String epic
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

            logger.info("Initialized AddRequirementPage");

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            Thread.sleep(6000);

            requirementTabPage.clickArrowRightPointingForExpandModule(project);
            logger.info("Selected project" + project);

            requirementTabPage.clickOnModule(epic);
            logger.info("Selected epic" + epic);

            requirementTabPage.clickDeleteModule();
            logger.info("Clicked Delete button");
            Thread.sleep(4000);
            String Actual_Warning = individualModulePage.alretMeaasgeForDeletingModule();
            System.out.println(Actual_Warning);

            Assert.assertEquals(Actual_Warning, "Deleting a module will also delete its associated requirements and test cases. Are you sure you want to delete?");
            logger.info("Assertion has done user can see the warning message");
            Thread.sleep(6000);
            requirementTabPage.clickNoBtn();
            logger.info("Close button has Clicked");

        } catch (AssertionError e) {
            logger.error(" Assertion failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error(" Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");

    }
}
