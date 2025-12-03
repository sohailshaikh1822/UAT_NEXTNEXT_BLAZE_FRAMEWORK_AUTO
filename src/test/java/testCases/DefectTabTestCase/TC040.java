package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;

public class TC040 extends BaseClass {

    @Test()
    public void verifyErrorClearsAfterCorrectingSummary() throws InterruptedException {

        logger.info("****** Starting Test Case TC040 ******");

        try {
            // ---------------------------------------
            // Step 1: Login & Navigate to Defect Page
            // ---------------------------------------
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Navigated to Defect Landing Page");

            // Click Create Defect
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked Create Defect button");

            CreateDefectPage createDefect = new CreateDefectPage(getDriver());

            // ---------------------------------------
            // Step 2: Leave Summary blank → Trigger Error
            // ---------------------------------------
            createDefect.enterSummary(" "); // Blank summary
            logger.info("Summary left blank");

            // Scroll to SAVE button before clicking
            createDefect.scrollToSaveButton();
            Thread.sleep(800);

            createDefect.clickSaveforNewDefect();
            logger.info("Clicked SAVE to trigger summary blank error");

            Thread.sleep(1500);

            // Validate error message
            String notificationText = createDefect.getSuccessNotificationText();
            Assert.assertEquals(notificationText.trim(), "Error: Summary cannot be blank.",
                    "FAILED: Expected Summary blank error message not shown!");

            logger.info("PASSED: Summary error message displayed correctly");

            // ---------------------------------------
            // Step 3: Enter valid Summary + Description
            // ---------------------------------------
            String validSummary = "CorrectedSummary_" + System.currentTimeMillis();
            String tempDescription = "Temporary Description to allow Save";

            createDefect.enterSummary(validSummary);
            logger.info("Entered valid Summary: " + validSummary);

            createDefect.enterDescription(tempDescription);
            logger.info("Entered Description");

            // Required dropdown fields
            createDefect.selectStatus("Closed");
            createDefect.selectPriority("Medium");

            // Scroll to SAVE again
            createDefect.scrollToSaveButton();
            Thread.sleep(800);

            // ---------------------------------------
            // Step 4: Save Again After Fixing Summary
            // ---------------------------------------
            createDefect.clickSave();
            logger.info("Clicked SAVE after correcting summary");

            Thread.sleep(2000);

            // ---------------------------------------
            // Step 5: Ensure Error Message Cleared
            // ---------------------------------------
            String notificationAfterFix = createDefect.getSuccessNotificationText();

            Assert.assertNotEquals(notificationAfterFix.trim(), "Error: Summary cannot be blank.",
                    "FAILED: Summary error message still visible after correction!");

            logger.info("PASSED: Summary error cleared successfully");

            // Ensure no other error appeared
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
