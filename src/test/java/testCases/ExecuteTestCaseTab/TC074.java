package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC074 extends BaseClass {

    @Test(
            dataProvider = "tc074",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyReassignmentOfAlreadyAssignedTestRunToAnotherUser(
            String releaseName,
            String trId,
            String tempUser,
            String secUser
    ) throws InterruptedException {

        logger.info("****** Starting TC074 *****************");

        try {
            login();
            logger.info("Logged in successfully");

            ExecuteLandingPage executeLandingPage =
                    new ExecuteLandingPage(getDriver());

            // Step 1: Navigate to Execute tab
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked Execute Test Case tab");

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickRelease(releaseName);
            logger.info("Selected release: " + releaseName);

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickEditAssigneeIcon(trId);
            logger.info("Clicked Edit Assignee icon");

            executeLandingPage.verifyAssignDropdownOpened(trId);
            logger.info("Assign dropdown opened");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.selectUserFromAssignDropdown(trId, tempUser);
            logger.info("Selected user from dropdown: " + tempUser);

            executeLandingPage.verifySaveButtonVisible(trId);
            logger.info("Save button is visible after user selection");

            executeLandingPage.clickAssignToSaveButton();
            logger.info("clicked on save button");
            WaitUtils.waitFor1000Milliseconds();

            IndividualTestRun individualTestRun=new IndividualTestRun(getDriver());
            individualTestRun.isTestLogUpdatedDisplayed();
            logger.info("test run successfully updated ");

            // update the assigned to user again

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickEditAssigneeIcon(trId);
            logger.info("Clicked Edit Assignee icon");

            executeLandingPage.verifyAssignDropdownOpened(trId);
            logger.info("Assign dropdown opened");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.selectUserFromAssignDropdown(trId, secUser);
            logger.info("Selected previous user from dropdown: " + secUser);

            WaitUtils.waitFor2000Milliseconds();
            executeLandingPage.clickAssignToSaveButton();
            logger.info("clicked on save button");
            WaitUtils.waitFor1000Milliseconds();

            individualTestRun.isTestLogUpdatedDisplayed();
            logger.info("test run updated");


        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ TC074 Finished *************************");
    }
}
