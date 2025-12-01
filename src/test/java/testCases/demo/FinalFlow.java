package testCases.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class FinalFlow extends BaseClass {

    public boolean check = false;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementFlow() throws InterruptedException {
        logger.info("****** Starting the Demo flow Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully...");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("navigated to requirement tab");

            requirementTabPage.clickOnTheProjectName();
            logger.info("clicked on the project name");

            requirementTabPage.clickNewModule();
            logger.info("clicked on new module ");

            requirementTabPage.setModuleName("Epic Mayukhjit");

            requirementTabPage.saveModule();
            requirementTabPage.clickNewModule();
            requirementTabPage.setModuleName("Feature Mayukhjit");
            requirementTabPage.saveModule();
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            individualModulePage.clickAddRequirement();
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            addRequirementPage.setRequirementId("RQ-114");
            addRequirementPage.setDescription("Hello new requirement");
            addRequirementPage.clickSave();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            authorTestCasePage.selectEpic("Epic Mayukhjit");
            authorTestCasePage.selectFeature("Feature Mayukhjit");
            authorTestCasePage.clickRequirement("RQ-114");

            authorTestCasePage.clickAddTestcase();

            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            addTestcasePage.setTestCaseName("tc-0142");
            addTestcasePage.setDescription("As a tester , I want to what and why ");
            addTestcasePage.selectPriority("Low");
            addTestcasePage.selectQaUser("Mayukhjit Chakraborty");
            addTestcasePage.clickSave();
            String testCaseId = addTestcasePage.getTestcaseId("tc-0142");
            System.out.println(testCaseId);
            authorTestCasePage.clickTestCase(testCaseId);
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            logger.info("debugging successfully");
            individualTestCasePage.addTestStepsFromExcelForNewTestCase("step 1,step 2, step 3", "expected 1 ,expected 2 ,expected 3");
            individualTestCasePage.clickSaveButton();
            RequirementTabPage requirementTabPage1 = new RequirementTabPage(getDriver());
            requirementTabPage1.clickRequirementTab();
            requirementTabPage1.clickArrowRightPointingForExpandModule("STG- PulseCodeOnAzureCloud");
            requirementTabPage1.clickOnModule("Epic Mayukhjit");
            requirementTabPage1.clickDeleteModule();
            requirementTabPage1.clickConfirmDelete();

        } catch (AssertionError e) {
            check = true;
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            check = true;
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }

    @AfterClass
    public void deleteModule() throws InterruptedException {
        if (check) {
            RequirementTabPage requirementTabPage1 = new RequirementTabPage(getDriver());
            requirementTabPage1.clickRequirementTab();
            requirementTabPage1.clickArrowRightPointingForExpandModule("STG- PulseCodeOnAzureCloud");
            requirementTabPage1.clickOnModule("Epic Mayukhjit");
            requirementTabPage1.clickDeleteModule();
            requirementTabPage1.clickConfirmDelete();
        }
    }
}
