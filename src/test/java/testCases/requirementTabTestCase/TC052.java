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

@Test(dataProvider = "tc052", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

public class TC052 extends BaseClass {
    public void VerifyUserCanCreateNewTcInDifferentReq(
            String mainProject,
            String epic,
            String requirementId,
            String tcName, String description,
            String priority, String type, String qaUser, String preCondition
    ) throws InterruptedException {

        logger.info("****** Starting the Log in Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage=new AddTestcasePage(getDriver());
            LinkTestCasePage linkTestCasePage=new LinkTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();
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
            addTestcasePage.setTestCaseName(tcName);
            logger.info("Test case name set to: " + tcName);
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
            WaitUtils.waitFor3000Milliseconds();
            addTestcasePage.clickSave();
            logger.info("New test case saved successfully.");
            String expectedMessage = "Test case linked successfully.";
            String actualMessage = linkTestCasePage.getAlertMessageWhileLinkingNewTc();
            Assert.assertEquals(actualMessage, expectedMessage);
            logger.info("Linked Test Cases screen displayed successfully");
        } catch (Exception | AssertionError e) {
            logger.error("Test case failed ...", e);
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
