package testCases.requirementTabTestCase;

import DataProviders.RequirementDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.authoTestCaseTab.AddTestcasePage;
import pageObjects.authoTestCaseTab.AuthorTestCasePage;
import pageObjects.authoTestCaseTab.LinkTestCasePage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;
@Test(dataProvider = "tc047", dataProviderClass = RequirementDataProvider.class, retryAnalyzer = RetryAnalyzer.class)

public class TC047 extends BaseClass {

    public void VerifyNameFieldIsMandatoryInAddTestcase(
            String mainProject,
            String epic,
            String requirementId,String desc
    ) throws InterruptedException {

        logger.info("****** Starting the Log in Test Case *****************");

        try {
            login();
            logger.info("Logged in successfully");

            WaitUtils.waitFor1000Milliseconds();

            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            IndividualModulePage individualModulePage = new IndividualModulePage(getDriver());
            AuthorTestCasePage authorTestCasePage = new AuthorTestCasePage(getDriver());
            AddTestcasePage addTestcasePage = new AddTestcasePage(getDriver());
            LinkTestCasePage linkTestCasePage = new LinkTestCasePage(getDriver());
            authorTestCasePage.clickAuthorTestcase();
            WaitUtils.waitFor3000Milliseconds();
            requirementTabPage.clickRequirementTab();

            requirementTabPage.clickDropdownToSelectProject(mainProject);
            logger.info("Expanded the main project: " + mainProject);

            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickArrowRightPointingForExpandModule(epic);
            WaitUtils.waitFor1000Milliseconds();
            requirementTabPage.clickOnModule(epic);
            WaitUtils.waitFor1000Milliseconds();

            authorTestCasePage.clickRequirement(requirementId);
            logger.info("Clicked on requirement id " + requirementId);

            WaitUtils.waitFor1000Milliseconds();
            authorTestCasePage.clickAddTestcase();
            logger.info("Clicked on AddTestCase");
            WaitUtils.waitFor1000Milliseconds();


            if (addTestcasePage.isDescriptionDisplayed()) {
                addTestcasePage.setDescription(desc);
                logger.info("Entered description successfully");
            } else {
                logger.error("Description field not displayed");
            }

            logger.info("Leaving Test Case Name field blank");

            addTestcasePage.clickSave();
            logger.info("Clicked Save button without entering Name");

            String actualError = addTestcasePage.waitForNameFieldRequiredError();
            Assert.assertEquals(actualError, "Error: Name is required.",
                    "Error message text mismatch");

            logger.info("Validation successful: Error message displayed - " + actualError);

        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: " + e.getMessage());
            throw e;
        }
        logger.info("************ TC045 Finished ************");
    }

}
