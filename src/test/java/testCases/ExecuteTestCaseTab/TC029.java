package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;


public class TC029 extends BaseClass {

    @Test(dataProvider = "tc029", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifythesearchfeature(
            String projectName,
            String releaseName1,
            String releaseName2
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: verify the search feature *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on Execute Test Case tab");

            executeLandingPage.clickToSelectProject(projectName);
            logger.info("Expanded the Release dropdown from left panel");

            executeLandingPage.clickRelease(releaseName1);
            logger.info("Selected release with less than 10 test runs");

            WaitUtils.waitFor1000Milliseconds();

            int totalEntriesFirst = executeLandingPage.getTotalEntriesCount();
            logger.info("Total entries in first release: " + totalEntriesFirst);
            Assert.assertTrue(totalEntriesFirst < 10,
                    "Expected less than 10 test runs in first release but found " + totalEntriesFirst);

            boolean isNextButtonClickableFirst = executeLandingPage.checkIfNextButtonIsClickable();
            logger.info("Next page button clickable for first release: " + isNextButtonClickableFirst);
            Assert.assertFalse(isNextButtonClickableFirst,
                    "Next button should be disabled when test runs are less than 10");
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickRelease(releaseName2);
            logger.info("Selected release with more than 10 test runs");
            WaitUtils.waitFor1000Milliseconds();;
            int totalEntriesSecond = new ExecuteLandingPage(getDriver()).getTotalEntriesCount();
            logger.info("Total entries in second release: " + totalEntriesSecond);
            Assert.assertTrue(totalEntriesSecond > 10,
                    "Expected more than 10 test runs in second release but found " + totalEntriesSecond);
            WaitUtils.waitFor1000Milliseconds();

            boolean isNextButtonClickableSecond = executeLandingPage.checkIfNextButtonIsClickable();
            logger.info("Next page button clickable for second release: " + isNextButtonClickableSecond);
            Assert.assertTrue(isNextButtonClickableSecond,
                    "Next button should be enabled when test runs are more than 10");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}
