package testCases.requirementTabTestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.requirementTab.AddRequirementPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;
import utils.WaitUtils;
public class TC025 extends BaseClass {

    @Test(dataProvider = "tc025", dataProviderClass = DataProviders.RequirementDataProvider.class, description = "Verify linked requirement count increases and decreases", retryAnalyzer = RetryAnalyzer.class)
    public void verifyLinkedRequirementCountIncreasesAndDecreases(String projectName, String moduleName)
            throws InterruptedException {
        logger.info(
                "************ Starting Test Case: Verify linked requirement count increases and decreases *****************");

        try {

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickArrowRightPointingForExpandModule(projectName);
            logger.info("Expanded project: " + projectName);

            requirementTabPage.clickOnModule(moduleName);
            logger.info("Opened module: " + moduleName);

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            if (individualModulePage.isClickableNextArrow()) {
                individualModulePage.clickLastPageArrowBtn();
            }
            WebElement countBeforeAdd = getDriver().findElement(By.xpath("//span[@class='entry-info']"));

            WaitUtils.waitFor2000Milliseconds();
           
            int beforeCount = Integer.parseInt(countBeforeAdd.getText().replaceAll("[^0-9]", ""));
            logger.info("Initial requirement count: " + beforeCount);
            addRequirementPage.ClickYesPopup();

            addRequirementPage.clickAddRequirementBtn();
            String newRequirementName = "Test_Sohail";
            addRequirementPage.setRequirementId(newRequirementName);
            addRequirementPage.clickSave();

            WaitUtils.waitFor2000Milliseconds();
            addRequirementPage.clickClose();
            WaitUtils.waitFor2000Milliseconds();;

            if (individualModulePage.isClickableNextArrow()) {
                individualModulePage.clickLastPageArrowBtn();
            }
            RequirementTabPage requirementTabPage1 = new RequirementTabPage(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            List<String> afterAddList = requirementTabPage1.getRequirementIDs();
            WebElement countAfterAdd = getDriver().findElement(By.xpath("//span[@class='entry-info']"));
            int afterAddCount = Integer.parseInt(countAfterAdd.getText().replaceAll("[^0-9]", ""));
            logger.info("Requirement count after adding: " + afterAddCount);
            Assert.assertEquals(afterAddCount, beforeCount + 1, "Requirement count did not increase after adding");
            String newRequirementId = afterAddList.getLast();
            logger.info("Newly linked requirement ID: " + newRequirementId);
            requirementTabPage1.unlinkRequirementById(newRequirementId, afterAddCount);
            logger.info("Unlinked requirement: " + newRequirementId);
            WaitUtils.waitFor2000Milliseconds();;
            WaitUtils.waitFor2000Milliseconds();
           
            int afterRemoveCount = Integer.parseInt(countAfterAdd.getText().replaceAll("[^0-9]", ""));

            logger.info("Requirement count after unlink: " + afterRemoveCount);

            Assert.assertEquals(afterRemoveCount, beforeCount, "Requirement count did not decrease after unlinking");

            logger.info("Verified requirement count increases by 1 after link and decreases by 1 after unlink");
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
