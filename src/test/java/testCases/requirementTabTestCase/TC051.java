package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

@Test(dataProvider = "tc051", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

public class TC051 extends BaseClass {

    public void VerifyUserCanCreateNewTestCasesForTheSelectedRequirement(
            String moduleid,
            String rqid
    ) throws InterruptedException {

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
            requirementTabPage.clickArrowRightPointingForExpandModule(moduleid);
            requirementTabPage.clickOnModule(moduleid);

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement(rqid);
            logger.info("Clicked on requirement id RQ-1599");

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on Add Test Case");

            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.setTestCaseName("TC058_Long_Description_Test");
            logger.info("Test case name entered");
            WaitUtils.waitFor1000Milliseconds();
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
