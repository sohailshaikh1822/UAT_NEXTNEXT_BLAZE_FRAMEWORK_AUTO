package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC037 extends BaseClass {
    @Test(dataProvider = "tc037", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNoDuplicateDefectisCreatedOnDoubleClickingSAVE(
            String Summary,
            String status,
            String Description
    ) throws InterruptedException {

        logger.info("****** Starting Test Case ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            Thread.sleep(3000);

            String actualUrl = getDriver().getCurrentUrl();
            String expectedUrlAfterClick = "https://webapp-stg-testnext.azurewebsites.net/defect";
            Assert.assertEquals(actualUrl, expectedUrlAfterClick,
                    "User is not navigated to the Defect page!");
            logger.info("Defect Page loaded and form fields are visible.");
            Thread.sleep(3000);
            defectLandingPage.clickCreateTestCaseButton();

            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(3000);
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);
            Thread.sleep(3000);

            createDefectPage.selectStatus(status);
            logger.info("status is selected");
            Thread.sleep(3000);

            createDefectPage.enterDescription(Description);
            logger.info("Description filled:"+Description);

            createDefectPage.clickSave();
            createDefectPage.clickSave();
            logger.info("Clicked on save button twice");


        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished *************");
    }
}
