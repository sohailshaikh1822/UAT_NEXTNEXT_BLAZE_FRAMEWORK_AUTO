package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.RequirementDataProvider;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.util.List;

public class TC006 extends BaseClass {

    @Test(dataProvider = "tc006", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementListUnderModule(String projectName, String moduleName) throws InterruptedException {

        logger.info("************ Starting Test Case: Verify Requirement list under module *****************");

        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickDropdownToSelectProject(projectName);
            logger.info("Expanded project: " + projectName);

            requirementTabPage.clickOnModule(moduleName);

            List<String> requirementIDs = requirementTabPage.getRequirementIDs();
            int count = requirementIDs.size();

            Assert.assertTrue(count > 0, "No requirements found under module: " + moduleName);
            logger.info("Requirements under module '" + moduleName + "': " + requirementIDs);
            logger.info("Total requirements found: " + count);

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
