package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC041 extends BaseClass {

    @Test(dataProvider = "tc041", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyUpdatedCountoftestcase(String rqName, String tcName, String description,
                                             String priority, String type, String qaUser, String preCondition) throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            login();
            logger.info("Login successful.");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());

            authorTestCasePage.searchRq(rqName);
            logger.info("Requirement '" + rqName + "' searched.");

            authorTestCasePage.clickRequirement(rqName);
            logger.info("Requirement '" + rqName + "' selected.");

            String totalCountBeforeAddTestcase = authorTestCasePage.totalNoOfTestcasesInsideRq();
            int before = authorTestCasePage.extractNumber(totalCountBeforeAddTestcase);

            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked Add Test Case.");

            addTestcasePage.setTestCaseName(tcName);
            addTestcasePage.setDescription(description);
            addTestcasePage.selectPriority(priority);
            addTestcasePage.selectType(type);
            addTestcasePage.selectQaUser(qaUser);
            addTestcasePage.setPrecondition(preCondition);
            addTestcasePage.clickSave();
            logger.info("Test case saved.");

            String totalCountAfter = authorTestCasePage.totalNoOfTestcasesInsideRq(before + 1);
            int after = authorTestCasePage.extractNumber(totalCountAfter);

            Assert.assertEquals(after, before + 1, "Test case count did not increase.");

        } catch (Exception e) {
            logger.error("An exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Execution Completed ************");
    }
}
