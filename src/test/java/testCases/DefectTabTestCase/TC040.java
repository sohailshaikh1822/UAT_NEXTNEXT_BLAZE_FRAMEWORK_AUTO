package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC040 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyErrorClearsAfterCorrectingSummary() throws InterruptedException {

        logger.info("****** Starting Test Case TC040 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Navigated to Defect Landing Page");
            Thread.sleep(2000);

            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked Create Defect button");

            CreateDefectPage createDefect = new CreateDefectPage(getDriver());

            createDefect.enterSummary(" ");
            logger.info("Summary left blank");

            Thread.sleep(500);
            createDefect.scrollToSaveButton();

            Thread.sleep(800);
            createDefect.clickSaveforNewDefect();
            logger.info("Clicked SAVE to trigger summary blank error");

            Thread.sleep(1500);
            String notificationText = createDefect.getNotificationText();
            Assert.assertEquals(notificationText.trim(), "Error: Summary cannot be blank.",
                    "FAILED: Expected Summary blank error message not shown!");

            logger.info("PASSED: Summary error message displayed correctly");

            String validSummary = "CorrectedSummary_" + System.currentTimeMillis();
            String tempDescription = "Temporary Description to allow Save";

            createDefect.enterSummary(validSummary);
            logger.info("Entered valid Summary: " + validSummary);

            createDefect.enterDescription(tempDescription);
            logger.info("Entered Description");

            createDefect.selectStatus("Closed");
            createDefect.selectPriority("Medium");

            createDefect.scrollToSaveButton();
            Thread.sleep(800);

            createDefect.clickSave();
            logger.info("Clicked SAVE after correcting summary");

            Thread.sleep(2000);

            String notificationAfterFix = createDefect.getSuccessNotificationText();

            Assert.assertNotEquals(notificationAfterFix.trim(), "Error: Summary cannot be blank.",
                    "FAILED: Summary error message still visible after correction!");

            logger.info("PASSED: Summary error cleared successfully");

            Assert.assertFalse(notificationAfterFix.contains("Error"),
                    "FAILED: Unexpected error still present after providing valid data!");

            logger.info("PASSED: Defect saved successfully with corrected Summary");

        } catch (AssertionError e) {
            logger.error("ASSERTION FAILED: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC040 Finished Successfully ************");
    }
}
