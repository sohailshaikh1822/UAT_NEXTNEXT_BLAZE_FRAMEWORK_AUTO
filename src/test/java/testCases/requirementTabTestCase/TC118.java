package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC118 extends BaseClass {

    @Test(dataProvider = "tc118", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyRequirementHistoryTabDisplaysVersionDetails(
            String project,
            String epic,
            String req
    ) throws InterruptedException {
        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementsPage = new RequirementTabPage(getDriver());
            logger.info("Initialized RequirementTabPage");

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            logger.info("Initialized IndividualModulePage");

            requirementsPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();

            logger.info("Clicked on the Project from left panel to open the module");
            requirementsPage.clickDropdownToSelectProject(project);
            logger.info("Navigate to the project");

            requirementsPage.clickOnModule(epic);
            logger.info("Navigated to Module");
            WaitUtils.waitFor2000Milliseconds();

            individualModulePage.clickRequirement(req);
            logger.info("Click on requirement tab");
            WaitUtils.waitFor2000Milliseconds();

            individualModulePage.clickRequirementHistory();
            logger.info("Clicked on the requirement history");
            WaitUtils.waitFor2000Milliseconds();

            String headerText = individualModulePage.verifyingHeaderOfRequirementHistory();

            Assert.assertTrue(headerText.contains("Version"), "Version column is missing");
            Assert.assertTrue(headerText.contains("Updated By"), "Updated By column is missing");
            Assert.assertTrue(headerText.contains("Updated At"), "Updated At column is missing");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }
        logger.info("************ Test Case Finished *************************");

    }
}
