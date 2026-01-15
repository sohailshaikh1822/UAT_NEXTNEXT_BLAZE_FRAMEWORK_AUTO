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
import utils.WaitUtils;

import java.time.Duration;

public class TC018 extends BaseClass {

    @Test(dataProvider = "tc018", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyNameAndDescription3(
            String project,
            String epic,
            String feature,
            String name,
            String descri
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
//            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Navigate to the project");
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            logger.info("Navigated to Module");
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickOnModule(epic);
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickNewModule();
            requirementTabPage.setModuleName(feature);
            requirementTabPage.saveModule();
            requirementTabPage.clickOnModule(feature);
            logger.info("clicked on specific module");
            IndividualModulePage indivisualModulePage = new IndividualModulePage(getDriver());
            indivisualModulePage.enterName(name);
            indivisualModulePage.setActualDescription(descri);
            indivisualModulePage.clickSave();
            logger.info("click on save btn");
            WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='notification']")));
            logger.info("Success msg appears");
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
