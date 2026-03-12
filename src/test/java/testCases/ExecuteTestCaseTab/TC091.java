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

public class TC091 extends BaseClass {

    @Test(
            dataProvider = "tc091",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyPDFFleExportFunctionalityOfTestLogDetails(
            String releaseName,
            String trId
    ) throws InterruptedException
    {
        logger.info("*************** Starting TC091 *****************");
        try {
            login();
            logger.info("Logged in successfully");
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked Execute Test Case tab");
            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickRelease(releaseName);
            logger.info("Selected release: " + releaseName);
            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickTestRunById(trId);
            logger.info("Clicked Selected Id");
            IndividualTestRun testRun= new IndividualTestRun(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            testRun.clickTabTestLogs();
            logger.info("click on export button");
            WaitUtils.waitFor2000Milliseconds();
            testRun.clickExportButton();
            logger.info("select dropdown");
            WaitUtils.waitFor2000Milliseconds();
            testRun.selectPDFFileType();
            WaitUtils.waitFor2000Milliseconds();
            testRun.clickCloseButton();
            logger.info("clicked on closed button");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }
        logger.info("************ TC089 Finished *************************");
    }
}
