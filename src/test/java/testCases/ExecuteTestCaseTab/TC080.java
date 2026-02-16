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

public class TC080 extends BaseClass {

    @Test(
            dataProvider = "tc078",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyNavigationWorksForMultipleTestRunUpdateNotifications(
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
            individualTestRun.selectStatus("Passed");
            logger.info("Update the status");
            WaitUtils.waitFor2000Milliseconds();
            individualTestRun.selectCheckAllStepsCheckbox();
            individualTestRun.selectDropdownStatusBesidesUpdate("Passed");
            WaitUtils.waitFor3000Milliseconds();
            logger.info("Update the status");
            individualTestRun.clickUpdate();
            notificationsListener.clickNotificationIcon();
            WaitUtils.waitFor2000Milliseconds();
            notificationsListener.clickNotificationById(trId);
            logger.info("Clicked the particular TrId through navigation ");

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
