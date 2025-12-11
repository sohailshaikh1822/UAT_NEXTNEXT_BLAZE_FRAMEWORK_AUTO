package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;


public class TC017 extends BaseClass {

    @Test(dataProvider = "tc017", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_success_message_content_after_save(
            String expectedUrlAfterClick,
            String Summary,
            String Description
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify success message content after save ********");

        try {
            login();
            logger.info("Logged in successfully and dashboard loaded");
            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();
            logger.info("Clicked on Defect Tab");
            String actualUrl = getDriver().getCurrentUrl();
            Assert.assertNotNull(actualUrl);
            Assert.assertTrue(actualUrl.contains(expectedUrlAfterClick),
                    "User did not navigate to the expected Defect Page URL.");
            logger.info("Successfully navigated to Defect Page. Current URL: " + actualUrl);
            logger.info("Defect Page loaded and form fields are visible.");
            WaitUtils.waitFor2000Milliseconds();;
            defectLandingPage.clickCreateTestCaseButton();
            logger.info("clicked on Create Defect Button");

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());
            WaitUtils.waitFor2000Milliseconds();;
            createDefectPage.enterSummary(Summary);
            logger.info("Summary filled:"+Summary);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectCategoryByIndex(1);
            logger.info("Category selected:"+1);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectPriorityByIndex(2);
            logger.info("Priority selected:"+2);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectStatusByIndex(1);
            logger.info("Status selected:"+1);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectAssignToByIndex(12);
            logger.info("Assign To selected:"+12);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.enterDescription(Description);
            logger.info("Description filled:"+Description);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.clickSave();
            logger.info("Clicked on save button");

            createDefectPage.verifySuccessNotification();

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify success message content after save *************");
    }
}
