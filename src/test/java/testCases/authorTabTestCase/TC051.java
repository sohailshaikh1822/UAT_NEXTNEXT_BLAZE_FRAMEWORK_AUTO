package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC051 extends BaseClass {

    @Test(dataProvider = "tc051", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void addNewTestcaseInsideRq(String rqName, String tcName, String description, String priority, String type,
            String qaUser, String preCondition) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            authorTestCasePage.searchRq(rqName);
            logger.info("Sucessfully search with RQId");
            authorTestCasePage.clickRequirement(rqName);
            logger.info("Click Selected Rq");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Click Addtestcase button");
            WaitUtils.waitFor3000Milliseconds();
            addTestcasePage.setTestCaseName(tcName);
            logger.info("Set testcase name");
            addTestcasePage.setDescription(description);
            logger.info(" Set Description");
            addTestcasePage.selectPriority(priority);
            logger.info("Select Priority");
            addTestcasePage.selectType(type);
            logger.info("Select Type");
            addTestcasePage.selectQaUser(qaUser);
            logger.info("select QaUser");
            addTestcasePage.setPrecondition(preCondition);
            logger.info("Add Precondition");
            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.clickSave();
            logger.info("Successfully add a New testcase");

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
