package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC048 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyDefectPageAccessibleAfterIdleSession() throws InterruptedException {

        logger.info("****** Starting Test Case TC048 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Navigated to Defect Landing Page");

            Thread.sleep(2000);
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Opened Create Defect page");

            CreateDefectPage createDefect = new CreateDefectPage(getDriver());

            Thread.sleep(2000);
            Assert.assertTrue(createDefect.textAreaSummaryIsDisplayed(),
                    "Summary field is NOT visible!");
            Assert.assertTrue(createDefect.dropdownSeverityIsDisplayed(),
                    "Severity dropdown is NOT visible!");

            logger.info("All required fields are visible. Page loaded successfully.");
            int idleSeconds = 10;
            logger.info("Idling for " + idleSeconds + " seconds...");
            Thread.sleep(idleSeconds * 1000L);

            String testSummary = "IdleSessionSummary_" + System.currentTimeMillis();
            String tempDescription = "Temporary Description to allow Save";
            createDefect.enterSummary(testSummary);
            logger.info("User entered Summary after idle time: " + testSummary);

            createDefect.enterDescription(tempDescription);
            logger.info("Entered Description");

            Thread.sleep(1000);
            createDefect.selectStatusByIndex(1);

            Thread.sleep(1000);
            createDefect.selectPriorityByIndex(1);

            createDefect.scrollToSaveButton();
            Thread.sleep(500);

            try {
                createDefect.clickSaveforNewDefect();
                logger.info("SAVE button was clickable after idle. GOOD SIGN.");
            } catch (Exception e) {
                Assert.fail("FAILED: SAVE button was NOT clickable after idle time!", e);
            }

            Thread.sleep(2000);

            String notificationText = createDefect.getNotificationText();

            Assert.assertFalse(notificationText.toLowerCase().contains("session"),
                    "FAILED: System suggests session timeout!");
            Assert.assertFalse(notificationText.toLowerCase().contains("login"),
                    "FAILED: Application redirected to login page!");

            logger.info("PASSED: Page still active, no session timeout occurred.");

        } catch (AssertionError e) {
            logger.error("ASSERTION FAILED: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC048 Finished Successfully ************");
    }
}
