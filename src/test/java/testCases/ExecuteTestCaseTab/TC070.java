package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC070 extends BaseClass {

    @Test(
            dataProvider = "tc070",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyToastNotificationAfterAssignTrToSomeOne(
            String releaseName,
            String trId ,
            String tempUser
    ) throws InterruptedException
    {

        logger.info("****** Starting TC070 *****************");
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

            String existingAssignee =
                    executeLandingPage.getCurrentAssigneeFromTestRun(trId);
            logger.info("Existing assignee: " + existingAssignee);

            executeLandingPage.clickAssignToSaveButton();
            logger.info("Clicked Save button");

            WaitUtils.waitFor2000Milliseconds();

            String actualMessage =executeLandingPage.getToastNotificationMessage();
            String expectedMessage="'"+trId+"'"+ " has been assigned to "+ existingAssignee+ " by Julie Kumari.";
            Assert.assertEquals(actualMessage,expectedMessage,"Notification message has not matched");



        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ TC071 Finished *************************");
    }

}
