package testCases.Settings;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC034 extends BaseClass {

    @Test(dataProvider = "tc034", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifythatuserisabletoaddaDefaultFieldValueintheTestcaseTab(
            String fieldName,
            String value
    ) throws InterruptedException {

        logger.info("****** Starting the TC022:Verify that user is able to add a Default Field Value in the Module Tab.***************");

        try {
            login();
            logger.info("Logged in successfully");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickTestStep();
            logger.info("Navigated to TestCase tab");

            otherTab.clickOnEdit(fieldName);
            logger.info("clicked on edit row:" + fieldName);

            otherTab.enterDefaultValue(value);
            logger.info("entered default value:" + value);

            otherTab.clickDefaultSaveChanges();
            logger.info("clicked on save changes");

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
