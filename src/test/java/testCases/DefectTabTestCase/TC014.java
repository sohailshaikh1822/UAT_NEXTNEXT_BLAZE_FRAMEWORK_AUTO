package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC014 extends BaseClass {
    @Test(dataProvider = "tc014", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyErrorMessageWhenMandatoryFieldIsMissing(
            String expectedUrlAfterClick,
            String Summary,
            String description
    ) throws InterruptedException {

        logger.info("****** Starting Test Case ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertNotNull(actualUrl);
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
                    "User did not navigate to the expected Defect Page URL.");
            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);
            logger.info("Defect Page loaded and form fields are visible.");
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            WaitUtils.waitFor2000Milliseconds();;
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.enterDescription(description);
            logger.info("description enetered");
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.clickSave();
            logger.info("Clicked on save button");
            createDefectPage.verifyStatusErrorMessage();
            logger.info("verified:Status manadatory error message displayed");
        }
        catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished *************");
    }
}
