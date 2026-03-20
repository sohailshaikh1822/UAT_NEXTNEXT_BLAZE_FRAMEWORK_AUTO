package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.NotificationsListener;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC126 extends BaseClass {

    @Test(dataProvider = "tc129", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

    public void VerifyNavigationToModuleHistoryTab(
            String project,
            String epic
    )
            throws InterruptedException {
        logger.info("****** Starting the TestCase *******");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            WaitUtils.waitFor3000Milliseconds();;
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");
            logger.info("Clicked on the Project from left panel to open the module");
            requirementTabPage.clickDropdownToSelectProject(project);
            logger.info("Navigate to the project");
            requirementTabPage.clickOnModule(epic);
            logger.info("Navigated to Module");
            WaitUtils.waitFor2000Milliseconds();
            individualModulePage.clickModuleHistory();
            logger.info("Clicked on the module history");
            WaitUtils.waitFor2000Milliseconds();

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
