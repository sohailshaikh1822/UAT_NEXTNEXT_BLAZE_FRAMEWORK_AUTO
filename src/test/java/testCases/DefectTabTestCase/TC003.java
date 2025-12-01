package testCases.DefectTabTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;

import java.time.Duration;

public class TC003 extends BaseClass {

    @Test
    public void verifyDefectPageNotAccessibleWithoutLogin() throws InterruptedException {

        logger.info("****** Starting TC003: Verify Deep Link Access Restriction *****************");

        try {

            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Navigated to Defect page");

            String defectPageUrl = getDriver().getCurrentUrl();
            logger.info("Stored Defect Page URL: " + defectPageUrl);

            logout();
            logger.info("Logged out successfully");

            ((WebDriver) getDriver()).switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
            getDriver().get(defectPageUrl);
            logger.info("Trying to open Defect page URL without login in new tab");

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));

            String actualUrl = getDriver().getCurrentUrl();
            String expectedUrlFragment = "login";

            Assert.assertTrue(actualUrl.contains(expectedUrlFragment),
                    "User SHOULD be redirected to login page, but is not!");
            logger.info("User is redirected to login page as expected when accessing Defect page without login");

        } catch (AssertionError e) {
            logger.error("Assertion Failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                login();
                logger.info("Re-logged in successfully for safe teardown.");
            } catch (Exception e) {
                logger.warn("Re-login failed in finally block: " + e.getMessage());
            }
        }
        logger.info("************ TC003 Finished Successfully *************************");
    }
}
