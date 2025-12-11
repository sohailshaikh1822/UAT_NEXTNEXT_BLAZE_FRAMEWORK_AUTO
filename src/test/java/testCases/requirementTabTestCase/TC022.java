package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC022 extends BaseClass {

    @Test(dataProvider = "tc022", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verifyWhitespaceNotAllowedInModuleNameAndDescription(String mainProject, String module, String newmodulename
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on the 'Requirement' tab");

            Thread.sleep(6000);

            requirementTabPage.clickArrowRightPointingForExpandModule(mainProject);
            logger.info("Expanded the main project: " + mainProject);

            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.clickOnModule(module);
            logger.info("Clicked on the module: " + module);

            requirementTabPage.setModuleName(newmodulename);
            logger.info("Entered module name: " + newmodulename);

            individualModulePage.clickSave();
            logger.info("Clicked the 'Save' button");

            String actualWarning = individualModulePage.getAlertMessage();
            logger.info("Captured alert message: " + actualWarning);

            Assert.assertEquals(actualWarning, "Please enter a module name to proceed.");
            logger.info("Assertion passed: Warning message is as expected");

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage(), e);
            throw e;
        }

        logger.info("************ Test Case Finished *************************");

    }
}
