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
import java.io.File;
import java.util.Arrays;
import java.util.List;

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
            WaitUtils.waitFor3000Milliseconds();
           IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
           WaitUtils.waitFor1000Milliseconds();
           individualTestRun.verifyExportButtonOfTrVisibleAndClickable();
//            ExportListener exportListener =new ExportListener(getDriver());
            ExportListener exportListener = new ExportListener(getDriver());

            // exportListener.selectExcelFileType();
            WaitUtils.waitFor3000Milliseconds();
            defectLandingPage.clickSaveExportButton();

            exportListener.pressSaveButton();

            WaitUtils.waitFor3000Milliseconds();

            String downloadDir = System.getProperty("user.home") +  File.separator + "Downloads";

            WaitUtils.waitFor3000Milliseconds();

            logger.info("Directory: " + downloadDir);
            WaitUtils.waitFor1000Milliseconds();
            String filePath = exportListener.getLatestFileFromDir(downloadDir);

            List<String> expectedColumns = Arrays.asList(
                    "Id",
                    "Pid",
                    "Name",
                    "Testcasepid",
                    "Testcasename",
                    "Testcaseversion",
                    "Releasename",
                    "Testcyclename",
                    "Testsuitename",
                    "Plannedstartdate",
                    "Plannedenddate",
                    "Status",
                    "Last Modified User"
            );

            exportListener.verifyExcelColumns(filePath, expectedColumns);
            logger.info("Successfully verified the excel column name");
        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
        }
        logger.info("************ TC084 Finished ***************");
    }
}
