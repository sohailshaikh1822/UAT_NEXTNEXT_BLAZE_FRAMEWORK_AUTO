package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.LinkTestCasePage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

@Test(dataProvider = "tc053", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
public class TC053 extends BaseClass {

    public void VerifyEmptyTestCaseNameValidation(
            String mainProject,
            String epic,
            String requirementId, String description,
            String priority, String type, String qaUser, String preCondition
    ) throws InterruptedException {

        logger.info("****** Starting the Log in Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            LinkTestCasePage linkTestCasePage = new LinkTestCasePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.clickAuthorTestcase();
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickDropdownToSelectProject(mainProject);
            logger.info("Expanded the main project: " + mainProject);
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.clickRequirement(requirementId);
            logger.info("Clicked on requirement id " + requirementId);
            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked 'Add Test Case' button.");
            WaitUtils.waitFor1000Milliseconds();

            addTestcasePage.setDescription(description);
            logger.info("Test case description set.");
            WaitUtils.waitFor1000Milliseconds();

            addTestcasePage.selectPriority(priority);
            logger.info("Priority set to: " + priority);
            WaitUtils.waitFor1000Milliseconds();

            addTestcasePage.selectType(type);
            logger.info("Type set to: " + type);
            WaitUtils.waitFor1000Milliseconds();

            addTestcasePage.selectQaUser(qaUser);
            logger.info("QA user assigned: " + qaUser);
            WaitUtils.waitFor1000Milliseconds();

            addTestcasePage.setPrecondition(preCondition);
            logger.info("Precondition set.");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            addTestcasePage.clickSave();
            logger.info("New test case saved successfully.");
            WaitUtils.waitFor1000Milliseconds();

            String actualError = addTestcasePage.waitForNameFieldRequiredError();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Validation successful: Error message displayed - " + actualError);
            WaitUtils.waitFor1000Milliseconds();

        } catch (Exception | AssertionError e) {
            logger.error("Test case failed ...", e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}