package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC010 extends BaseClass {

    @Test(dataProvider = "tc010", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_text_input_in_Summary_and_Description_fields(
            String expectedUrlAfterClick,
            String Summary,
            String Description
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify text input in Summary and Description fields ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
                    "User did not navigate to expected URL.");
            logger.info("Navigated to Defect Page: " + actualUrl);
            Thread.sleep(3000);
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(3000);
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled: " + Summary);
            Thread.sleep(3000);

            createDefectPage.enterDescription(Description);
            logger.info("Description filled:\n" + Description);
            Thread.sleep(3000);

            boolean isVerified = createDefectPage.verifySummaryAndDescription(Summary, Description);
            Assert.assertTrue(isVerified, "Summary or Description text validation failed.");

            logger.info("Summary and Description fields accept user inputs verified successfully.");


        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify text input in Summary and Description fields *************");
    }
}
