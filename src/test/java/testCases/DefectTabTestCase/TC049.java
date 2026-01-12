package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC049 extends BaseClass {

    @Test(
            dataProvider = "tc049",
            dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void Verify_that_a_success_notification_is_displayed_when_a_new_defect_is_createdVerify_text_input_in_Summary_and_Description_fields(
            String Summary,
            String status
    ) throws InterruptedException {

        logger.info("****** Starting TC049: Verify defect creation notification ******");

        try {
            login();
            logger.info("Logged in successfully");

            DefectLandingPage defectLandingPage = new DefectLandingPage(getDriver());
            defectLandingPage.clickDefectTab();

            WaitUtils.waitFor2000Milliseconds();
            defectLandingPage.clickCreateTestCaseButton();

            CreateDefectPage createDefectPage = new CreateDefectPage(getDriver());

            WaitUtils.waitFor2000Milliseconds();
            createDefectPage.enterSummary(Summary);

            WaitUtils.waitFor2000Milliseconds();
            createDefectPage.selectStatusByIndex(Integer.parseInt(status));

            WaitUtils.waitFor2000Milliseconds();
            createDefectPage.clickSaveforNewDefect();

            WaitUtils.waitFor9000Milliseconds();

            String defectId = createDefectPage.getDefectIdFromCreationNotification();
            logger.info("Defect created with ID: " + defectId);

            createDefectPage.verifyDefectCreationNotification(defectId);

            logger.info("Defect creation notification verified successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("****** Finished TC049: Defect creation notification verification ******");
    }
}
