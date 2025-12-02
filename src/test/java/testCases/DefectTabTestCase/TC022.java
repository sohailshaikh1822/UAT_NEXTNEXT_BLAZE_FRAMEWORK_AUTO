package testCases.DefectTabTestCase;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import pageObjects.defectTab.CreateDefectPage;
import testBase.BaseClass;

public class TC022 extends BaseClass {
    @Test
    public void verifyErrorOnSaveWithoutMandatoryFields() throws InterruptedException {

        logger.info("****** Starting TC022 - Error on SAVE without mandatory fields ******");

        try {

            login();
            logger.info("Logged in successfully");

            DefectLandingPage landingPage = new DefectLandingPage(getDriver());
            landingPage.clickDefectTab();
            logger.info("Navigated to Defect page");

            landingPage.clickCreateTestCaseButton();
            logger.info("Opened Create Defect form");

            CreateDefectPage createPage = new CreateDefectPage(getDriver());

            createPage.enterSummary("Automation defect summary");

            createPage.selectStatus("New");

            createPage.clickSave();
            logger.info("Clicked Save button");

            String expectedError = "Error: Please enter a valid Description.";

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
