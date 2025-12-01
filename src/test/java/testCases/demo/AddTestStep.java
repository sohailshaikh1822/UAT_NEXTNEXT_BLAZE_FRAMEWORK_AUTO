package testCases.demo;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class AddTestStep extends BaseClass {

    @Test(dataProvider = "AddTest", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyTestCaseCreation(
            String epic, String feature, String requirementId,
            String testCaseName, String description, String priority,
            String QA
    ) throws InterruptedException {
        logger.info("****** Starting the Log in Test Case *****************");
        try {
            login();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            authorTestCasePage.clickRequirement("RQ-437");
            authorTestCasePage.clickTestCase("TC-369");
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            individualTestCasePage.addTestStepsFromExcelForNewTestCase("step 1,step 2, step 3", "expected 1 ,expected 2 ,expected 3");
            //individualTestCasePage.addTestStepsAtEndForExistingTestCase("step 1,step 2, step 3","expected 1 ,expected 2 ,expected 3");
            //individualTestCasePage.editSpecificTestStep(3,"Hi this Mayukhjit","Yes It is expected");
            individualTestCasePage.clickSaveButton();

        } catch (Exception | AssertionError e) {
            e.printStackTrace();
            logger.error("Test case failed ...");
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
