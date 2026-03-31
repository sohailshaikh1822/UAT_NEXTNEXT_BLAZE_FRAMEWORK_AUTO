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
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");
            WaitUtils.waitFor1000Milliseconds();

//            requirementTabPage.clickDropdownToSelectProject(projectName);
//            logger.info("Expanded project: " + projectName);

            requirementTabPage.clickArrowRightPointingForExpandModule(moduleName);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(moduleName);
            logger.info("Opened module: " + moduleName);
            WaitUtils.waitFor1000Milliseconds();

            if (individualModulePage.isClickableNextArrow()) {
                individualModulePage.clickLastPageArrowBtn();
                WaitUtils.waitFor1000Milliseconds();
            }

            // -------- COUNT BEFORE ADD --------
            WebElement countBeforeAddElement =
                    getDriver().findElement(By.xpath("//span[@class='entry-info']"));
            WaitUtils.waitFor1000Milliseconds();

            int beforeCount = Integer.parseInt(
                    countBeforeAddElement.getText().replaceAll("[^0-9]", "")
            );
            logger.info("Initial requirement count: " + beforeCount);
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickAddRequirementBtn();
            logger.info("Clicked Add Requirement");
            WaitUtils.waitFor1000Milliseconds();

//            addRequirementPage.setRequirementId("Test_Sohail");

            addRequirementPage.clickSave();
            logger.info("save");
            WaitUtils.waitFor1000Milliseconds();

//            addRequirementPage.ClickYesPopup();

            logger.info("popup");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickClose();
            logger.info("close");
            WaitUtils.waitFor1000Milliseconds();

            if (individualModulePage.isClickableNextArrow()) {
                individualModulePage.clickLastPageArrowBtn();
                WaitUtils.waitFor1000Milliseconds();
            }

            addRequirementPage.clickClose();
            WaitUtils.waitFor1000Milliseconds();

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> {
                        String text = driver.findElement(
                                By.xpath("//span[@class='entry-info']")
                        ).getText();
                        int count = Integer.parseInt(text.replaceAll("[^0-9]", ""));
                        return count == beforeCount + 1;
                    });

            WaitUtils.waitFor1000Milliseconds();

            WebElement countAfterAddElement =
                    getDriver().findElement(By.xpath("//span[@class='entry-info']"));
            WaitUtils.waitFor1000Milliseconds();

            int afterAddCount = Integer.parseInt(
                    countAfterAddElement.getText().replaceAll("[^0-9]", "")
            );

            logger.info("Requirement count after adding: " + afterAddCount);
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertEquals(
                    afterAddCount,
                    beforeCount + 1,
                    "Requirement count did not increase after adding"
            );
            WaitUtils.waitFor1000Milliseconds();

            List<String> requirementIds = requirementTabPage.getRequirementIDs();
            WaitUtils.waitFor1000Milliseconds();

            String newRequirementId = requirementIds.get(requirementIds.size() - 1);
            logger.info("Newly linked requirement ID: " + newRequirementId);
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.unlinkRequirementById(newRequirementId);
            logger.info("Unlinked requirement: " + newRequirementId);
            WaitUtils.waitFor1000Milliseconds();

            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> {
                        String text = driver.findElement(
                                By.xpath("//span[@class='entry-info']")
                        ).getText();
                        int count = Integer.parseInt(text.replaceAll("[^0-9]", ""));
                        return count == beforeCount;
                    });

            WaitUtils.waitFor1000Milliseconds();

            WebElement countAfterRemoveElement =
                    getDriver().findElement(By.xpath("//span[@class='entry-info']"));
            WaitUtils.waitFor1000Milliseconds();

            int afterRemoveCount = Integer.parseInt(
                    countAfterRemoveElement.getText().replaceAll("[^0-9]", "")
            );

            logger.info("Requirement count after unlink: " + afterRemoveCount);
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertEquals(
                    afterRemoveCount,
                    beforeCount,
                    "Requirement count did not decrease after unlinking"
            );
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Verified requirement count increases and decreases correctly");
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