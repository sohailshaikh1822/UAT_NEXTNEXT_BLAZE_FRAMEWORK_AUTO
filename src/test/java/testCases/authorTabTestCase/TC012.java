package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC012 extends BaseClass {

    @Test(dataProvider = "tc011", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyClickFunctionOfFeaturesDropdown(
            String epicName, String feature) throws InterruptedException {
        logger.info("****** Starting the Log in Test Case *****************");
        try {

            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Click on the Epic Drop Down");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.selectEpic(epicName);
            logger.info("selected the epic from the dropdown");
            System.out.println(authorTestCasePage.getSelectedEpic());
            logger.info("selected the feature from the dropdown");
            authorTestCasePage.getAllFeatures();
            logger.info("Verification done...");
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
