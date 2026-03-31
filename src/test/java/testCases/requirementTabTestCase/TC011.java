package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.RequirementTabPage;
import pageObjects.requirementTab.IndividualModulePage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC011 extends BaseClass {

    @Test(dataProvider = "tc011", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void VerifySavebuttonfunctionalityintherequirements(
            String project,
            String epic,
            String rq,
            String pri,
            String pri1
    ) throws InterruptedException {

        logger.info("****** Starting the TC:11 Verify Save button functionality in the requirements  *************");

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
            logger.info("Selected project" + project);
            WaitUtils.waitFor1000Milliseconds();

            reqPage.clickArrowRightPointingForExpandModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            WaitUtils.waitFor2000Milliseconds();

            reqPage.clickOnModule(epic);
            logger.info("Selected epic" + epic);
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage req = new IndividualModulePage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            req.clickRequirement(rq);
            logger.info("Selected Requirement" + rq);
            WaitUtils.waitFor1000Milliseconds();

            req.setPriority(pri);
            logger.info("Priority changed" + pri);
            WaitUtils.waitFor1000Milliseconds();

            req.clickSave();
            logger.info("Clicked saved");
            WaitUtils.waitFor1000Milliseconds();

            if (req.isRequirementUpdatedSuccessfully()) {
                logger.info("Requirement updated successfully is visible.");
            } else {
                logger.error("Requirement update Unsuccessfully.");
            }
            WaitUtils.waitFor1000Milliseconds();

            req.clickCloseButton();
            logger.info("Close button clicked");
            WaitUtils.waitFor1000Milliseconds();

            req.clickRequirement(rq);
            logger.info("click req");
            WaitUtils.waitFor1000Milliseconds();

            req.setPriority(pri1);
            WaitUtils.waitFor1000Milliseconds();

            req.clickSave();
            WaitUtils.waitFor1000Milliseconds();
//            req.ClickYesPopup();

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC:11 Finished *************************");
    }
}