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
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on the 'Requirement' tab");
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor3000Milliseconds();

//            requirementTabPage.clickDropdownToSelectProject(mainProject);
//            logger.info("Expanded the main project: " + mainProject);

            WaitUtils.waitFor2000Milliseconds();

            requirementTabPage.clickOnModule(module);
            logger.info("Clicked on the module: " + module);
            WaitUtils.waitFor1000Milliseconds();

            requirementTabPage.setModuleName(newmodulename);
            logger.info("Entered module name: " + newmodulename);
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.clickSave();
            logger.info("Clicked the 'Save' button");
            WaitUtils.waitFor1000Milliseconds();

            String actualWarning = individualModulePage.getAlertMessage();
            logger.info("Captured alert message: " + actualWarning);
            WaitUtils.waitFor1000Milliseconds();

            Assert.assertEquals(actualWarning, "Please enter a module name to proceed.");
            logger.info("Assertion passed: Warning message is as expected");
            WaitUtils.waitFor1000Milliseconds();

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