package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.testPlanTab.IndividualReleasePage;
import pageObjects.testPlanTab.IndividualTestCyclePage;
import pageObjects.testPlanTab.IndividualTestSuitePage;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.nio.file.LinkOption;

public class TC062 extends BaseClass {
    @Test(dataProvider = "tc062", dataProviderClass = DataProviders.TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyCreationNotificationIsEnabledAfterReleaseIsRestored(
            String projectName,
            String releaseName
    )throws InterruptedException
    {
        logger.info("****** Starting TC62 **********");
        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());
            IndividualReleasePage individualReleasePage=new IndividualReleasePage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded");

            testPlanPage.clickOnTheProjectName();
            logger.info("Selected project: " + projectName);

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.clickNewRelease();
            logger.info("Clicked Add Release button, release form appeared");

            testPlanPage.enterReleaseName(releaseName);
            WaitUtils.waitFor2000Milliseconds();

            testPlanPage.clickSaveRelease();
            logger.info("Clicked Save button");
            WaitUtils.waitFor9000Milliseconds();

            String releaseId = individualReleasePage.getReleaseId();
            logger.info("Suite ID captured: " + releaseId);


            WaitUtils.waitFor2000Milliseconds();

            testPlanPage.clickDelete();
            logger.info("Release is deleted");

            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickOnConfirmDeleteYes(releaseName);

            logger.info("Release is deleted successfully");

            WaitUtils.waitFor2000Milliseconds();
            individualReleasePage.verifyCreationAndDeletionReleaseNotificationsAreDisabled(releaseId);
            logger.info("Successfully verified both creation and deletion notifications are disabled after Release deletion ");

            WaitUtils.waitFor2000Milliseconds();

            testPlanPage.clickOnRecycleBinIcon();
            logger.info("Clicked on Recycle Bin");
            WaitUtils.waitFor2000Milliseconds();
            testPlanPage.selectObjectDropdownValue("Release");

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.smoothScrollRecycleBin();

            WaitUtils.waitFor3000Milliseconds();
            testPlanPage.selectRadioById(releaseId);

            WaitUtils.waitFor1000Milliseconds();
            testPlanPage.clickRestoreButton();

            String actualMessage =testPlanPage.getNotificationMessage();
            String expectedMessage="Release restored successfully.";
            Assert.assertEquals(actualMessage,expectedMessage,"Confirmation message has not matched");

            WaitUtils.waitFor2000Milliseconds();
            individualReleasePage.verifyCreatedReleaseNotificationNotClickable(releaseId);
            logger.info("Verified that creation notification is enabled after Release is restored");

            logger.info("************ Test Case Finished Successfully ******************");

        }
        catch (AssertionError e)
        {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        }
        catch (Exception e)
        {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
