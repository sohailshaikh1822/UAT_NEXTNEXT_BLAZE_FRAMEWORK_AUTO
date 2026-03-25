package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC093 extends BaseClass {

    @Test(dataProvider = "tc093", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyRecycleBinCountAfterDeletingRequirement(
            String moduleName,
            String rqTitle)
            throws InterruptedException {

        logger.info("****** Starting TC093 ********");

        login();
        RequirementTabPage requirementTabPage =
                new RequirementTabPage(getDriver());
        IndividualModulePage individualModulePage =
                new IndividualModulePage(getDriver());
        AddRequirementPage addRequirementPage =
                new AddRequirementPage(getDriver());

        requirementTabPage.clickRequirementTab();
        WaitUtils.waitFor2000Milliseconds();
        requirementTabPage.clickRecycleBinButton();
        WaitUtils.waitFor2000Milliseconds();

        requirementTabPage.selectObjectFromDropdown("Requirement");
        WaitUtils.waitFor3000Milliseconds();
        String countText = requirementTabPage.getItemCountText();
        logger.info("Initial Count Text: " + countText);

        int initialCount = requirementTabPage.extractCount(countText);

        requirementTabPage.closeRecycleBin();
        logger.info("Closed the recycle bin");
        WaitUtils.waitFor2000Milliseconds();

//        requirementTabPage.clickRequirementTab();
//
//        WaitUtils.waitFor2000Milliseconds();
//        requirementTabPage.clickEpicDropdown();

        WaitUtils.waitFor2000Milliseconds();
        requirementTabPage.clickOnModule(moduleName);
        logger.info("Open module");


        WaitUtils.waitFor2000Milliseconds();
        individualModulePage.clickAddRequirement();
        logger.info("clicked on add requirement button");


        WaitUtils.waitFor2000Milliseconds();
        addRequirementPage.setRequirementId(rqTitle);

        WaitUtils.waitFor1000Milliseconds();
        addRequirementPage.clickSave();
        logger.info("Clicked Save button");
        WaitUtils.waitFor2000Milliseconds();
        String rqId = addRequirementPage.getRqId();
        addRequirementPage.clickClose();
        logger.info("Clicked Close button");


        WaitUtils.waitFor2000Milliseconds();
        requirementTabPage.DeleteRequirementById(rqId);
        WaitUtils.waitFor2000Milliseconds();

        requirementTabPage.clickRecycleBinButton();
        WaitUtils.waitFor3000Milliseconds();

        requirementTabPage.selectObjectFromDropdown("Requirement");
        WaitUtils.waitFor3000Milliseconds();

        String updatedText = requirementTabPage.getItemCountText();
        logger.info("Updated Count Text: " + updatedText);

        int updatedCount =  requirementTabPage.extractCount(countText);
        logger.info("****** TC093 Completed Successfully ********");

    }
}
