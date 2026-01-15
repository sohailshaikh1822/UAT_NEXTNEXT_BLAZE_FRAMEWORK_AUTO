package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.time.Duration;

public class TC026 extends BaseClass {

    @Test(dataProvider = "tc026", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyPlaceholderOfDescription(
            String epic,
            String feature,
            String descri
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *******");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            logger.info("Navigated to Module");
            requirementTabPage.clickOnModule(feature);
            logger.info("clicked on specific module");
            WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
            IndividualModulePage indivisualModulePage = new IndividualModulePage(getDriver());
            indivisualModulePage.clearActualDescription();
            indivisualModulePage.clickInputTitle();
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//em[normalize-space()='Click to add description']")));
            logger.info("Placeholder-Click to add description is verified");

            indivisualModulePage.clickSave();

            logger.info("Module saved successfully");
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
