package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC057 extends BaseClass {

    @Test(dataProvider = "tc057", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDescriptionandPreconditionfieldsInTestCaseForm(
            String epicName,
            String featureName,
            String rq_id,
            String desc,
            String pri
    ) throws InterruptedException {
        logger.info("****** Starting TC057: Verify Description and Precondition fields In TestCase Form ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);

            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);

            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);
            WaitUtils.waitFor2000Milliseconds();

            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestCase");
            WaitUtils.waitFor3000Milliseconds();
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());

            if (addTestcasePage.isDescriptionDisplayed()) {
                addTestcasePage.setDescription(desc);
                logger.info("Entered description successfully");
            } else {
                logger.error("Description field not displayed");
                Assert.fail("Description field not displayed");
            }

            addTestcasePage.selectPriority(pri);
            logger.info("User able to select Priority from the drop down " + pri);

            logger.info("User able to set description and priority in add test case page");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC057 Finished ************");
    }
}
