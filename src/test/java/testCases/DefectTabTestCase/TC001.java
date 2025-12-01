package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;

public class TC001 extends BaseClass {

    @Test()
    public void verifyNavigationToDefectPage() throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Navigated to Defect page");

            String actualUrl = getDriver().getCurrentUrl();
            String expectedUrlAfterClick = "https://webapp-stg-testnext.azurewebsites.net/defect";
            Assert.assertEquals(actualUrl, expectedUrlAfterClick,
                    "User is not navigated to the Defect page!");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
