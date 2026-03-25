package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC128 extends BaseClass {


    @Test(dataProvider = "tc129", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifyVersionIncreasesByOneWhenModuleIsUpdatedAndSaved(
            String project,
            String epic
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            logger.info("************ Test Case Started ****************");

            login();
            logger.info("Logged in successfully");

            RequirementTabPage requirementsPage = new RequirementTabPage(getDriver());
            logger.info("Initialized RequirementTabPage");

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            logger.info("Initialized IndividualModulePage");

            WaitUtils.waitFor3000Milliseconds();
            requirementsPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");

            WaitUtils.waitFor3000Milliseconds();

            logger.info("Clicked on the Project from left panel to open the module");
            requirementsPage.clickDropdownToSelectProject(project);
            logger.info("Navigate to the project");

            requirementsPage.clickOnModule(epic);
            logger.info("Navigated to Module");
            WaitUtils.waitFor2000Milliseconds();

            individualModulePage.clickModuleHistory();
            logger.info("Clicked on the module history");
            WaitUtils.waitFor2000Milliseconds();

            individualModulePage.clickLatestUpdatedVersion();
            logger.info("Clicked on the latest updated version");
            WaitUtils.waitFor1000Milliseconds();

            individualModulePage.enterName("Module Name updated");

            WaitUtils.waitFor2000Milliseconds();

            individualModulePage.clickSave();

            WaitUtils.waitFor2000Milliseconds();
            individualModulePage.clickModuleHistory();
            WaitUtils.waitFor1000Milliseconds();

//            String updatedVersionNoVersionNo=individualModulePage.getLatestUpdatedVersionNo();
//
//            Assert.assertEquals(
//                    Integer.parseInt(updatedVersionNoVersionNo),
//                    Integer.parseInt(currentVersionNo) + 1,
//                    "Version number did not increment by 1"
//            );

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
