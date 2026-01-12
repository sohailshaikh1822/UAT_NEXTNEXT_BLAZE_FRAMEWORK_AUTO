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

public class TC064 extends BaseClass {

    @Test(dataProvider = "tc063", dataProviderClass = ExecuteTestCaseDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
    public void VerifyPopupNotificationAppearsAutomaticallyWhenaDefectIsUnlinkedInTheTestRun(
            String releaseName,
            String trID,
            String defectId
    ) throws InterruptedException {
        logger.info("****** Starting Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
//            executeLandingPage.clickToSelectProject(projectName);

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickRelease(releaseName);
            logger.info("Clicked on the desired release from the dropdown");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickTestRunById(trID);
            logger.info("Clicked on test run id ");

            individualTestRun.clickLinkDefect();
            linkDefectPage.enterDefectSearch(defectId);
            linkDefectPage.clickSearchButton();
            linkDefectPage.clickRadioButtonBesideDefectId(defectId);
            linkDefectPage.clickLink();
            logger.info("Defect is linked successfully");
            individualTestRun.clickSaveButton();

            individualTestRun.clickLinkDefect();
            linkDefectPage.clickUnlinkButtonByDefectId(defectId);

            linkDefectPage.isNotificationPopupDisplayed("Yes");
            linkDefectPage.verifyDefectUnlinkedPopup(defectId,trID);
            logger.info("Unlinking defect is verified successfully");
            WaitUtils.waitFor1000Milliseconds();

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
