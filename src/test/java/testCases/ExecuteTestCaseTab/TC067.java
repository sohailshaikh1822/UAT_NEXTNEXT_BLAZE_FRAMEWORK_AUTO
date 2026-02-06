package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC067 extends BaseClass {


    @Test(
            dataProvider = "tc067",
            dataProviderClass = ExecuteTestCaseDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    public void VerifyEditAssigneeButtonFunctionalityInAssignToFlow(
            String releaseName,
            String trId ,
            String tempUser
    ) throws InterruptedException
    {

        logger.info("****** Starting TC067 *****************");

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

            String existingAssignee =
                    executeLandingPage.getCurrentAssigneeFromTestRun(trId);
            logger.info("Existing assignee: " + existingAssignee);

            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.clickEditAssigneeIcon(trId);
            logger.info("Clicked Edit Assignee icon");
            WaitUtils.waitFor2000Milliseconds();

            executeLandingPage.verifyAssignDropdownOpened(trId);
            logger.info("Assign dropdown opened");

            WaitUtils.waitFor2000Milliseconds();

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
