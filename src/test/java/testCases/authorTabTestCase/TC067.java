package testCases.authorTabTestCase;

import DataProviders.AuthorTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC067 extends BaseClass {

    @Test(dataProvider = "tc067", dataProviderClass = AuthorTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verifyMaxLengthOfTcName(String rqName) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");

            logger.info("Navigated to Author Test Case tab");
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());

            authorTestCasePage.searchRq(rqName);
            logger.info("Successfully searched with RQ ID: " + rqName);

            authorTestCasePage.clickRequirement(rqName);
            logger.info("Clicked on the selected RQ");

            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked 'Add Test Case' button");

            logger.info("Entering a long name (600 chars) to verify max length enforcement...");
            String actualEnteredName = addTestcasePage.verifyTestCaseNameMaxLength();
            logger.info("Length of entered name: " + actualEnteredName.length());

            Assert.assertTrue(actualEnteredName.length() >= 500,
                    "Test case name field accepted more than 500 characters! Length: " + actualEnteredName.length());
            logger.info("Verified that name field does not accept more than 500 characters");

            addTestcasePage.clickSave();
            logger.info("Clicked the save button");

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");

    }

}
