package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC041 extends BaseClass {

    @Test(dataProvider = "tc041", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyFileAbove5MBInDefect(String projectName,
            String ReleaseName,
            String CycleName,
            String SuiteName,
            String TR,
            String summary,
            String status,
            String fileAddress
    ) throws InterruptedException {
        logger.info("****** Starting Test Case 041: Verify that files up to 5MB can be uploaded as defect attachments inside the defect *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the Execute Test Case tab");

            executeLandingPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded Project: " + projectName);

            executeLandingPage.expandRelease(ReleaseName);
            logger.info("Expanded Release: " + ReleaseName);

            executeLandingPage.expandSubTestCycle(CycleName);
            logger.info("Expanded Cycle: " + CycleName);

            Thread.sleep(3000);
            executeLandingPage.clickOnSuite(SuiteName);
            logger.info("Clicked on Suite: " + SuiteName);
            Thread.sleep(3000);

            executeLandingPage.clickTestRunById(TR);
            logger.info("Clicked on Test Run ID: " + TR);

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());
            individualTestRun.clickLinkDefect();
            logger.info("Clicked on link defect ");

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());

            linkDefectPage.clickNew();
            logger.info("Clicked On the New ");

            linkDefectPage.enterSummary(summary);
            logger.info("Entered Summary" + summary);

            linkDefectPage.selectStatus(status);
            logger.info("Clicked on status" + status);

            linkDefectPage.uploadFile(fileAddress);
            logger.info("<less than 5 mb uploaded");

            linkDefectPage.clickSave();
            logger.info("Clicked on save button");

            boolean popupVisible = linkDefectPage.isNotificationPopupDisplayed("Defect created and linked successfully.");
            if (popupVisible) {
                logger.info("Notification popup displayed successfully.");
            } else {
                logger.error("Notification popup not displayed or text does not match.");
            }

            Assert.assertTrue(popupVisible, "Notification popup not displayed or message mismatch.");

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
