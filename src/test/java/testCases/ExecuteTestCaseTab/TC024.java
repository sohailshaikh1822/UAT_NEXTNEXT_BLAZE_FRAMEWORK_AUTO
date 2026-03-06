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

@Test(dataProvider = "tc024", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

public class TC024 extends BaseClass {

    public void verifyEvidenceAttachment(
            String projName,
            String releaseName,
            String testRun,
            String defSummary,
            String status,
            String description
    ) throws Exception {

        logger.info("****** Starting Test Case: Verify Expand feature of sub test cycle *****************");

        try {

            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor2000Milliseconds();

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickToSelectProject(projName);
            WaitUtils.waitFor2000Milliseconds();

            Assert.assertTrue(
                    executeLandingPage.selectedModuleOrReleaseName(projName).isDisplayed(),
                    "Parent module not visible after expand"
            );
            logger.info("Expanded parent module: " + projName);

            executeLandingPage.expandRelease(releaseName);
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(
                    executeLandingPage.isReleaseVisible(releaseName),
                    "Release not visible after expand"
            );
            logger.info("Expanded Release module: " + releaseName);

            WaitUtils.waitFor1000Milliseconds();

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());

            executeLandingPage.clickPlayActionById(testRun);
            logger.info("clicked on Action Play button");
            WaitUtils.waitFor2000Milliseconds();

            individualTestrun.clickLinkDefect();
            logger.info("clicked on linkdefect button");
            WaitUtils.waitFor1000Milliseconds();

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());

            linkDefectPage.clickNew();
            logger.info("clicked on new defect button");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.enterSummary(defSummary);
            logger.info("Entered defect Summary");

            linkDefectPage.selectStatus(status);
            logger.info("Select status");

            linkDefectPage.enterDescription(description);
            logger.info("Entered description");

            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.uploadFile("uploadInDefect.png");
            logger.info("File uploaded successfully");

            WaitUtils.waitFor2000Milliseconds();

            linkDefectPage.clickSave();
            logger.info("New Defect has been created with evidence");

            WaitUtils.waitFor2000Milliseconds();

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