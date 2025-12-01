package testCases.Settings;

import org.testng.annotations.Test;
import DataProviders.SettingTestCaseDataProvider;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC017 extends BaseClass {

    @Test(dataProvider = "tc017", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyAddAndDeleteGlobalCustomField(String fieldName, String dataType) throws InterruptedException {

        logger.info("****** Starting TC017 : Verify user can add and delete a global custom field ******");

        try {
            login();
            logger.info("User logged in successfully with valid credentials");

            GlobalTabPage globalTab = new GlobalTabPage(getDriver());
            OtherTabPage otherTab = new OtherTabPage(getDriver());

            globalTab.clickCurrentUserAndGoToSettings();
            logger.info("Clicked on Settings option from user dropdown");

            otherTab.clickOnRelease();
            logger.info("Navigated to Release tab under Settings");

            otherTab.clickOnAddCustomField();
            logger.info("Clicked on Add Custom Field button");

            otherTab.createCustomEnterFieldName(fieldName);
            logger.info("Entered custom field name: " + fieldName);

            otherTab.createCustomSelectDataType(dataType);
            logger.info("Selected field data type: " + dataType);

            otherTab.clickcreatefieldButton();
            logger.info("Clicked on Create Field button");

            Thread.sleep(1000);
            boolean isFieldAdded = otherTab.isCustomFieldPresent(fieldName);
            assert isFieldAdded : "Custom field was not added successfully.";
            logger.info("Verified: Custom field '" + fieldName + "' added successfully");

            otherTab.deleteCustomFieldAndVerify(fieldName);
            logger.info("Verified: Custom field '" + fieldName + "' deleted successfully from the dashboard");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC017 Test Case Finished Successfully *************************");
    }
}
