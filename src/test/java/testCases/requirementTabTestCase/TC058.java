package testCases.requirementTabTestCase;

import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

@Test(retryAnalyzer = RetryAnalyzer.class)
public class TC058 extends BaseClass {

    public void VerifyThatTheUserIsAbleToDownloadAttachedFile() throws InterruptedException {

        logger.info("************ Starting TC058 ************");

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

            authorTestCasePage.clickRequirement("RQ-1599");
            logger.info("Clicked on requirement id " + "RQ-1599");

            requirementTabPage.clickDownloadAttachementButton(1);
            logger.info("Downloaded the attachement");



        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ TC058 Finished ************");
    }

}
