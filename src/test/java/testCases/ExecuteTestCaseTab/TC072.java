package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC072 extends BaseClass {

    @Test(
            dataProvider = "tc072",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifySaveButtonAppearsOnlyAfterSelectingAUserWhileAssigningTestRun(
            String releaseName,
            String trId,
            String tempUser
    ) throws InterruptedException {

        logger.info("****** Starting TC072 *****************");

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

            executeLandingPage.verifySaveButtonNotVisible(trId);
            logger.info("Save button is not visible before user selection");

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.selectUserFromAssignDropdown(trId, tempUser);
            logger.info("Selected user from dropdown: " + tempUser);

            executeLandingPage.verifySaveButtonVisible(trId);
            logger.info("Save button is visible after user selection");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ TC072 Finished *************************");
    }
}
