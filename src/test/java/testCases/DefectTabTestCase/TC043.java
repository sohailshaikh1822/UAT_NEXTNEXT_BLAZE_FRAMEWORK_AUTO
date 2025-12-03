package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC043 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyThatUserCanCancelEditAndKeepOldValues() throws InterruptedException {

        logger.info("****** Starting TC043 ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage landingPage = new DefectLandingPage(getDriver());
            landingPage.clickDefectTab();
            logger.info("Navigated to Defect page");

            landingPage.ClickDefectbyID("312");
            logger.info("Opened an existing defect");

            CreateDefectPage createPage = new CreateDefectPage(getDriver());

            Thread.sleep(3000);
            createPage.enterSummary("Automation defect summary");

            createPage.selectStatus("New");

            createPage.clickClose();
            logger.info("Clicked CLOSE button");

            String expectedUrl = "https://webapp-stg-testnext.azurewebsites.net/defect";
            String currentUrl = getDriver().getCurrentUrl();

            Assert.assertEquals(currentUrl, expectedUrl,
                    "FAILED: CLOSE button did not navigate back to Defect page");

            landingPage.ClickDefectbyID("312");
            logger.info("Opened an existing defect");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC043 Finished *************************");
    }
}
