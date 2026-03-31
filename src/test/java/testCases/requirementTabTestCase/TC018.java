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
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            WaitUtils.waitFor1000Milliseconds();

//            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Navigate to the project");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            logger.info("Navigated to Module");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickOnModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickNewModule();
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.setModuleName(feature);
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.saveModule();
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(feature);
            logger.info("clicked on specific module");
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage indivisualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            indivisualModulePage.enterName(name);
            WaitUtils.waitFor1000Milliseconds();

            indivisualModulePage.setActualDescription(descri);
            WaitUtils.waitFor1000Milliseconds();

            indivisualModulePage.clickSave();
            logger.info("click on save btn");
            WaitUtils.waitFor1000Milliseconds();

            WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            WaitUtils.waitFor1000Milliseconds();

            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='notification']")));
            logger.info("Success msg appears");
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