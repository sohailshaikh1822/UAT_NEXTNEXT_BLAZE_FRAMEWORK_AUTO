package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC087 extends BaseClass {

    @Test(dataProvider = "tc087", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyToastNotificationIsDisplayedWhenDeletedRequirementIsRestored(String moduleName,
                                                                                   String Rqtitle,
                                                                                   String description,
                                                                                   String priority,
                                                                                   String status,
                                                                                   String type) throws InterruptedException {

        logger.info("****** Starting the TC086  *******");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickEpicDropdown();
            logger.info("Clicked on Epic drop down");
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickOnModule(moduleName);
            logger.info("Opened module: " + moduleName);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            individualModulePage.clickAddRequirement();
            logger.info("Clicked on Add Requirement");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            addRequirementPage.setRequirementId(Rqtitle);
            logger.info("Enter Rqtitle:" + Rqtitle);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.setDescription(description);
            logger.info("Set Description");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.selectPriority(priority);
            logger.info("Selected Priority: " + priority);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectStatus(status);
            logger.info("Selected Status: " + status);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.selectType(type);
            logger.info("Selected Type: " + type);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            addRequirementPage.clickSave();
            logger.info("Clicked Save button");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            String rqId = addRequirementPage.getRqId();
            logger.info("Captured Requirement ID: " + rqId);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            addRequirementPage.clickClose();
            logger.info("Clicked Close button");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

            requirementTabPage.DeleteRequirementById(rqId);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor9000Milliseconds();

            requirementTabPage.verifyDeleteNotification(rqId);
            logger.info("Requirement deletion notification verified successfully");
            WaitUtils.waitFor1000Milliseconds();

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.selectObjectDropdownValue("Requirement");
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.smoothScrollRecycleBin();
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.selectRadioById(rqId);
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickRestoreButton();
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickCloseButtonOfRecycleBinPage();
            String actualMessage = testPlanPage.getToastNotificationMessage();
            WaitUtils.waitFor1000Milliseconds();

            String expectedMessage = "'" + rqId + "' is restored by Julie Kumari.";
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertEquals(actualMessage, expectedMessage, "Confirmation message has not matched");
            WaitUtils.waitFor1000Milliseconds();

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}