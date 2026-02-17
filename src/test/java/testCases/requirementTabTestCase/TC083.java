package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.util.List;

public class TC083 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyObjectDropdownValues() throws InterruptedException {

        logger.info("****** Starting TC083 ********");

        try {

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage =
                    new RequirementTabPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement tab");

            requirementTabPage.clickRecycleBinButton();
            requirementTabPage.verifyRecycleBinPageOpened();
            logger.info("Recycle Bin opened successfully");

            List<String> options =
                    requirementTabPage.getObjectDropdownOptions();

            List<String> normalizedOptions = options.stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .toList();

            Assert.assertTrue(normalizedOptions.contains("all"),
                    "All option not found");

            Assert.assertTrue(normalizedOptions.contains("module"),
                    "Module option not found");

            Assert.assertTrue(normalizedOptions.contains("requirement"),
                    "Requirement option not found");

            logger.info("Object dropdown values verified successfully");


            requirementTabPage.selectObjectFromDropdown("Requirement");
            requirementTabPage.verifyOnlyRequirementsDisplayed();

            requirementTabPage.selectObjectFromDropdown("All");
            requirementTabPage.verifyAllFilterDisplayedCorrectly();


            requirementTabPage.selectObjectFromDropdown("Module");

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
