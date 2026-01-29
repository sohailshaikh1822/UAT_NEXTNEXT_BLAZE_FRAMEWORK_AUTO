package testCases.authorTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC071 extends BaseClass {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyLongDescriptionInputHandling() throws InterruptedException {
        logger.info("************ Starting the Test Case: Verify long Description input handling *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            WaitUtils.waitFor2000Milliseconds();
            authorTestCasePage.clickAuthorTestcase();
            logger.info("Clicked Author Testcase tab");
            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickRequirement("RQ-438");
            logger.info("Selected requirement RQ-438");
            WaitUtils.waitFor3000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked Add Testcase button");

            authorTestCasePage.enterName("Long Description Test");

            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
            StringBuilder sb = new StringBuilder(3000);
            java.util.Random random = new java.util.Random();
            for (int i = 0; i < 3000; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            String longDescription = sb.toString();
            authorTestCasePage.enterDescription(longDescription);
            logger.info("Entered 3,000 random characters in Description field");

            String actualValue = getDriver().findElement(org.openqa.selenium.By.xpath("//table[@id='newTestCasesTable']//tr[1]/td[2]/input")).getAttribute("value");
            Assert.assertEquals(actualValue.length(), 3000, "Description field does not contain 3,000 characters");
            logger.info("Verified Description field contains 3,000 characters");
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
