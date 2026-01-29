package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC066 extends BaseClass {

    @Test(dataProvider = "tc066", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void addNewTestcaseWithoutTcName(String rqName) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");

            logger.info("Navigated to Author Test Case tab");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.searchRq(rqName);
            logger.info("Successfully searched with RQ ID: " + rqName);
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage. clickRequirement(rqName);
            logger.info("Clicked on the selected RQ");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked 'Add Test Case' button");

            logger.info("Attempting to save without entering test case name");
            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.clickSave();
            logger.info("Clicked the save button");
            WaitUtils.waitFor2000Milliseconds();
            String actualWarning = addTestcasePage.getTcNameRequiredWarningMessage();
            String expectedWarning = "Error: Name is required.";

            logger.info("Validating warning message...");
            logger.info("Expected: " + expectedWarning);
            logger.info("Actual: " + actualWarning);

            Assert.assertEquals(actualWarning, expectedWarning, "Validation message did not match!");
            logger.info("Verified warning notification successfully");

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");

    }
}
