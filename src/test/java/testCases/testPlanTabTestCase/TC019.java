package testCases.testPlanTabTestCase;

import DataProviders.TestPlanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC019 extends BaseClass {

    @Test(dataProvider = "tc019", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDuplicateReleaseNames(String projectName, String release) throws InterruptedException {
        logger.info("****** Test Case 19: Verify duplicate release names *****************");
        try {

            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandProjectSTG(projectName);
            logger.info("Expanded STG Project: " + projectName);

            int count = testPlanPage.getDuplicateReleaseCount(release);
            logger.info("Release name '" + release + "' found " + count + " times");

            Assert.assertTrue(count > 1, "Duplicate release not found for: " + release);
            logger.info("Duplicate release verified successfully for: " + release);

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
