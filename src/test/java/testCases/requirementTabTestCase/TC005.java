package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import DataProviders.RequirementDataProvider;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC005 extends BaseClass {

    @Test(dataProvider = "tc005", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectNameIsVisible(String ProjectName) throws InterruptedException {

        logger.info("************ Starting Test Case: Verify that the project name is visible *****************");

        try {

            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();
//            requirementTabPage.clickProjectName();
//            logger.info("Clicked on project: " + ProjectName);

            requirementTabPage.clickDropdownToSelectProject(ProjectName);
            logger.info("Clicked on the project");
            WaitUtils.waitFor1000Milliseconds();

            String isProjectVisible = requirementTabPage.getProjectNameText();
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(!(isProjectVisible.equals(null)), "Project name is not visible beside the label");
            logger.info("Project name is displayed correctly: " + ProjectName);
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