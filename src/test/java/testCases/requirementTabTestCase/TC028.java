package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

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

            RequirementTabPage reqPage = new RequirementTabPage(getDriver());
            reqPage.clickRequirementTab();
            logger.info("clicked on requirement tab");

            reqPage.clickArrowRightPointingForExpandModule(project);
            logger.info("Selected project" + project);

            reqPage.clickOnModule(epic);
            logger.info("Selected epic" + epic);

            IndividualModulePage req = new IndividualModulePage(getDriver());
            req.clickRequirement(rq);
            logger.info("Selected Requirement" + rq);

            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());

            String currentdesc = addRequirementPage.getRequirementDescription();
            logger.info("current description name:" + currentdesc);

            addRequirementPage.clickClose();
            logger.info("clicked on close button");

            req.clickRequirement(serq1);
            logger.info("Again clicked on the same req" + serq1);

            addRequirementPage.setDescription(c_name);
            addRequirementPage.clickOnRequirementIdLabel();
            String changedescription = addRequirementPage.getRequirementDescription();
            logger.info("changed desc:" + changedescription);

            addRequirementPage.clickClose();
            logger.info("clicked on close button");
            addRequirementPage.ClickYesPopup();

            req.clickRequirement(serq2);
            logger.info("Again clicked on the same req" + serq2);
            String changedes = addRequirementPage.getRequirementDescription();
            logger.info("changed desc:" + changedes);

            if (currentdesc.equals(changedes)) {
                logger.info("changes done even after close button");
            } else {
                logger.info("changes are not done after close button");
            }
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
