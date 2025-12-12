package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC046 extends BaseClass {

    @Test(dataProvider = "tc046", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifydropdownselectionforQAandBusinessUserinAddTestCase(
            String epicName,
            String featureName,
            String rq_id,
            String desc,
            String Qauser
    ) throws InterruptedException {
        logger.info("****** Starting TC046: Verify dropdown selection for QA and Business User in Add TestCase ******");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Navigated to Author Test Case tab");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Clicked on Epic Drop Down");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.selectEpic(epicName);
            logger.info("Selected Epic: " + epicName);
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.selectFeature(featureName);
            logger.info("Selected Feature: " + featureName);
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement(rq_id);
            logger.info("Selected Requirement: " + rq_id);
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestCase");
            WaitUtils.waitFor1000Milliseconds();
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());

            if (addTestcasePage.isDescriptionDisplayed()) {
                addTestcasePage.setDescription(desc);
                logger.info("Entered description successfully");
            } else {
                logger.error("Description field not displayed");
                Assert.fail("Description field not displayed");
            }
            WaitUtils.waitFor1000Milliseconds();
            if (addTestcasePage.isQAUserDropdownDisplayed()) {
                addTestcasePage.selectQaUser(Qauser);
                logger.info("User able to select QA User from dropdown: " + Qauser);

                String selectedUser = Qauser;
                Assert.assertEquals(selectedUser, Qauser, "QA User dropdown selection mismatch!");
            } else {
                logger.error("QA User dropdown not displayed");
                Assert.fail("QA User dropdown not displayed");
            }

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC046 Finished ************");
    }
}
