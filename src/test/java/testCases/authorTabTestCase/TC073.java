package testCases.authorTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC073 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyToggleFunctionalityInRequirementPanel() throws Exception {
        logger.info("************ Starting the Test Case: Verify Toggle Functionality in Requirement Panel *****************");
        try {

            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Clicked Author Testcase tab");
            authorTestCasePage.selectEpic("Epic Mohit");
            logger.info("Selected epic");
            authorTestCasePage.selectFeature("Mohit Feature");
            logger.info("Selected feature");
            String reqId = "RQ-489";
            authorTestCasePage.clickRequirement(reqId);
            logger.info("Selected requirement " + reqId);

            boolean initiallyExpanded = authorTestCasePage.isRequirementPanelExpanded(reqId);
            logger.info("Initially Expanded? " + initiallyExpanded);
            Assert.assertTrue(initiallyExpanded, "Panel should be expanded initially");

            int cycles = 10;   // Number of toggle cycles
            for (int i = 1; i <= cycles; i++) {
                // Collapse
                authorTestCasePage.toggleRequirementPanel(reqId);
                Thread.sleep(1000);
                boolean collapsed = !authorTestCasePage.isRequirementPanelExpanded(reqId);
                logger.info("Cycle " + i + " -> Panel collapsed? " + collapsed);
                Assert.assertTrue(collapsed, "Panel should collapse at cycle " + i);
                // Expand
                authorTestCasePage.toggleRequirementPanel(reqId);
                Thread.sleep(1000);
                boolean expanded = authorTestCasePage.isRequirementPanelExpanded(reqId);
                logger.info("Cycle " + i + " -> Panel expanded? " + expanded);
                Assert.assertTrue(expanded, "Panel should expand at cycle " + i);
            }
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
