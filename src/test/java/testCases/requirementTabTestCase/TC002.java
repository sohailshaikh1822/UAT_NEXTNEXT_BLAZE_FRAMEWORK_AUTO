package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

public class TC002 extends BaseClass {

    @Test(dataProvider = "tc002", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyDescriptionInModule(
            String project,
            String epic,
            String feature,
            String description
    ) throws InterruptedException {
        logger.info("****** Starting the Test Case *****************");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            for (int i = 0; i <= 1; i++) {
                requirementTabPage.clickArrowRightPointingForExpandModule(project);
                logger.info("Navigated to the project");
                requirementTabPage.clickArrowRightPointingForExpandModule(epic);
                logger.info("Navigated to Module");
                requirementTabPage.clickOnModule(feature);
                logger.info("clicked on specific module");
                if (i == 0) {
                    individualModulePage.setActualDescription(description);
                    logger.info("Typed new description");
                    individualModulePage.clickSave();
                    logger.info("Clicked Save Button");
                    getDriver().navigate().refresh();
                }
            }
            Assert.assertEquals(individualModulePage.getActualDescription(), description);
            logger.info("Verified the description successfully..");
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
