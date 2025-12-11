package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import DataProviders.DefectTabTestCaseDataProvider;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC041 extends BaseClass {

    @Test(dataProvider = "tc041", dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void Verify_that_Summary_field_dosent_trims_leading_and_trailing_spaces(
            String expectedUrlAfterClick,
            String ID,
            String Summary
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify that Summary field dosen't trims leading and trailing spaces ********");

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
            WaitUtils.waitFor2000Milliseconds();;

            defectLandingPage.ClickDefectbyID(ID);
            logger.info("Defect clicked: " + ID);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled: " + Summary);
            WaitUtils.waitFor2000Milliseconds();;

            String savedSummary = createDefectPage.getRawSummary();
            logger.info("Saved Summary captured: '" + savedSummary + "'");
            WaitUtils.waitFor2000Milliseconds();;

            Assert.assertEquals(
                    savedSummary,
                    Summary,
                    "FAILED: Summary saved does not match the exact user input including spaces."
            );

            logger.info("Summary verified with exact spacing as entered");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify that Summary field dosen't trims leading and trailing spaces *************");
    }
}
