package testCases.requirementTabTestCase;

import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC014 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifytheexpandcollapseontheleftpannel() throws InterruptedException {
        logger.info("****** Starting the TC:14 verify the expand/collapse on the left pannel   *************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage reqPage = new RequirementTabPage(getDriver());
            reqPage.clickRequirementTab();
            logger.info("clicked on requirement tab");

            reqPage.clicktoggleSidebar();
            logger.info("Verified that Toggle button are clickable");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }
        logger.info("************ TC:14 Finished *************************");
    }
}
