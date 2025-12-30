package testCases.requirementTabTestCase;

import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

@Test(retryAnalyzer = RetryAnalyzer.class)
public class TC057 extends BaseClass {

    public void VerifyLongDescriptionInputHandlingInCreateTestCases() throws InterruptedException {

        logger.info("************ Starting TC057 ************");

        try {
            login();
            logger.info("Logged in successfully");

            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());

            authorTestCasePage.clickAuthorTestcase();
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();

            requirementTabPage.clickDropdownToSelectProject("STG- SPARK Modernization");
            logger.info("Selected project: STG- SPARK Modernization");

            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickArrowRightPointingForExpandModule("Epic j17");
            requirementTabPage.clickOnModule("Epic j17");

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement("RQ-1599");
            logger.info("Clicked on requirement id RQ-1599");

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on Add Test Case");

            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.setTestCaseName("TC058_Long_Description_Test");
            logger.info("Test case name entered");

            // Create 3000-character description
            StringBuilder descriptionBuilder = new StringBuilder();
            for (int i = 0; i < 3000; i++) {
                descriptionBuilder.append("A");
            }
            String longDescription = descriptionBuilder.toString();
            addTestcasePage.setDescription(longDescription);
            logger.info("Entered 3000 characters in description field");

            addTestcasePage.clickSave();
            logger.info("Clicked Save button successfully");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC057 Finished ************");
    }
}
