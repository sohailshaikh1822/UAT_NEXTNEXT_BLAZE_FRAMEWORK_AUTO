package testCases.DefectTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import pageObjects.defectTab.CreateDefectPage;
import testBase.BaseClass;

public class TC019 extends BaseClass {

    @Test
    public void verifyCloseButtonWithoutChanges() throws InterruptedException {

        logger.info("****** Starting TC019 - CLOSE button without changes ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage landingPage = new DefectLandingPage(getDriver());
            landingPage.clickDefectTab();
            logger.info("Navigated to Defect page");

            landingPage.ClickDefectbyID("1");
            logger.info("Opened an existing defect");

            CreateDefectPage defectPage = new CreateDefectPage(getDriver());
            defectPage.clickClose();
            logger.info("Clicked CLOSE button");

            String expectedUrl = "https://webapp-stg-testnext.azurewebsites.net/defect";
            String currentUrl = getDriver().getCurrentUrl();

            Assert.assertEquals(currentUrl, expectedUrl,
                    "FAILED: CLOSE button did not navigate back to Defect page");

            logger.info("CLOSE button successfully navigated user to Defect page");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC019 Finished *************************");
    }
}
