package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

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

            executeLandingPage.clickArrowRightPointingForExpandModule(projName);
            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(projName).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + projName);

            executeLandingPage.expandRelease(releaseName);
            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            executeLandingPage.clickPlayActionById(testRun);
            logger.info("clicked on Action Play button");

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());
            linkDefectPage.clickDefectId(defectID);
            logger.info("Clicked on the defect");
            linkDefectPage.clickDownloadIcon(1);
            logger.info("Downloaded the attachement");

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
