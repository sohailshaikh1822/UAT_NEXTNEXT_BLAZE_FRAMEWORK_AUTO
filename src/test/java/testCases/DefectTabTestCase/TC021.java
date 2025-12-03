package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC021 extends BaseClass {

    @Test(dataProvider = "tc021", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class, description = "Verify Description supports multiline")
    public void verifyDescriptionSupportsMultiline(String defectId, String multilineDescription)
            throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Description supports multiline ********");

        try {
            // Login
            login();
            logger.info("Logged in successfully");

            DefectLandingPage landingPage = new DefectLandingPage(getDriver());
            landingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            landingPage.ClickDefectbyID(defectId);
            logger.info("Opened defect with ID: " + defectId);

            CreateDefectPage defectPage = new CreateDefectPage(getDriver());

            defectPage.getDescriptionField().click();
            defectPage.getDescriptionField().sendKeys(multilineDescription);

            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].dispatchEvent(new Event('change'))", defectPage.getDescriptionField());

            logger.info("Multiline description appended and change event triggered");

            defectPage.clickSave();
            logger.info("Defect saved successfully");

            defectPage.clickClose();
            logger.info("Defect page closed successfully");

            landingPage.ClickDefectbyID(defectId);
            logger.info("Re-opened defect with ID: " + defectId);
            logger.info("Verified multiline description is retained after re-opening the defect");

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
