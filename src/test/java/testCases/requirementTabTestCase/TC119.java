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

public class TC119 extends BaseClass {

    @Test(dataProvider = "tc119", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDefaultVersionWhenATestCaseIsCreated(
            String project,
            String epic
    ) throws InterruptedException {
        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementsPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());

            requirementsPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();
            requirementsPage.clickDropdownToSelectProject(project);
            logger.info("Navigate to the project");
            WaitUtils.waitFor1000Milliseconds();

            requirementsPage.clickOnModule(epic);
            logger.info("Navigated to Module");
            WaitUtils.waitFor2000Milliseconds();

            individualModulePage.clickAddRequirement();
            logger.info("Click on requirement tab");
            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("Requirement create successfully");
            WaitUtils.waitFor2000Milliseconds();

            individualModulePage.clickRequirementHistory();
            logger.info("Clicked on the requirement history");
            WaitUtils.waitFor2000Milliseconds();

            individualModulePage.clickLatestUpdatedRequirementVersion();
            logger.info("Clicked on the latest version");
            WaitUtils.waitFor2000Milliseconds();

            Assert.assertTrue(
                    individualModulePage.verifyDefaultVersionIsOne(),
                    "Default version is not 1"
            );

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
