package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC048 extends BaseClass {

    @Test(dataProvider = "tc048", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyTheSaveButton(
            String epic, String feature, String requirementId,
            String testCaseName, String description, String priority,
            String QA
    ) throws InterruptedException {
        logger.info("****** Starting the Log in Test Case *****************");
        try {
            login();
            WaitUtils.waitFor1000Milliseconds();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Clicked On the author test case");
            authorTestCasePage.selectEpic(epic);
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Selected the epic.");
            authorTestCasePage.selectFeature(feature);
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Selected the Feature");
            authorTestCasePage.clickRequirement(requirementId);
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Clicked on the requirement ");
            authorTestCasePage.clickAddTestcase();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Clicked on the add test case");
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.setTestCaseName(testCaseName);
            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.setDescription(description);
            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.selectPriority(priority);
            WaitUtils.waitFor1000Milliseconds();
            addTestcasePage.selectQaUser(QA);
            WaitUtils.waitFor1000Milliseconds();
            logger.info("filled the details");
            addTestcasePage.clickSave();
            logger.info("clicked the save button");
            WaitUtils.waitFor1000Milliseconds();
            String testCaseId = addTestcasePage.getTestcaseId(testCaseName);
            Assert.assertFalse(testCaseId.isEmpty());
            logger.info("Verified successfully");

        } catch (Exception | AssertionError e) {
            e.printStackTrace();
            logger.error("Test case failed ...");
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
