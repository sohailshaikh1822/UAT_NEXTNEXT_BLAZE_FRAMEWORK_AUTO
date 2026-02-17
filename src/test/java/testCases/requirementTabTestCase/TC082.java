package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.util.List;

public class TC082 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyRecycleBinGridColumns() throws InterruptedException {

        logger.info("****** Starting TC084 ********");

        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage =
                    new RequirementTabPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement tab");

            requirementTabPage.clickRecycleBinButton();
            requirementTabPage.verifyRecycleBinPageOpened();
            logger.info("Recycle Bin opened successfully");

            List<String> actualHeaders =
                    requirementTabPage.getRecycleBinColumnHeaders();

            List<String> expectedHeaders = List.of(
                    "id",
                    "name",
                    "type",
                    "deleted by",
                    "deleted date"
            );

            Assert.assertEquals(
                    actualHeaders,
                    expectedHeaders,
                    "Recycle Bin column headers mismatch"
            );

            logger.info("Column headers verified successfully");

            requirementTabPage.verifyRecycleBinGridData();
            logger.info("Grid data validated successfully");

            logger.info("****** TC084 Completed Successfully ********");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }
    }
}
