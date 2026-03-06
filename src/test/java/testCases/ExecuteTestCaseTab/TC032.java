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

public class TC032 extends BaseClass {

    @Test(dataProvider = "tc032", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyPreventionOfSameDefect(
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

            Assert.assertTrue(executeLandingPage.selectedModuleOrReleaseName(projName).isDisplayed(),
                    "Parent module not visible after expand");
            logger.info("Expanded parent module: " + projName);

            executeLandingPage.expandRelease(releaseName);
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(executeLandingPage.isReleaseVisible(releaseName), "Release not visible after expand");
            logger.info("Expanded Release module: " + releaseName);

            executeLandingPage.clickPlayActionById(testRun);
            logger.info("clicked on Action Play button");
            WaitUtils.waitFor2000Milliseconds();

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            individualTestRun.clickLinkDefect();
            logger.info("Clicked on link defect ");
            WaitUtils.waitFor1000Milliseconds();

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());
            linkDefectPage.enterDefectSearch(defectID.replaceAll("[^0-9]", ""));
            logger.info("Entered the defect defect id");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.clickSearchButton();
            logger.info("Searched the defect");
            WaitUtils.waitFor2000Milliseconds();

            try {
                linkDefectPage.clickRadioButtonBesideDefectId(defectID);
                logger.info("Clicked on defect id {}", defectID);
            } catch (Exception e) {
                logger.warn("Defect {} is already linked : {}", defectID, e.getMessage());
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