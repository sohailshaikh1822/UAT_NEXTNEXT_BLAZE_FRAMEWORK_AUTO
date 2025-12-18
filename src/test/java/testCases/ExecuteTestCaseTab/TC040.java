package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC040 extends BaseClass {

    @Test(dataProvider = "tc040", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyCreateTestRunFunctionality(String projectname, String releaseName, String requirementID,
            String testCaseID, String successMsg) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Create Test Run functionality *****************");

        try {

            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickToSelectProject(projectname);
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(projectname).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + projectname);

            executeLandingPage.expandRelease(releaseName);
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            executeLandingPage.clickCreateTestRunButton();
            logger.info("Clicked Create Test Run button");
            WaitUtils.waitFor200Milliseconds();

            executeLandingPage.clickRequirementById(requirementID);
            logger.info("Selected requirement ID: " + requirementID);

            executeLandingPage.selectTestCaseCheckbox(testCaseID);
            logger.info("Selected test case ID: " + testCaseID);

            executeLandingPage.clickSaveInPopup();
            logger.info("Clicked Save button in popup");
            WaitUtils.waitFor100Milliseconds();

            boolean isSuccess = executeLandingPage.waitForSuccessMessage(successMsg);
            Assert.assertTrue(isSuccess, "Success message not displayed or mismatched!");
            logger.info("Verified success message: " + successMsg);

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
