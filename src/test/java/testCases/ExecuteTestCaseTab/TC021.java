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

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            individualTestRun.clickLinkDefect();
            logger.info("Clicked on Link Defect");
            WaitUtils.waitFor2000Milliseconds();

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());

            linkDefectPage.enterDefectSearch(defectID.replaceAll("[^0-9]", ""));
            logger.info("Entered the defect id");
            WaitUtils.waitFor2000Milliseconds();

            linkDefectPage.clickSearchButton();
            logger.info("Searched the defect");
            WaitUtils.waitFor2000Milliseconds();

            linkDefectPage.clickRadioButtonBesideDefectId(defectID);
            logger.info("Clicked on defect id {}", defectID);
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.clickLink();
            logger.info("Defect linked successfully");
            WaitUtils.waitFor3000Milliseconds();

            individualTestRun.clickLinkDefect();
            logger.info("clicked on link defect");
            WaitUtils.waitFor2000Milliseconds();

            LinkDefectPage linkDefectPage2 = new LinkDefectPage(getDriver());
            linkDefectPage2.clickUnlinkButtonByDefectId(defectID);

            logger.info("Defect unlinked successfully");
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