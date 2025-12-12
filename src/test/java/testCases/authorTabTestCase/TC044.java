package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC044 extends BaseClass {

    @Test(dataProvider = "tc044", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyVisibilityOfAllFieldsInAddTestCase(String requirementId
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement(requirementId);
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("Clicked on Add Test Case button");
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(addTestcasePage.isNameDisplayed());
            logger.info("Verified Name field is displayed");
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(addTestcasePage.isDescriptionDisplayed());
            logger.info("Verified Description field is displayed");
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(addTestcasePage.isPriorityDropdownDisplayed());
            logger.info("Verified Priority dropdown is displayed");
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(addTestcasePage.isTypeDropdownDisplayed());
            logger.info("Verified Type dropdown is displayed");
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(addTestcasePage.isQAUserDropdownDisplayed());
            logger.info("Verified QA User dropdown is displayed");
            WaitUtils.waitFor1000Milliseconds();
            Assert.assertTrue(addTestcasePage.isPreconditionDisplayed());
            logger.info("Verified Precondition field is displayed");
            logger.info("All columns in Add Test Case page verified successfully");

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
