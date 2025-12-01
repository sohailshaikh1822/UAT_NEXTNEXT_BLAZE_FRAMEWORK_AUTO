package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC018 extends BaseClass {

    @Test(dataProvider = "tc018", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyFeatureDropdownClearsValueAfterPageRefresh(
            String epic, String feature, String expectedSelectedEpic
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickEpic();
            logger.info("Navigated to Author Test Case tab");

            authorTestCasePage.selectEpic(epic);
            logger.info("selected the Epic");

            authorTestCasePage.selectFeature(feature);
            logger.info("Selected the Feature");

            getDriver().navigate().refresh();
            logger.info("Refreshed the page");

            String selectedEpic = authorTestCasePage.getSelectedEpic();
            Assert.assertEquals(selectedEpic, expectedSelectedEpic);
            logger.info("Verified Successfully");

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
