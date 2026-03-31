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
            String epic,
            String feature,
            String id,
            String reqId
    ) throws InterruptedException {

        logger.info("****** Starting the Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            logger.info("Expanded module: " + epic);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(feature);
            logger.info("clicked on specific module");
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickAddRequirementBtn();
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            addRequirementPage.setRequirementId(id);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("click on save btn");
            WaitUtils.waitFor1000Milliseconds();

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WaitUtils.waitFor1000Milliseconds();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='notification']")));
            logger.info("Success msg appears");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            addRequirementPage.clickClose();
            WaitUtils.waitFor1000Milliseconds();
//            addRequirementPage.ClickYesPopup();

            boolean requirementVisible = wait.until(webDriver -> requirementTabPage.isRequirementVisible(reqId));
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertTrue(requirementVisible, "newly created requirement is visble");
            logger.info("verified the requirement");
            WaitUtils.waitFor1000Milliseconds();

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