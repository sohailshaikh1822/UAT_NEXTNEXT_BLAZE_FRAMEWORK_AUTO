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
@Test(retryAnalyzer = RetryAnalyzer.class)

public class TC056 extends BaseClass {

    public void VerifyApplicationBehaviorwhenDuplicateTestCaseNameIsentered() throws InterruptedException {

        logger.info("****** Starting the Log in Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");

            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            LinkTestCasePage linkTestCasePage = new LinkTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();

            requirementTabPage.clickDropdownToSelectProject("STG- SPARK Modernization");
            logger.info("Expanded the main project: " + "STG- SPARK Modernization");

            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickArrowRightPointingForExpandModule("Epic j17");
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickOnModule("Epic j17");
            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.clickRequirement("RQ-1599");
            logger.info("Clicked on requirement id " + "RQ-1599");

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestCase");
            WaitUtils.waitFor1000Milliseconds();

            addTestcasePage.setTestCaseName("Duplicate Testcase Name");
            logger.info("Test case name set to: " + "Duplicate Testcase Name");

            addTestcasePage.clickSave();
            logger.info("Clicked Save button ");

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestCase");
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickYesBtn();

            addTestcasePage.setTestCaseName("Duplicate Testcase Name");
            logger.info("Test case name set to: " + "Duplicate Testcase Name");

            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.clickSave();
            logger.info("Clicked Save button after entering the duplicate name ");

            requirementTabPage.clickYesBtn();



        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ TC056 Finished ************");
    }

}
