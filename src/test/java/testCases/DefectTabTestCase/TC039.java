package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC039 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyCancelDoesNotCreateDefect() throws InterruptedException {

        logger.info("****** Starting Test Case TC039 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("User navigated to Defect Landing Page");

            WaitUtils.waitFor1000Milliseconds();
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked Create Defect");

            CreateDefectPage createDefect = new CreateDefectPage(getDriver());

            String tempSummary = "AutoTest_CancelDefect_" + System.currentTimeMillis();
            String tempDescription = "Temporary Description - Should Not Be Saved";

            WaitUtils.waitFor500Milliseconds();;
            createDefect.enterSummary(tempSummary);
            logger.info("Entered Summary: " + tempSummary);

            createDefect.enterDescription(tempDescription);
            logger.info("Entered Description");

            WaitUtils.waitFor1000Milliseconds();
            createDefect.selectSeverityByIndex(1);

            WaitUtils.waitFor1000Milliseconds();
            createDefect.selectPriorityByIndex(1);
            logger.info("Filled mandatory details WITHOUT saving");

            WaitUtils.waitFor500Milliseconds();;
            createDefect.clickClose();
            logger.info("Clicked CLOSE without saving");

            WaitUtils.waitFor1000Milliseconds();
            createDefect.clickPopupYes();
            logger.info("Confirmed to close without saving");

            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.enterSummary(tempSummary);
            WaitUtils.waitFor500Milliseconds();;
            defectLandingPage.clickSearchButton();
            logger.info("Searching for unsaved defect on listing page");

            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.clickLastPageArrow();
            logger.info("Navigated to last page of defect listing");

            WaitUtils.waitFor2000Milliseconds();
            String pageText = getDriver().getPageSource();
            boolean defectFound = pageText.contains(tempSummary);
            Assert.assertFalse(defectFound,
                    "FAILED Defect was found even after cancelling! SUMMARY: " + tempSummary);

            logger.info("PASSED  No defect found with Summary: " + tempSummary);

        } catch (AssertionError e) {
            logger.error("Assertion Failure: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Exception Occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC039 Finished Successfully ************");
    }
}
