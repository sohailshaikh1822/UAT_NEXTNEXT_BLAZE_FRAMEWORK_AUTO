package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

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
            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("Navigated to Requirement page");
            WaitUtils.waitFor1000Milliseconds();

            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());

            for (int i = 0; i <= 1; i++) {

                requirementTabPage.clickDropdownToSelectProject(project);
                logger.info("Navigated to the project");
                WaitUtils.waitFor1000Milliseconds();

                requirementTabPage.clickArrowRightPointingForExpandModule(epic);
                logger.info("Navigated to Module");
                WaitUtils.waitFor1000Milliseconds();

                requirementTabPage.clickOnModule(feature);
                logger.info("clicked on specific module");
                WaitUtils.waitFor1000Milliseconds();

                if (i == 0) {
                    individualModulePage.setActualDescription(description);
                    logger.info("Typed new description");
                    WaitUtils.waitFor1000Milliseconds();

                    individualModulePage.clickSave();
                    logger.info("Clicked Save Button");
                    WaitUtils.waitFor1000Milliseconds();

                    getDriver().navigate().refresh();
                    WaitUtils.waitFor1000Milliseconds();
                }
            }

            WaitUtils.waitFor1000Milliseconds();

            Assert.assertEquals(individualModulePage.getActualDescription(), description);
            logger.info("Verified the description successfully..");
            WaitUtils.waitFor1000Milliseconds();

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