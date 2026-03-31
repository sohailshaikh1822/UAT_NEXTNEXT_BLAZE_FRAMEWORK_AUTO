package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC083 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyObjectDropdownValues() throws InterruptedException {

        logger.info("****** Starting TC083 ********");

        try {

            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage =
                    new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement tab");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickRecycleBinButton();
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.verifyRecycleBinPageOpened();
            logger.info("Recycle Bin opened successfully");
            WaitUtils.waitFor1000Milliseconds();

            List<String> options =
                    requirementTabPage.getObjectDropdownOptions();
            WaitUtils.waitFor1000Milliseconds();

            List<String> normalizedOptions = options.stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .toList();
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(normalizedOptions.contains("all"),
                    "All option not found");
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(normalizedOptions.contains("module"),
                    "Module option not found");
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(normalizedOptions.contains("requirement"),
                    "Requirement option not found");
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Object dropdown values verified successfully");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.selectObjectFromDropdown("Requirement");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.verifyOnlyRequirementsDisplayed();
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.selectObjectFromDropdown("All");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.verifyAllFilterDisplayedCorrectly();
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.selectObjectFromDropdown("Module");
            WaitUtils.waitFor1000Milliseconds();

            logger.info("****** TC083 Completed Successfully ********");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }
    }
}