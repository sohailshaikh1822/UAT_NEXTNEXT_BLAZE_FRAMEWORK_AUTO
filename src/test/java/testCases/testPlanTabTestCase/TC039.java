package testCases.testPlanTabTestCase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC039 extends BaseClass
{
    @Test(dataProvider = "tc039", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void VerifyANotificationIsDisplayedWhenADeletedReleaseIsRestored(
            String projectName,
            String releaseName
    ) throws InterruptedException {
        logger.info("****** Starting TC039 *****************");

        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            IndividualReleasePage individualReleasePage=new IndividualReleasePage(getDriver());

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


            logger.info("Entered release details");

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");
            WaitUtils.waitFor3000Milliseconds();
            String rlid = individualReleasePage.getReleaseId();
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

            testPlanPage.selectRadioById(rlid);
            WaitUtils.waitFor1000Milliseconds();

            testPlanPage.clickRestoreButton();
            WaitUtils.waitFor1000Milliseconds();

            individualReleasePage.verifyRestoreToastMessage(rlid);
            logger.info("restored toast notification");
            WaitUtils.waitFor2000Milliseconds();

            individualReleasePage.verifyReleaseRestoreNotification(rlid);
            logger.info("Restored Notification found");
        }
        catch (AssertionError e)
        {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        }
        catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
