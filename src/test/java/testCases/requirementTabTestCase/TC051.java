package testCases.requirementTabTestCase;

import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC051 extends BaseClass {
    @Test(dataProvider = "tc051", dataProviderClass = DataProviders.RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_SAVE_functionality_in_Add_TestCase(
            String moduleName,
            String requirementId
    ) {
        logger.info("************ Starting Test Case: Verify SAVE functionality in Add TestCase *****************");

        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestCasePage = new AddTestcasePage(getDriver());


            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickEpicDropdown();
            logger.info("Clicked on Epic drop down");

            requirementTabPage.clickOnModule(moduleName);
            logger.info("Opened module: " + moduleName);

            authorTestCasePage.clickRequirement(requirementId);
            logger.info("Clicked on requirement id " + requirementId);

            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestcase Button");

            addTestCasePage.clickSave();
            logger.info("clicked on save button");

            addTestCasePage.getTcNameRequiredWarningMessage();




        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
        }

        logger.info("************ Test Case Finished *************************");

    }
}
