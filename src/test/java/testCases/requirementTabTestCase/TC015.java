package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.requirementTab.AddRequirementPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC015 extends BaseClass {

    @Test(dataProvider = "tc015", dataProviderClass = DataProviders.RequirementDataProvider.class, description = "Verify error when requirement name is left blank during creation", retryAnalyzer = RetryAnalyzer.class)
    public void verifyErrorWhenRequirementNameBlank(String projectName, String moduleName) throws InterruptedException {
        logger.info(
                "************ Starting Test Case: Verify error when requirement name is left blank during creation *****************");
        try {
            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");

            requirementTabPage.clickDropdownToSelectProject(projectName);
            logger.info("Expanded project: " + projectName);

            requirementTabPage.clickArrowRightPointingForExpandModule(moduleName);
            WaitUtils.waitFor2000Milliseconds();
            requirementTabPage.clickOnModule(moduleName);
            logger.info("Clicked on module: " + moduleName);

            addRequirementPage.clickAddRequirementBtn();
            logger.info("Clicked on Add Requirement button");

            addRequirementPage.clearRequirementName();

            addRequirementPage.clickSave();
            logger.info("Clicked Save with name field left blank");

            String actualError = addRequirementPage.getErrorMessage();
            logger.info("Captured error message: " + actualError);

            Assert.assertEquals(actualError, "Error: Please enter the Name",
                    "Error message did not match when requirement name was left blank");
            logger.info("Verified correct error message is displayed");

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
