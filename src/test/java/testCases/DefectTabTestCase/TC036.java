package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC036 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyBrowserBackButtonBehaviorFromDefectPage() throws InterruptedException {

        logger.info("****** Starting Test Case ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            Thread.sleep(3000);

            waitForPageLoad();

            getDriver().navigate().back();
            logger.info("Clicked on browser back button");



            getDriver().navigate().forward();
            logger.info("Clicked on browser forward button");

            Thread.sleep(3000);


            String actualUrl = getDriver().getCurrentUrl();
            String expectedUrlAfterClick = "https://webapp-stg-testnext.azurewebsites.net/defect";
            Assert.assertEquals(actualUrl, expectedUrlAfterClick,
                    "User is not navigated to the Defect page!");
            logger.info("Defect Page loaded and form fields are visible.");

        }
        catch (AssertionError ae)
        {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        }
        catch (Exception ex)
        {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished *************");
    }
}
