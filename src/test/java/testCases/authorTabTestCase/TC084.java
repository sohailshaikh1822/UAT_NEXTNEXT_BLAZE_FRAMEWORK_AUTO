package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC084 extends BaseClass {

    @Test(dataProvider = "tc084", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verifying_the_notification_message_after_approving_a_new_Test_Case(
            String epicName,
            String featureName,
            String rq_id,
            String TCname,
            String desc,
            String Type,
            String Qauser,
            String Prior
    ) throws InterruptedException {
        logger.info("****** Starting TC084: Verifying the notification message after approving a new  Test Case ******");

        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestCase");
            WaitUtils.waitFor3000Milliseconds();
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            authorTestCasePage.clickAddTestCaseAndEnterName(TCname);
            logger.info("Testcase Name"+TCname);
            addTestcasePage.setDescription(desc);
            logger.info("Testcase Description"+desc);
            addTestcasePage.selectType(Type);
            addTestcasePage.selectQaUser(Qauser);
            addTestcasePage.clickSave();
            logger.info("clicked on save button");


            WaitUtils.waitFor3000Milliseconds();
            String tcId = authorTestCasePage.getNewlyCreatedTestCaseId();

            authorTestCasePage.openNewlyCreatedTestCase();
            WaitUtils.waitFor3000Milliseconds();

            individualTestCasePage.selectPriority(Prior);
            logger.info("Selected priority: " + Prior);

            WaitUtils.waitFor3000Milliseconds();
            individualTestCasePage.clickSaveButton();
            logger.info("Clicked Save button after update");
            WaitUtils.waitFor9000Milliseconds();

            individualTestCasePage.clickApproveButton();
            logger.info("Clicked on Approve button");

            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.verifyTestCaseApproveNotification(tcId);
            logger.info("Test Case approved notification verified successfully");


        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC084 Finished ************");
    }
}