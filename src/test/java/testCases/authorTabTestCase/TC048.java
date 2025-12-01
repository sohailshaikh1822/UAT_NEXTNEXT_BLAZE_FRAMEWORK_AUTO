package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC048 extends BaseClass {

    @Test(dataProvider = "AddTest", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyTheSaveButton(
            String epic, String feature, String requirementId,
            String testCaseName, String description, String priority,
            String QA
    ) throws InterruptedException {
        logger.info("****** Starting the Log in Test Case *****************");
        try {
            login();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Clicked On the author test case");
            authorTestCasePage.selectEpic(epic);
            logger.info("Selected the epic.");
            authorTestCasePage.selectFeature(feature);
            logger.info("Selected the Feature");
            authorTestCasePage.clickRequirement(requirementId);
            logger.info("Clicked on the requirement ");
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on the add test case");
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            addTestcasePage.setTestCaseName(testCaseName);
            addTestcasePage.setDescription(description);
            addTestcasePage.selectPriority(priority);
            addTestcasePage.selectQaUser(QA);
            logger.info("filled the details");
            addTestcasePage.clickSave();
            logger.info("clicked the save button");
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
