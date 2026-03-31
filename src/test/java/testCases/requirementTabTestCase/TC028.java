package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC028 extends BaseClass {

    @Test(dataProvider = "tc028", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void Verifytheclosebuttonclearsunsavedinputformtherequirementsfield(
            String project,
            String epic,
            String rq,
            String serq1,
            String c_name,
            String serq2) throws InterruptedException {

        logger.info(
                "****** Starting the TC:28 Verify the close button clears unsaved input form the requirements field   *************");

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

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            WaitUtils.waitFor1000Milliseconds();

            String currentdesc = addRequirementPage.getRequirementDescription();
            logger.info("current description name:" + currentdesc);
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickClose();
            logger.info("clicked on close button");
            WaitUtils.waitFor1000Milliseconds();

            req.clickRequirement(serq1);
            logger.info("Again clicked on the same req" + serq1);
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.setDescription(c_name);
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickOnRequirementIdLabel();
            WaitUtils.waitFor1000Milliseconds();

            String changedescription = addRequirementPage.getRequirementDescription();
            logger.info("changed desc:" + changedescription);
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.clickClose();
            logger.info("clicked on close button");
            WaitUtils.waitFor1000Milliseconds();

            addRequirementPage.ClickYesPopup();
            WaitUtils.waitFor1000Milliseconds();

            req.clickRequirement(serq2);
            logger.info("Again clicked on the same req" + serq2);
            WaitUtils.waitFor1000Milliseconds();

            String changedes = addRequirementPage.getRequirementDescription();
            logger.info("changed desc:" + changedes);
            WaitUtils.waitFor1000Milliseconds();

            if (currentdesc.equals(changedes)) {
                logger.info("changes done even after close button");
            } else {
                logger.info("changes are not done after close button");
            }
            WaitUtils.waitFor1000Milliseconds();

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }

        logger.info("************ TC:28 Finished *************************");
    }
}