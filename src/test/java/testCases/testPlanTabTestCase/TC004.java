package testCases.testPlanTabTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.TestPlanDataProvider;
import pageObjects.testPlanTab.TestPlanLandingPage;
import testBase.BaseClass;
import utils.RetryAnalyzer;

import java.util.List;

public class TC004 extends BaseClass {

    @Test(dataProvider = "tc004", dataProviderClass = TestPlanDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyReleaseListUpdatesBasedOnProjectSelection(String ReleaseName, String SecondReleaseName)
            throws InterruptedException {
        logger.info(
                "****** Starting Test Case: Verify Release List Updates Based on Project Selection *****************");
        try {
            login();
            logger.info("Logged in successfully");

            TestPlanLandingPage testPlanPage = new TestPlanLandingPage(getDriver());

            testPlanPage.selectTestPlanTab();
            logger.info("Navigated to Test Plan tab");

            testPlanPage.expandSidebarIfCollapsed();
            logger.info("Sidebar expanded if it was collapsed");

            testPlanPage.expandProjectSTG("STG- PulseCodeOnAzureCloud");
            logger.info("Expanded STG Project");

            testPlanPage.expandRelease(ReleaseName);
            List<String> setDrvChildren = testPlanPage.getChildNodesOfRelease("SET- DRV");
            logger.info("Child nodes under SET- DRV: " + setDrvChildren);

            Assert.assertTrue(!setDrvChildren.isEmpty(),
                    "No child nodes found under release 'SET- DRV' after expanding!");

            testPlanPage.expandRelease(SecondReleaseName);
            List<String> SecondReleaseNameChildren = testPlanPage.getChildNodesOfRelease("Release 039");
            logger.info("Child nodes under Release 039: " + SecondReleaseNameChildren);

            Assert.assertTrue(!SecondReleaseNameChildren.isEmpty(),
                    "No child nodes found under release 'Release 039' after expanding!");

            Assert.assertNotEquals(setDrvChildren, SecondReleaseNameChildren,
                    "Child nodes under SET- DRV and Release 039 are identical, expected them to differ!");

            logger.info("Verified release list refreshes correctly when selecting different releases");

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
