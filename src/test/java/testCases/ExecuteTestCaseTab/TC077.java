package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC077 extends BaseClass {

    @Test(
            dataProvider = "tc077",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyNavigationToTestRunDetailsPageOnClickingAssignedNotification(
            String releaseName,
            String trId ,
            String tempUser,
            String secUser
    ) throws InterruptedException
    {

        logger.info("****** Starting TC077 *****************");
        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage =
                    new ExecuteLandingPage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked Execute Test Case tab");

            WaitUtils.waitFor1000Milliseconds();
            executeLandingPage.clickRelease(releaseName);
            logger.info("Selected release: " + releaseName);


            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickEditAssigneeIcon(trId);
            logger.info("Clicked Edit Assignee icon");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.verifyAssignDropdownOpened(trId);
            logger.info("Assign dropdown opened");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.selectUserFromAssignDropdown(trId, tempUser);
            logger.info("Selected user from dropdown: " + tempUser);
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickAssignToSaveButton();
            logger.info("Clicked Save button");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickAssignedTestRunNotificationFromPanel(trId);
            logger.info("clicked on the notification");
            IndividualTestRun individualTestRun=new IndividualTestRun(getDriver());
            individualTestRun.clickCloseButton();

            //reassign

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickEditAssigneeIcon(trId);
            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.verifyAssignDropdownOpened(trId);
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.selectUserFromAssignDropdown(trId, secUser);


            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickAssignToSaveButton();
            WaitUtils.waitFor1000Milliseconds();



        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ TC077 Finished *************************");
    }

}
