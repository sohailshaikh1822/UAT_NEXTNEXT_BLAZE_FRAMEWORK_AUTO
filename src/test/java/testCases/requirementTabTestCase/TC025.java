package testCases.requirementTabTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class TC025 extends BaseClass {

    @Test(
            dataProvider = "tc025",
            dataProviderClass = DataProviders.RequirementDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void verifyLinkedRequirementCountIncreasesAndDecreases(
            String projectName, String moduleName) throws InterruptedException {

        logger.info("************ Starting Test Case: Verify linked requirement count increases and decreases *****************");

        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

//            requirementTabPage.clickDropdownToSelectProject(projectName);
//            logger.info("Expanded project: " + projectName);

            requirementTabPage.clickArrowRightPointingForExpandModule(moduleName);
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickOnModule(moduleName);
            logger.info("Opened module: " + moduleName);

            if (individualModulePage.isClickableNextArrow()) {
                individualModulePage.clickLastPageArrowBtn();
            }

            // -------- COUNT BEFORE ADD --------
            WebElement countBeforeAddElement =
                    getDriver().findElement(By.xpath("//span[@class='entry-info']"));
            int beforeCount = Integer.parseInt(
                    countBeforeAddElement.getText().replaceAll("[^0-9]", "")
            );
            logger.info("Initial requirement count: " + beforeCount);

            // -------- ADD REQUIREMENT --------
            addRequirementPage.clickAddRequirementBtn();
            logger.info("Clicked Add Requirement");

//            addRequirementPage.setRequirementId("Test_Sohail");
            addRequirementPage.clickSave();
            logger.info("save");
//            addRequirementPage.ClickYesPopup();
            logger.info("popup");
            addRequirementPage.clickClose();
            logger.info("close");

            if (individualModulePage.isClickableNextArrow()) {
                individualModulePage.clickLastPageArrowBtn();
            }
            addRequirementPage.clickClose();

            // -------- WAIT FOR COUNT TO INCREASE --------
            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> {
                        String text = driver.findElement(
                                By.xpath("//span[@class='entry-info']")
                        ).getText();
                        int count = Integer.parseInt(text.replaceAll("[^0-9]", ""));
                        return count == beforeCount + 1;
                    });

            WebElement countAfterAddElement =
                    getDriver().findElement(By.xpath("//span[@class='entry-info']"));
            int afterAddCount = Integer.parseInt(
                    countAfterAddElement.getText().replaceAll("[^0-9]", "")
            );

            logger.info("Requirement count after adding: " + afterAddCount);
            Assert.assertEquals(
                    afterAddCount,
                    beforeCount + 1,
                    "Requirement count did not increase after adding"
            );

            // -------- GET NEW REQUIREMENT ID --------
            List<String> requirementIds = requirementTabPage.getRequirementIDs();
            String newRequirementId = requirementIds.get(requirementIds.size() - 1);
            logger.info("Newly linked requirement ID: " + newRequirementId);

            requirementTabPage.unlinkRequirementById(newRequirementId);
            logger.info("Unlinked requirement: " + newRequirementId);

            // -------- WAIT FOR COUNT TO DECREASE --------
            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> {
                        String text = driver.findElement(
                                By.xpath("//span[@class='entry-info']")
                        ).getText();
                        int count = Integer.parseInt(text.replaceAll("[^0-9]", ""));
                        return count == beforeCount;
                    });

            WebElement countAfterRemoveElement =
                    getDriver().findElement(By.xpath("//span[@class='entry-info']"));
            int afterRemoveCount = Integer.parseInt(
                    countAfterRemoveElement.getText().replaceAll("[^0-9]", "")
            );

            logger.info("Requirement count after unlink: " + afterRemoveCount);

            Assert.assertEquals(
                    afterRemoveCount,
                    beforeCount,
                    "Requirement count did not decrease after unlinking"
            );

            logger.info("Verified requirement count increases and decreases correctly");

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