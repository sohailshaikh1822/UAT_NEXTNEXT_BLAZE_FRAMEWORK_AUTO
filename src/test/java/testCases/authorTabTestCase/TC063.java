package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.IndividualTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

import java.time.Duration;

public class TC063 extends BaseClass {

    @Test(dataProvider = "tc063", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyDeleteSteps(
            String requirementId, String TestcaseId
    ) throws InterruptedException {
        logger.info("************ Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            authorTestCasePage.clickRequirement(requirementId);
            authorTestCasePage.linkTestCaseIdFromId(TestcaseId).click();
            Thread.sleep(3000);
            IndividualTestCasePage individualTestCasePage = new IndividualTestCasePage(getDriver());
            individualTestCasePage.clickAddTestStep();
             WaitUtils.waitFor1000Milliseconds();
            int beforeCount = individualTestCasePage.getStepCountInt();
            Thread.sleep(3000);
            logger.info("before count was " + beforeCount);
            individualTestCasePage.clickDeleteButton(beforeCount);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            wait.until(webDriver -> individualTestCasePage.getStepCountInt() == beforeCount - 1);
            int afterCount = individualTestCasePage.getStepCountInt();
            logger.info("after count is - " + afterCount);
            assert afterCount == beforeCount - 1 : "Row was not deleted";
            individualTestCasePage.clickSaveButton();

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
