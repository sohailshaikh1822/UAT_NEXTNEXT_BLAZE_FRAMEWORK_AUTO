package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import pageObjects.defectTab.CreateDefectPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC022 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyErrorOnSaveWithoutMandatoryFields() throws InterruptedException {

        logger.info("****** Starting TC022 - Error on SAVE without mandatory fields ******");

        try {

            login();
            logger.info("Logged in successfully");

            DefectLandingPage landingPage = new DefectLandingPage(getDriver());
            landingPage.clickDefectTab();
            logger.info("Navigated to Defect page");

            WaitUtils.waitFor2000Milliseconds();

            landingPage.clickCreateTestCaseButton();
            logger.info("Opened Create Defect form");

            CreateDefectPage createPage = new CreateDefectPage(getDriver());

            WaitUtils.waitFor500Milliseconds();;
            createPage.enterSummary("Automation defect summary");
            WaitUtils.waitFor2000Milliseconds();

            createPage.selectStatusByIndex(1);
            WaitUtils.waitFor2000Milliseconds();
            createPage.clearDescriptionField();
            logger.info("Cleared Description field to leave it empty");

            WaitUtils.waitFor500Milliseconds();;
            createPage.clickSaveforNewDefect();
            logger.info("Clicked Save button");

            String expectedError = "Error: Description cannot be blank.";

            boolean isErrorDisplayed = createPage.verifySuccessMessage(expectedError);

            Assert.assertTrue(isErrorDisplayed,
                    "FAILED: Expected popup not displayed OR text mismatch.");

            logger.info("Validation message verified successfully: " + expectedError);

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC022 Finished *************************");
    }
}
