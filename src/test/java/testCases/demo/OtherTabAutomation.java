package testCases.demo;

import DataProviders.SettingTestCaseDataProvider;
import org.testng.annotations.Test;
import pageObjects.Settings.GlobalTabPage;
import pageObjects.Settings.OtherTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class OtherTabAutomation extends BaseClass {

    public OtherTabAutomation() {
        super();
    }

    @Test(dataProvider = "Demo1", dataProviderClass = SettingTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void runOtherTabAutomation(
            String editrow,
            String value,
            String fieldname,
            String fieldtype
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            logger.info("Navigated to Author Test Case tab");

            GlobalTabPage globalTabPage = new GlobalTabPage(getDriver());
            globalTabPage.clickCurrentUserAndGoToSettings();

            OtherTabPage otherTabPage = new OtherTabPage(getDriver());
//            otherTabPage.clickModule();
//            logger.info("clicked on Modules");
//
//            otherTabPage.clickOnEdit(editrow);
//            logger.info("clicked on edit row:"+editrow);
//
//            otherTabPage.clickDefaultAddValue() ;
//            logger.info("clicked on adddefaultvalue");
//
//            otherTabPage.enterDefaultValue(value);
//            logger.info("entered default value"+value);
//
//            otherTabPage.clickDefaultDeleteIcon();
//            logger.info("clicked on delete icon");
//
//            otherTabPage.clickDefaultCloseButton();
//            logger.info("clicked on close button");
//
//            otherTabPage.clickOnAddCustomField();
//            logger.info("clicked on add custom button");
//
//            otherTabPage.createCustomEnterFieldName(fieldname);
//            logger.info("entered custom field value"+fieldname);
//
//            otherTabPage.createCustomSelectDataType(fieldtype);
//            logger.info("entered custom field value"+fieldtype);
//
//            otherTabPage.createCustomClickCancelButton();

            //-------------------------------------------------------------------
            //release
            otherTabPage.clickOnRelease();
            logger.info("clicked on release");

            otherTabPage.clickOnEdit(editrow);
            logger.info("clicked on edit row:" + editrow);

            otherTabPage.clickDefaultAddValue();
            logger.info("clicked on adddefaultvalue");

            otherTabPage.enterDefaultValue(value);
            logger.info("entered default value" + value);

            otherTabPage.clickDefaultDeleteIcon();
            logger.info("clicked on delete icon");

            otherTabPage.clickDefaultCloseButton();
            logger.info("clicked on close button");

            otherTabPage.clickOnAddCustomField();
            logger.info("clicked on add custom button");

            otherTabPage.createCustomEnterFieldName(fieldname);
            logger.info("entered custom field value" + fieldname);

            otherTabPage.createCustomSelectDataType(fieldtype);
            logger.info("entered custom field value" + fieldtype);

            otherTabPage.createCustomClickCancelButton();

//            otherTabPage.deleteButtonForRow(editrow);
//            logger.info("clicked on delete row:"+editrow);
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
