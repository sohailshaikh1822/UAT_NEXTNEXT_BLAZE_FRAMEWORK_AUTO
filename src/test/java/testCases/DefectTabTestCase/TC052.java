package testCases.DefectTabTestCase;

import DataProviders.DefectTabTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.defectTab.CreateDefectPage;
import pageObjects.defectTab.DefectLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC052 extends BaseClass {

    @Test(
            dataProvider = "tc050",
            dataProviderClass = DefectTabTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void Verify_navigation_to_defect_detials_page_when_an_existing_defect_is_updated(
            String Summary,
            String status,
            String status1
    ) throws InterruptedException {

        logger.info("****** Starting TC052: Verify defect update notification ******");

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

            String defectId = createDefectPage.getNewlyCreatedDefectId();
            logger.info("Defect created with ID: " + defectId);

            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.openNewlyCreatedDefect();

            WaitUtils.waitFor2000Milliseconds();

            createDefectPage.selectStatusByIndex(Integer.parseInt(status1));
            WaitUtils.waitFor2000Milliseconds();
            createDefectPage.clickSave();
            WaitUtils.waitFor9000Milliseconds();
            createDefectPage.verifyDefectUpdateNotification(defectId);

            logger.info("Defect update notification verified successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception ex) {
            logger.error("Exception occurred: " + ex.getMessage());
            throw ex;
        }

        logger.info("****** Finished TC052: Defect update notification verification ******");
    }
}
