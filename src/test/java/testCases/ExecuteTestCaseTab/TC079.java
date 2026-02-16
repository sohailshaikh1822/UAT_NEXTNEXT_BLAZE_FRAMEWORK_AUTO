package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;


public class TC079 extends BaseClass {

    @Test(
            dataProvider = "tc078",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyNavigationToTestRunDetailsPageOnClickingUnLinkedDefectNotification(
            String releaseName,
            String trId ,
            String defectId
    ) throws InterruptedException
    {
        logger.info("*************** Starting TC079 *****************");
        try {
            login();
            logger.info("Logged in successfully");
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            NotificationsListener notificationsListener=new NotificationsListener(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked Execute Test Case tab");
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickRelease(releaseName);
            logger.info("Selected release: " + releaseName);
            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickTestRunById(trId);
            logger.info("Clicked Selected Id");
            IndividualTestRun individualTestRun= new IndividualTestRun(getDriver());
            individualTestRun.clickLinkDefect();
            WaitUtils.waitFor2000Milliseconds();
            logger.info("Clicked Linked Defect Button");
            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());
            linkDefectPage.clickUnlinkButtonByDefectId(defectId);
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickNotificationById(defectId);
            logger.info("Clicked the particular defect through navigation ");
        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }
        logger.info("************ TC079 Finished *************************");
    }
}
