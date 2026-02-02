package testCases.testPlanTabTestCase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC035 extends BaseClass
{

    @Test(dataProvider = "tc005", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verifyDeletedReleaseRestoreSuccessFully(String projectName, String releaseName) throws InterruptedException {
        logger.info("****** Starting TC035 *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickNewRelease();
            logger.info("Clicked Add Release button, release form appeared");

            testPlanPage.enterReleaseName(releaseName);
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectReleaseStatus("Planned");
            testPlanPage.selectStartDate("05-01-2025");
            testPlanPage.selectEndDate("10-01-2025");
            String releaseDescription = "Automated description for new release";
            testPlanPage.enterDescription(releaseDescription);
            String releaseNotes = "Test Notes for the new release";
            testPlanPage.enterReleaseNotes(releaseNotes);

            logger.info("Entered all release details");

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickDelete();
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(releaseName);

           WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectObjectDropdownValue("Release");
            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.smoothScrollRecycleBin();
            WaitUtils.waitFor3000Milliseconds();

            testPlanPage.selectRadioById("RL-870");
            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickRestoreButton();


        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
