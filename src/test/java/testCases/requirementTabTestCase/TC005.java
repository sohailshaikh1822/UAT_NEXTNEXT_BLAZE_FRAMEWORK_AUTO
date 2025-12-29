package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import DataProviders.RequirementDataProvider;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC005 extends BaseClass {

    @Test(dataProvider = "tc005", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectNameIsVisible(String ProjectName) throws InterruptedException {
        logger.info("************ Starting Test Case: Verify that the project name is visible *****************");

        try {

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

//            requirementTabPage.clickProjectName();
//            logger.info("Clicked on project: " + ProjectName);

            requirementTabPage.clickDropdownToSelectProject(ProjectName);
            logger.info("Clicked on the project");


            String isProjectVisible = requirementTabPage.getProjectNameText();
            Assert.assertTrue(!(isProjectVisible.equals(null)), "Project name is not visible beside the label");
            logger.info("Project name is displayed correctly: " + ProjectName);

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
