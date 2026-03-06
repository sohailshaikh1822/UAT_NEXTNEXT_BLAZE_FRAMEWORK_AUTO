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

public class TC022 extends BaseClass {

    @Test(dataProvider = "tc022", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNewDefectCreation(
            String projName,
            String releaseName,
            String testRun,
            String defSummary,
            String status,
            String description
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify new defect creation *****************");

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

            executeLandingPage.clickPlayActionById(testRun);
            logger.info("Clicked on Action Play button");
            WaitUtils.waitFor2000Milliseconds();

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());

            individualTestrun.clickLinkDefect();
            logger.info("Clicked on Link Defect button");
            WaitUtils.waitFor2000Milliseconds();

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());

            linkDefectPage.clickNew();
            logger.info("Clicked on New Defect button");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.enterSummary(defSummary);
            logger.info("Entered defect summary");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.selectStatus(status);
            logger.info("Selected defect status");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.enterDescription(description);
            logger.info("Entered defect description");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.clickSave();
            logger.info("Clicked on Save button");
            WaitUtils.waitFor2000Milliseconds();

            logger.info("Defect has successfully been created");

            String actualAlert = linkDefectPage.getNotificationPopUpText();
            String expectedAlert = "Defect created and linked successfully.";

            Assert.assertEquals(actualAlert, expectedAlert);
            logger.info("Assertion has completed");

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