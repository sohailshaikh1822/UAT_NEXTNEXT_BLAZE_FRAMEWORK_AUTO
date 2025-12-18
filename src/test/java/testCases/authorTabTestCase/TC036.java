package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC036 extends BaseClass {

    @Test(
            dataProvider = "tc036",
            dataProviderClass = AuthorTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyAddTestcaseDisplayedNextWindow(
            String epic,
            String feature,
            String requirementId
    ) throws InterruptedException {

        logger.info("****** Starting the Log in Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");

            WaitUtils.waitFor1000Milliseconds();

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());

            authorTestCasePage.clickAuthorTestcase();
            logger.info("Clicked the Author test case");

            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.selectEpic(epic);
            logger.info("Selected the epic");

            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.selectFeature(feature);
            logger.info("Selected the feature");

            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.clickRequirement(requirementId);
            logger.info("Clicked on requirement id " + requirementId);

            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked Add test case");
            WaitUtils.waitFor1000Milliseconds();
            String expectedHeader = "Create Test Cases";
            String actualHeader = addTestcasePage.waitAndGetAddTestCaseHeader();
            Assert.assertEquals(actualHeader, expectedHeader);
            logger.info("Create Test Cases screen displayed successfully");
        } catch (Exception | AssertionError e) {
            logger.error("Test case failed ...", e);
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
