package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC042 extends BaseClass {

    @Test(dataProvider = "tc042", dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_user_can_modify_and_resave_an_existing_defect(
            String expectedUrlAfterClick,
            String ID,
            String Summary
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify that user can modify and re-save an existing defect ********");

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

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(3000);

            defectLandingPage.ClickDefectbyID(ID);
            logger.info("Defect clicked: " + ID);
            Thread.sleep(3000);

            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled: " + Summary);
            Thread.sleep(3000);

            createDefectPage.clickSave();
            logger.info("Clicked on save button");
            Thread.sleep(3000);

            createDefectPage.verifySuccessNotification();

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify that user can modify and re-save an existing defect *************");
    }
}
