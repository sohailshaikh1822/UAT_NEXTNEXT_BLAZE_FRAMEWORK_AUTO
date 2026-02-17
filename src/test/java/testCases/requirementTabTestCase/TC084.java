package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC084 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyRecycleBinGridColumns() throws InterruptedException {

        logger.info("****** Starting TC084 ********");

        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());

            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement tab");

            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.clickRecycleBinButton();
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.verifyRecycleBinPageOpened();
            logger.info("Recycle Bin opened successfully");

            WaitUtils.waitFor2000Milliseconds();
            List<String> actualHeaders =
                    requirementTabPage.getRecycleBinColumnHeaders();

            Assert.assertTrue(actualHeaders.contains("id"),
                    "ID column not found");
            WaitUtils.waitFor2000Milliseconds();

            Assert.assertTrue(actualHeaders.contains("name"),
                    "Name column not found");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(actualHeaders.contains("type"),
                    "Type column not found");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(actualHeaders.contains("deleted by"),
                    "Deleted By column not found");

            WaitUtils.waitFor2000Milliseconds();
            Assert.assertTrue(actualHeaders.contains("deleted date"),
                    "Deleted Date column not found");
            WaitUtils.waitFor2000Milliseconds();

            logger.info("Column headers verified successfully");
            requirementTabPage.verifyRecycleBinGridData();
            logger.info("Grid data validated successfully");
            WaitUtils.waitFor2000Milliseconds();

            logger.info("TC084 executed successfully");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
    }
}
