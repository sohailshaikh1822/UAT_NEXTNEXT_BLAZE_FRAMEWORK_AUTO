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
@Test(dataProvider = "tc045", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

public class TC045 extends BaseClass {

    public void VerifyUnlinkingOfNewTestCases(
            String mainProject,
            String epic,
            String requirementId,
            String tcName
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

            authorTestCasePage.clicklinktestcase();
            logger.info("Clicked on LinkTestcase Button");
            linkTestCasePage.searchTestCase(tcName);
            logger.info("Searched with TcName");

            linkTestCasePage.clickSearch();
            logger.info("Clicked Search Button");
            linkTestCasePage.clickPid(tcName);
            logger.info("Clicked Searched TestCasename");
            WaitUtils.waitFor3000Milliseconds();
            //Test case linked successfully.
            String expectedMessage = "Test case linked successfully.";
            String actualMessage = linkTestCasePage.getAlertMessageWhileLinkingNewTc();
            Assert.assertEquals(actualMessage, expectedMessage);
            logger.info("Linked Test Cases screen displayed successfully");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickActionIcon(tcName);
            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.confirmUnlink();

        } catch (Exception | AssertionError e) {
            logger.error("Test case failed ...", e);
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
