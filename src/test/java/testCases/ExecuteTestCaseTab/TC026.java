package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC026 extends BaseClass {

    @Test(dataProvider = "tc026", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyDefectAttachement(
            String projName,
            String releaseName,
            String testRun,
            String defectID
    ) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Expand feature of sub test cycle *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickToSelectProject(projName);
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(projName).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + projName);

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.expandRelease(releaseName);
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickPlayActionById(testRun);
            logger.info("Clicked on Action Play button");

            WaitUtils.waitFor2000Milliseconds();

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());
            linkDefectPage.clickDefectId(defectID);
            logger.info("Clicked on the defect");

            WaitUtils.waitFor2000Milliseconds();

            linkDefectPage.clickDownloadIcon(1);
            logger.info("Downloaded the attachment");

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