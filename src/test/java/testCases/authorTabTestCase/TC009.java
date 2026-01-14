package testCases.authorTabTestCase;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.util.List;

public class TC009 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyEpicsWithSpecialCharacter() throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickEpic();
            logger.info("Click on the Epic Drop Down");
            List<WebElement> epics = authorTestCasePage.getAllEpics();
            boolean b = false;
            for (int i = 0; i <= epics.size() - 1; i++) {
                if (epics.get(i).getText().contains(" ") || epics.get(i).getText().contains("_")
                        || epics.get(i).getText().contains(".")) {
                    b = true;
                    System.out.println(epics.get(i).getText());
                }
            }
            Assert.assertTrue(b, "Elements with ' ' , . , is not working....");
            logger.info("Verified successfully that elements with space or special character is present");
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
