package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC041 extends BaseClass {

    @Test(dataProvider = "tc041", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyUpdatedCountoftestcase(String rqName, String tcName, String description,
            String priority, String type, String qaUser, String preCondition)
            throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Login successful.");
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Navigating to 'Author Test Case' tab.");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.searchRq(rqName);
            logger.info("Requirement '" + rqName + "' searched and selected.");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement(rqName);
            logger.info("Requirement '" + rqName + "' clicked.");
            WaitUtils.waitFor1000Milliseconds();

            String totalCountBeforeAddTestcase = authorTestCasePage.totalNoOfTestcasesInsideRq();
            logger.info("Total test cases before addition: " + totalCountBeforeAddTestcase);

            int before = authorTestCasePage.extractNumber(totalCountBeforeAddTestcase);
            WaitUtils.waitFor1000Milliseconds();
//            authorTestCasePage.clickAddTestcase();
//            logger.info("Clicked 'Add Test Case' button.");
//            WaitUtils.waitFor1000Milliseconds();
//            addTestcasePage.setTestCaseName(tcName);
            authorTestCasePage.clickAddTestCaseAndEnterName(tcName);
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
            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.clickSave();
            logger.info("New test case saved successfully.");
            WaitUtils.waitFor1000Milliseconds();
            String totalCountAfterAddNewTestcase = authorTestCasePage.totalNoOfTestcasesInsideRq(before + 1);
            logger.info("Total test cases after addition: " + totalCountAfterAddNewTestcase);

            int after = authorTestCasePage.extractNumber(totalCountAfterAddNewTestcase);

            Assert.assertEquals(after, before + 1, "Test case count did not increase after adding a new test case.");

        } catch (Exception e) {
            logger.error("An exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Execution Completed ************");
    }
}
