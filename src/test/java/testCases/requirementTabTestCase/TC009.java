package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.time.Duration;

public class TC009 extends BaseClass {

    @Test(dataProvider = "tc009", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRequirementUnderModule(
            String project,
            String epic,
            String feature,
            String id,
            String reqId
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Navigate to the project");
            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            logger.info("Navigated to Module");
            requirementTabPage.clickOnModule(feature);
            logger.info("clicked on specific module");
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            addRequirementPage.clickAddRequirementBtn();
            addRequirementPage.setRequirementId(id);
            addRequirementPage.clickSave();
            logger.info("click on save btn");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='notification']")));
            logger.info("Success msg appears");
            addRequirementPage.clickClose();
//            addRequirementPage.ClickYesPopup();
            boolean requirementVisible = wait.until(webDriver -> requirementTabPage.isRequirementVisible(reqId));
            Assert.assertTrue(requirementVisible, "newly created requirement is visble");
            logger.info("verified the requirement");
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
