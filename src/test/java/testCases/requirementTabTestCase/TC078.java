package testCases.requirementTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.requirementTab.AddRequirementPage;
import pageObjects.requirementTab.IndividualModulePage;
import pageObjects.requirementTab.RequirementTabPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC078 extends BaseClass {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void VerifyNotificationIsDisplayedTheCorrectUserName()
            throws InterruptedException {
        logger.info("****** Starting the TC078 *****************");
        try {
            login();
            logger.info("Logged in successfully");
            RequirementTabPage requirementTabPage = new RequirementTabPage(getDriver());
            AddRequirementPage addRequirementPage = new AddRequirementPage(getDriver());
            IndividualModulePage individualModulePage=new IndividualModulePage(getDriver());
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirement Tab");
            WaitUtils.waitFor3000Milliseconds();;
            requirementTabPage.clickRequirementTab();
            logger.info("Clicked on Requirements tab");
            requirementTabPage.clickEpicDropdown();
            logger.info("Clicked on Epic drop down");
            requirementTabPage.clickOnModule("New Epic");
            requirementTabPage.clickNewModule();
            logger.info("Clicked on create module");
            individualModulePage.setActualDescription("new");
            requirementTabPage.saveModule();
            logger.info("Clicked on save button");
            WaitUtils.waitFor1000Milliseconds();
            String loggedUserName=requirementTabPage.getLoggedInUserName();
            String actualUserName="Julie Kumari";
            Assert.assertEquals(loggedUserName,actualUserName,"User Name mismatched");
            WaitUtils.waitFor9000Milliseconds();
            logger.info("verified the correct user name has displayed");

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
