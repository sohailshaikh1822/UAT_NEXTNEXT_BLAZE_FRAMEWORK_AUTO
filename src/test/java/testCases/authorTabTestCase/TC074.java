package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC074 extends BaseClass {
    @Test(dataProvider = "tc074", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_version_is_displayed_after_creating_a_new_test_case(
            String epicName,
            String featureName,
            String rq_id,
            String TCname,
            String Type,
            String Qauser
    ) throws InterruptedException {
        logger.info("****** Starting TC074: Verify version is displayed after creating a new test case ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestCase");
            WaitUtils.waitFor3000Milliseconds();
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            addTestcasePage.setTestCaseName(TCname);
            logger.info("Testcase Name"+TCname);
            addTestcasePage.selectType(Type);
            addTestcasePage.selectQaUser(Qauser);
            addTestcasePage.clickSave();
            logger.info("clicked on save button");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.openNewlyCreatedTestCase();
            logger.info("Opened newly created Test Case");
            WaitUtils.waitFor3000Milliseconds();
            String actualVersion =individualTestCasePage.getTestCaseVersion();
            logger.info("Version displayed: " + actualVersion);
            Assert.assertEquals(actualVersion, "1.0", "Version is not displayed correctly after TC creation");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC074 Finished ************");
    }
}
