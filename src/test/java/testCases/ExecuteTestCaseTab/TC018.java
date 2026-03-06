package testCases.ExecuteTestCaseTab;

import DataProviders.ExecuteTestCaseDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.executeTestCaseTab.ExecuteLandingPage;
import pageObjects.executeTestCaseTab.IndividualTestRun;
import pageObjects.executeTestCaseTab.LinkDefectPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;
import utils.WaitUtils;

public class TC018 extends BaseClass {

    @Test(dataProvider = "tc018", dataProviderClass = ExecuteTestCaseDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifySearchByDefectIdAndLinkDefect(String projectName,
                                                    String release,
                                                    String cycle,
                                                    String suite,
                                                    String testRunId,
                                                    String dfId
    ) throws InterruptedException {

        logger.info("****** Starting Test Case: Verify Project Selection from Dropdown *****************");

        try {
            login();
            logger.info("Logged in successfully");
            WaitUtils.waitFor1000Milliseconds();

            ExecuteLandingPage executeLandingPage = new ExecuteLandingPage(getDriver());

            executeLandingPage.clickExecuteTab();
            logger.info("Clicked on the execute test case tab ..");
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickOnProject();
            logger.info("Clicked on project Name ....");
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.expandRelease(release);
            logger.info("Expanded the release {}", release);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickArrowRightToExpandModule(cycle);
            logger.info("Expanded the cycle : {}", cycle);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.clickOnSuite(suite);
            logger.info("clicked on the suite : {}", suite);
            WaitUtils.waitFor1000Milliseconds();

            executeLandingPage.searchTestCase(testRunId);
            logger.info("Entered the test run Id : {}", testRunId);
            WaitUtils.waitFor1000Milliseconds();

//            executeLandingPage.clickTestRunById(testRunId);
            executeLandingPage.clickPlayActionById(testRunId);
            logger.info("clicked on test run Id {}", testRunId);
            WaitUtils.waitFor1000Milliseconds();

            IndividualTestRun individualTestRun = new IndividualTestRun(getDriver());

            individualTestRun.clickLinkDefect();
            logger.info("Clicked on link defect ");
            WaitUtils.waitFor1000Milliseconds();

            LinkDefectPage linkDefectPage = new LinkDefectPage(getDriver());

            linkDefectPage.enterDefectSearch(dfId.replaceAll("[^0-9]", ""));
            logger.info("Entered the defect defect id");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.clickSearchButton();
            logger.info("Searched the defect");
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.clickRadioButtonBesideDefectId(dfId);
            logger.info("clicked on defect id {}", dfId);
            WaitUtils.waitFor1000Milliseconds();

            linkDefectPage.clickLink();
            logger.info("Clicked on link button ");
            WaitUtils.waitFor1000Milliseconds();

            String displayText = new IndividualTestRun(getDriver()).getDefectIdAfterLink();
            Assert.assertEquals(displayText, dfId);
            logger.info("verified successfully");
            WaitUtils.waitFor1000Milliseconds();

            IndividualTestRun individualTestRun1 = new IndividualTestRun(getDriver());
            individualTestRun1.clickLinkDefect();
            WaitUtils.waitFor1000Milliseconds();

            LinkDefectPage linkDefectPage1 = new LinkDefectPage(getDriver());
            linkDefectPage1.clickUnlinkButtonByDefectId(dfId);
            WaitUtils.waitFor1000Milliseconds();

            logger.info("Unlinked successfully");

        } catch (AssertionError e) {
            logger.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
            throw e;
        }

        logger.info("************ Test Case Finished *************************");
    }
}