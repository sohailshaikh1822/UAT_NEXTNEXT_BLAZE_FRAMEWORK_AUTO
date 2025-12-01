package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC037 extends BaseClass {

    @Test(dataProvider = "tc037", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyViewAllRadioButton(
            String parentModule,
            String releaseName,
            String subTestCycle,
            String subTestsuit
    ) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Expand feature of sub test cycle *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(parentModule);
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(parentModule).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + parentModule);

            executeLandingPage.expandRelease(releaseName);
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            executeLandingPage.expandSubTestCycle(subTestCycle);
            Assert.assertTrue(executeLandingPage.isSubTestCycleVisible(subTestCycle), "Sub test cycle not visible");
            logger.info("Expanded Test Cycle :  " + subTestCycle);

            executeLandingPage.expandTestSuit(subTestsuit);
            logger.info("TestSuit visible");

            executeLandingPage.ClickViewAllRadioButton();
            logger.info("Clicked on View All Radio Button");

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
