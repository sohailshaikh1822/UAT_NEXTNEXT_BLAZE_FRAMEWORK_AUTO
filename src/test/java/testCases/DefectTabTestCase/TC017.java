package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.time.Duration;

public class TC017 extends BaseClass {

    @Test(dataProvider = "tc017", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_success_message_content_after_save(
            String expectedUrlAfterClick,
            String Summary,
            String Severity,
            String Type,
            String Category,
            String Priority,
            String Status,
            String AssignTo,
            String Description
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify success message content after save ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");

            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertNotNull(actualUrl);
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick), "User did not navigate to the expected Defect Page URL.");
            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);

            defectLandingPage.clickCreateTestCaseButton();
            logger.info("Clicked on Create Defect Button");

            // ◆ IMPORTANT: Wait for Create Defect form to fully load
            new WebDriverWait(getDriver(), Duration.ofSeconds(40))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("DefSummary")));

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());

            createDefectPage.enterSummary(Summary);
            createDefectPage.selectSeverity(Severity);
            createDefectPage.selectType(Type);
            createDefectPage.selectCategory(Category);
            createDefectPage.selectPriority(Priority);
            createDefectPage.selectStatus(Status);
            createDefectPage.selectAssignTo(AssignTo);
            createDefectPage.enterDescription(Description);

            createDefectPage.clickSave();

            String actualMsg = createDefectPage.getSuccessNotificationText();
            Assert.assertEquals(actualMsg, "Defect created successfully.", "Success message mismatch!");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify success message content after save *************");
    }
}
