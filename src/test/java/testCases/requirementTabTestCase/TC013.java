package testCases.requirementTabTestCase;

import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC013 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyuserisabletoclickonhelp() throws InterruptedException {
        logger.info("****** Starting the TC:13 verify user is able to click on help   *************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage reqPage = new RequirementTabPage(getDriver());
            reqPage.clickRequirementTab();
            logger.info("clicked on requirement tab");

            reqPage.verifyHelpDropdown(getDriver());
            logger.info("Help dropdown is visible");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ TC:13 Finished *************************");
    }
}
