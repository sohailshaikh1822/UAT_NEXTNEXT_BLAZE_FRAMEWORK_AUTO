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
import utils.WaitUtils;

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
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            WaitUtils.waitFor2000Milliseconds();
//            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Expanded project: " + project);
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickOnModule(epic);
            logger.info("Opened module: " + epic);
            requirementTabPage.clickArrowRightPointingForExpandModule(epic);

            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickOnModule(feature);
            logger.info("clicked on specific module");
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            addRequirementPage.clickAddRequirementBtn();
            WaitUtils.waitFor3000Milliseconds();
            addRequirementPage.setRequirementId(id);
            WaitUtils.waitFor3000Milliseconds();
            addRequirementPage.clickSave();
            logger.info("click on save btn");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='notification']")));
            logger.info("Success msg appears");
            WaitUtils.waitFor3000Milliseconds();
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
