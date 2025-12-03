package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import javax.swing.*;

public class TC044 extends BaseClass {
    @Test( retryAnalyzer = RetryAnalyzer.class)
    public void VerifyThatDescriptionSupportsCopyPasteOperations() throws InterruptedException {

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


            defectLandingPage.clickCreateTestCaseButton();

            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            Thread.sleep(3000);
            createDefectPage.enterSummary("Automation defect summary");

            createDefectPage.selectStatus("New");

            createDefectPage.enterDescription("description for defect");
            Actions actions = new Actions(getDriver());
            actions.click(createDefectPage.getDescriptionField())
                    .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                    .keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL)
                    .keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL)
                    .perform();
            logger.info("Copied & pasted Description text");

            createDefectPage.clickSave();
            logger.info("Clicked on save button");


        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC044 Finished *************************");
    }
}