package testCases.testPlanTabTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.time.Duration;

public class TC001 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectDropdownVisibility() throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Dropdown Visibility *****************");
        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.expandProjectSTG("STG- SPARK Modernization");
            logger.info("Expanded the project dropdown");

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

            WebElement toggleIcon = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//div[contains(@class,'project') and contains(.,'STG- SPARK Modernization')]//i[contains(@class,'toggle-icon')]")));

            wait.until(driver -> toggleIcon.getAttribute("style").contains("90deg"));

            String transform = toggleIcon.getAttribute("style");
            Assert.assertTrue(transform.contains("90deg"), "Project dropdown is not expanded/visible!");

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
