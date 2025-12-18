package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC016 extends BaseClass {
    @Test(dataProvider = "tc016", dataProviderClass = DefectTabTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verify_saving_with_all_fields_filled(
            String expectedUrlAfterClick,
            String Summary,
            String Type,
            String Category,
            String Priority,
            String Status,
            String AssignTo,
            String Description
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify saving with all fields filled ********");

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


            createDefectPage.selectTypeByIndex(Integer.parseInt(Type));
            logger.info("Type selected:"+Type);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectCategoryByIndex(Integer.parseInt(Category));
            logger.info("Category selected:"+Category);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectPriorityByIndex(Integer.parseInt(Priority));
            logger.info("Priority selected:"+Priority);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectStatusByIndex(Integer.parseInt(Status));
            logger.info("Status selected:"+Status);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.selectAssignToByIndex(Integer.parseInt(AssignTo));
            logger.info("Assign To selected:"+AssignTo);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.enterDescription(Description);
            logger.info("Description filled:"+Description);
            WaitUtils.waitFor2000Milliseconds();;

            createDefectPage.clickSave();
            logger.info("Clicked on save button");


        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("************ Test Case Finished: Verify saving with all fields filled *************");
    }
}
