package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC008 extends BaseClass {

    @Test(dataProvider = "tc001", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyProjectSelectionFromDropdown(String projectName,
            String releaseName) throws InterruptedException {
        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");
        try {
            login();
            logger.info("Logged in successfully");
            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());
            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
            executeLandingPage.clickToSelectProject(projectName);
            logger.info("Expanded the Release dropdown from left panel");

            executeLandingPage.clickRelease(releaseName);
            logger.info("Clicked on the desired release from the dropdown");
            WaitUtils.waitFor2000Milliseconds();

            String currentPageBefore = executeLandingPage.getCurrentPageNumber();
            logger.info("Current page before clicking next arrow: " + currentPageBefore);

            executeLandingPage.clickNextArrow();
            logger.info("Clicked the next arrow button to go to the next page");

            WaitUtils.waitFor3000Milliseconds();

            String currentPageAfter = executeLandingPage.getCurrentPageNumber();
            logger.info("Current page after clicking next arrow: " + currentPageAfter);

            Assert.assertNotEquals(currentPageBefore, currentPageAfter,
                    "Page number did not change after clicking next arrow");
            Assert.assertEquals(currentPageAfter, "2",
                    "Page number is not showing 2 after clicking next arrow");
            logger.info("Verified pagination working successfully. Page number changed from 1 to 2");
        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
