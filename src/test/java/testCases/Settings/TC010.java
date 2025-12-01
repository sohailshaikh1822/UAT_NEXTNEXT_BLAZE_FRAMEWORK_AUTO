package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC010 extends BaseClass {

    @Test(dataProvider = "tc010", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyThatUserIsAbleToAddaDefaultFieldValueintheModuleTab(
            String fieldrow,
            String fieldName
    ) throws InterruptedException {

        logger.info("****** Starting the TC010 ***********");

        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            Thread.sleep(3000);

            otherTab.clickModule();
            logger.info("Navigated to Module tab");

            otherTab.clickOnEdit(fieldrow);
            logger.info("Clicked on edit field button:" + fieldrow);

            otherTab.clickDefaultAddValue();
            logger.info("Click on add default value");

            otherTab.enterDefaultValue(fieldName);
            logger.info("Entered defualt value: " + fieldName);

            otherTab.clickDefaultSaveButton();
            logger.info("clicked on default save changes button");

            Thread.sleep(2000);

            logger.info("Now delete the added default field value");

            otherTab.clickOnEdit(fieldrow);
            logger.info("Clicked on edit field");

            Thread.sleep(2000);

            otherTab.clickDefaultDeleteIcon();
            logger.info("Click on delete button");

            Thread.sleep(2000);

            otherTab.clickYesDefaultValueDelete();
            logger.info("Successfully deleted the Default value after adding it");

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
