package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC037 extends BaseClass {

    @Test(dataProvider = "tc037", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void verifyCLoseButtonOnTheAlertWhenEmptyModuleName(String mainProject, String module)
            throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            logger.info("************ Test Case Started *************************");

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());

            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on the 'Requirement' tab");

            WaitUtils.waitFor2000Milliseconds();;

            requirementTabPage.clickDropdownToSelectProject(mainProject);
            logger.info("Expanded the main project: " + mainProject);

            requirementTabPage.clickOnModule(module);
            logger.info("Clicked on the module: " + module);

            WaitUtils.waitFor2000Milliseconds();;

            requirementTabPage.setModuleName("");
            logger.info("Entered module name: " + " ");

            individualModulePage.clickSave();
            logger.info("Clicked the 'Save' button");
            WaitUtils.waitFor2000Milliseconds();;

            individualModulePage.clickCloseBtnOfALertModuleName();
            logger.info("Successfully Click the close button");

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
