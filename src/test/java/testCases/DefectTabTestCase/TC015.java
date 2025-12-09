package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC015 extends BaseClass {
    @Test(dataProvider = "tc015", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifySavingWithOnlyMandatoryFieldsFilled(
            String expectedUrlAfterClick,
            String Summary,
            String status,
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
            Thread.sleep(3000);
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(3000);
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);
            Thread.sleep(3000);

            createDefectPage.selectStatusByIndex(Integer.parseInt(status));
            logger.info("Status selected:"+status);
            Thread.sleep(3000);

            createDefectPage.enterDescription(description);
            logger.info("Description filled:"+description);
            Thread.sleep(3000);

            createDefectPage.clickSave();
            logger.info("Clicked on save button");
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
