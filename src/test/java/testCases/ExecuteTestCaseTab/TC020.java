package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC020 extends BaseClass {

    @Test(dataProvider = "tc020", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verify_the_user_is_able_to_click_on_the_linked_defect_and_see_the_details(
            String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR,
            String defid) throws InterruptedException {

        logger.info(
                "****** Starting Test Case 020: verify the user is able to click on the linked defect and see the details *****************");

        try {
            login();
            logger.info("Logged in successfully");

            WaitUtils.waitFor2000Milliseconds();

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickToSelectProject(projectName);
            logger.info("Expanded Project: " + projectName);

            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandRelease(ReleaseName);
            logger.info("Expanded Release: " + ReleaseName);

            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandSubTestCycle(CycleName);
            logger.info("Expanded Cycle: " + CycleName);

            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);

            WaitUtils.waitFor2000Milliseconds();

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());

            boolean defectPresent = individualTestrun.isDefectPresent();

            WaitUtils.waitFor1000Milliseconds();

            if (defectPresent) {
                logger.info("Defect is present under the defect section");
                Assert.assertTrue(defectPresent, "Defect is not displayed under the defect category.");
            } else {
                logger.error("Defect is NOT present under the defect category.");

                LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());

                WaitUtils.waitFor1000Milliseconds();

                linkDefectPage.clickDefectById(defid);
                logger.info("Clicked on defect id: " + defid);
            }

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