package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.AddRequirementPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC012 extends BaseClass {

    @Test(dataProvider = "tc012", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifytheclosebuttonfunctionalityintherequirements(
            String project,
            String epic,
            String rq
    ) throws InterruptedException {

        logger.info("****** Starting the TC:12 verify the close button functionality in the requirements   *************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage reqPage = new RequirementTabPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            reqPage.clickRequirementTab();
            logger.info("clicked on requirement tab");
            WaitUtils.waitFor1000Milliseconds();

//            reqPage.clickDropdownToSelectProject(project);
//            logger.info("Selected project" + project);

            reqPage.clickOnModule(epic);
            logger.info("Selected epic" + epic);
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage req = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            req.clickRequirement(rq);
            logger.info("Selected Requirement" + rq);
            WaitUtils.waitFor1000Milliseconds();

            req.clickCloseButton();
            logger.info("Close button clicked");
            WaitUtils.waitFor1000Milliseconds();

            AddRequirementPage addreqPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            addreqPage.isModulePageReopened();
            logger.info("Module page reopened sucessfully");
            WaitUtils.waitFor1000Milliseconds();

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC:12 Finished *************************");
    }
}