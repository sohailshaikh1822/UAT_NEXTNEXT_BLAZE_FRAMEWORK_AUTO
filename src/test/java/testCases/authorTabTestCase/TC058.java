package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC058 extends BaseClass {

    @Test(dataProvider = "tc058", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyTheDropDownsInLeftPane(
            String requirementId,
            String testCaseId,
            String priority,
            String status,
            String type,
            String automationType
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Clicked on the Author test case tab");
            authorTestCasePage.clickRequirement(requirementId);
            logger.info("Clicked on the Requirement");
            authorTestCasePage.clickTestCase(testCaseId);
            logger.info("Clicked on the test case");
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            Assert.assertEquals(individualTestCasePage.getCountPriorityOptions(), Integer.parseInt(priority));
            Assert.assertEquals(individualTestCasePage.getCountStatusOptions(), Integer.parseInt(status));
            Assert.assertEquals(individualTestCasePage.getCountTypeOptions(), Integer.parseInt(type));
            Assert.assertEquals(individualTestCasePage.getCountTypeAutomationProgress(), Integer.parseInt(automationType));
            logger.info("Verified successfully......");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
