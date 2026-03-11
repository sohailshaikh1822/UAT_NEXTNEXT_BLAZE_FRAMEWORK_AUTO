package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.ExportListener;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import javax.swing.text.DefaultEditorKit;

public class TC084 extends BaseClass {

    @Test(
            dataProvider = "tc080",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyNavigationWorksForMultipleTestRunUpdateNotifications(
            String releaseName,
            String trId ,
            String defectId
    ) throws InterruptedException
    {
        logger.info("*************** Starting TC084 *****************");
        try {
            login();
            logger.info("Logged in successfully");
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            NotificationsListener notificationsListener=new NotificationsListener(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked Execute Test Case tab");
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickRelease(releaseName);
            logger.info("Selected release: " + releaseName);
            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickTestRunById(trId);
            logger.info("Clicked Selected Id");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();
           IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
           WaitUtils.waitFor1000Milliseconds();
           individualTestRun.verifyExportButtonOfTrVisibleAndClickable();
            WaitUtils.waitFor3000Milliseconds();
            ExportListener exportListener =new ExportListener(getDriver());

            exportListener.selectPdfFileType();
            defectLandingPage.clickSaveExportButton();


        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }
        logger.info("************ TC084 Finished ***************");
    }
}
