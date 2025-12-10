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

public class TC021 extends BaseClass {

    @Test(dataProvider = "tc021", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyUnlinkDefectButton(
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

            IndividualTestRun individualTestrun = new IndividualTestRun(getDriver());
            executeLandingPage.clickPlayActionById(testRun);
            logger.info("clicked on Action Play button");

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            individualTestRun.clickLinkDefect();
            logger.info("Clicked on link defect ");

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());
            linkDefectPage.enterDefectSearch(defectID.replaceAll("[^0-9]", ""));
            logger.info("Entered the defect defect id");

            linkDefectPage.clickSearchButton();
            logger.info("Searched the defect");

            linkDefectPage.clickRadioButtonBesideDefectId(defectID);
            logger.info("clicked on defect id {}", defectID);

            linkDefectPage.clickLink();
            WaitUtils.waitFor1000Milliseconds();
            logger.info("defect is linked successfully");

            individualTestrun.clickLinkDefect();

            linkDefectPage.clickUnlinkButtonByDefectId(defectID);
            logger.info("Defect is unlinked successfully");
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
