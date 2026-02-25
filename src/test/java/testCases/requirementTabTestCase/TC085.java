package testCases.requirementTabTestCase;

import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC085 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifySingleSelectionInRecycleBin() throws InterruptedException {

        logger.info("****** Starting TC085 ********");

        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage =
                    new RequirementTabPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked Requirement tab");

            requirementTabPage.clickRecycleBinButton();
            requirementTabPage.verifyRecycleBinPageOpened();
            logger.info("Recycle Bin opened successfully");

            requirementTabPage.verifyOnlyOneItemSelectable();
            logger.info("Single selection validation completed");

            logger.info("****** TC085 Completed Successfully ********");

        } catch (Exception e) {
            logger.error("TC085 Failed: " + e.getMessage(), e);
            throw e;
        }
    }
}
