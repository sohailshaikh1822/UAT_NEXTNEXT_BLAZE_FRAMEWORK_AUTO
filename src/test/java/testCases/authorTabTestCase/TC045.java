package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC045 extends BaseClass {

    @Test(dataProvider = "tc045", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNamefieldismandatoryinAddTestcase(
            String epicName,
            String featureName,
            String rq_id,
            String desc
    ) throws InterruptedException {
        logger.info("****** Starting TC045: Verify Name field is mandatory in Add Testcase ******");
        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);
            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestCase");
            WaitUtils.waitFor1000Milliseconds();

            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());

            if (addTestcasePage.isDescriptionDisplayed()) {
                addTestcasePage.setDescription(desc);
                logger.info("Entered description successfully");
            } else {
                logger.error("Description field not displayed");
            }

            logger.info("Leaving Test Case Name field blank");

            addTestcasePage.clickSave();
            logger.info("Clicked Save button without entering Name");

            String actualError = addTestcasePage.waitForNameFieldRequiredError();
            Assert.assertEquals(actualError, "Error: Name is required.",
                    "Error message text mismatch");

            logger.info("Validation successful: Error message displayed - " + actualError);

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ TC045 Finished ************");
    }
}
