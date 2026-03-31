package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.RequirementDataProvider;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC006 extends BaseClass {

    @Test(dataProvider = "tc006", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementListUnderModule(String projectName, String moduleName) throws InterruptedException {

        logger.info("************ Starting Test Case: Verify Requirement list under module *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickDropdownToSelectProject(projectName);
            logger.info("Expanded project: " + projectName);
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(moduleName);
            WaitUtils.waitFor1000Milliseconds();

            List<String> requirementIDs = requirementTabPage.getRequirementIDs();
            WaitUtils.waitFor1000Milliseconds();

            int count = requirementIDs.size();
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(count > 0, "No requirements found under module: " + moduleName);
            logger.info("Requirements under module '" + moduleName + "': " + requirementIDs);
            logger.info("Total requirements found: " + count);
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