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

public class TC066 extends BaseClass {

    @Test(dataProvider = "tc066", dataProviderClass = ExecuteTestCaseDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
    public void  VerifyThataNotificationAppearsAutomaticallyWhenaDefectIsUnlinkedWithTheTestRun(
            String releaseName,
            String trID,
            String defectId
    ) throws InterruptedException
    {
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
            WaitUtils.waitFor1000Milliseconds();

            individualTestRun.clickLinkDefect();
            linkDefectPage.enterDefectSearch(defectId);
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.clickSearchButton();
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.clickRadioButtonBesideDefectId(defectId);
            linkDefectPage.clickLink();

            logger.info("Defect is linked successfully");
            WaitUtils.waitFor1000Milliseconds();
            WaitUtils.waitFor1000Milliseconds();

            individualTestRun.clickLinkDefect();
            linkDefectPage.clickUnlinkButtonByDefectId(defectId);
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.isNotificationPopupDisplayed("Yes");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.verifyDefectUnlinkedFromTRNotification(defectId,trID);
            logger.info("linking defect is verified successfully");

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
