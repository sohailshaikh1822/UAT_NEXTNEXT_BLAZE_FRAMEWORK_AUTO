package testCases.authorTabTestCase;

import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC032 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyDisabledNextBtnOnLastPage() throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");

            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickLastPageArrowBtn();
            WaitUtils.waitFor1000Milliseconds();
            String cursorStyle = authorTestCasePage.checkIfNextButtonIsClickable();
            logger.info("Cursor style of Next button: " + cursorStyle);

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ Test Case Finished *************************");
    }

}
