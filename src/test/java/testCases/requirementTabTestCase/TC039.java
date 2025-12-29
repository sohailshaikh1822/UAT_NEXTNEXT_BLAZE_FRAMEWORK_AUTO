package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;
@Test(dataProvider = "tc039", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

public class TC039 extends BaseClass {
    public void verifyAfterClickAddTestcaseButtonDisplayedNextWindow(
            String mainProject,
            String epic,
            String requirementId
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
            logger.info("Clicked Add test case");
            WaitUtils.waitFor1000Milliseconds();
            String expectedHeader = "Create Test Cases";
            String actualHeader = addTestcasePage.waitAndGetAddTestCaseHeader();
            Assert.assertEquals(actualHeader, expectedHeader);
            logger.info("Create Test Cases screen displayed successfully");
        } catch (Exception | AssertionError e) {
            logger.error("Test case failed ...", e);
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
