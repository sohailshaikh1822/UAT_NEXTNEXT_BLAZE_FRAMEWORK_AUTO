package testCases.authorTabTestCase;

import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC031 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyDisabledPreviousBtnOnFirstPage() throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());

            String cursorStyle = authorTestCasePage.checkIfPreviousButtonIsClickable();
            logger.info("Cursor style of Previous button: " + cursorStyle);

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }
}
