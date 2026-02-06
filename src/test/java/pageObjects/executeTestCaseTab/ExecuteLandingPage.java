package pageObjects.executeTestCaseTab;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import utils.WaitUtils;

public class ExecuteLandingPage extends BasePage {

    public ExecuteLandingPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================

    // landing page
    @FindBy(xpath = "//button[@class='help-btn']")
    WebElement helpBtn;

    @FindBy(id = "execute")
    WebElement tabexceute;

    @FindBy(xpath = "//div[@class='project ']")
    List<WebElement> allProjects;

    @FindBy(xpath = "//select[@class='text select-dropdown']")
    WebElement labelProjectName;

    @FindBy(xpath = "//div[contains(@class,'project')]//i[contains(@class,'fa-caret-right') and contains(@class,'toggle-icon')]")
    List<WebElement> expandArrows;

    @FindBy(xpath = "//div[contains(@class,'project') or contains(@class,'releases')]")
    List<WebElement> allModulesAndReleases;

    // release page

    @FindBy(id = "assignToMe")
    WebElement assignToMeRadio;

    @FindBy(id = "viewAll")
    WebElement viewAllRadio;

    @FindBy(id = "statusDropdown")
    WebElement statusDropdown;

    @FindBy(id = "createTestRunButton")
    WebElement createTestRunButton;

    @FindBy(xpath = "//div[@class='wrapper']")
    List<WebElement> rqCountWrapper;

    @FindBy(xpath = "(//select[@class='text select-dropdown'])[2]")
    WebElement dropdownFeature;

    @FindBy(xpath = "//img[@alt='Last Page']")
    WebElement lastPageArrowBtn;

    @FindBy(xpath = "//img[@alt='Previous']")
    WebElement arrowBackwardPrevious;

    @FindBy(xpath = "//img[@alt='Next']")
    WebElement arrowForwardNextPagination;

    @FindBy(xpath = "//div[@class='pagination-item']")
    WebElement divRequirementPagination;

    @FindBy(xpath = "//a[contains(@class,'runButton')]")
    List<WebElement> testRunLinks;

    @FindBy(xpath = "//div[@class='testlistcell1']/a[contains(text(),'TR')]")
    List<WebElement> allTestRunIds;

    @FindBy(xpath = "//div[@class='testlistcell1']/a[contains(text(),'TC')]")
    List<WebElement> allTestCaseIds;

    @FindBy(xpath = "//button[@id='clearsearchButton']")
    WebElement buttonClear;

    public WebElement buttonActionPlay(String trId) {
        return driver.findElement(
                By.xpath("//a[text()='" + trId + "']/ancestor::div[@class='requirement testlistframe-11']//button"));
    }

    // locators for create test run

    @FindBy(xpath = "//button[.//div[text()='SAVE']]")
    WebElement saveButtonInPopup;

    @FindBy(id = "closeModal")
    WebElement cancelButtonInPopup;

    @FindBy(xpath = "//input[@id='searchInput']")
    WebElement searchfield;

    @FindBy(xpath = "//div[@class='test-execution-label-3']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='text-wrapper-9']")
    WebElement currentPageNumber;

    @FindBy(xpath = "//div[contains(text(),'Test runs created successfully.')]")
    WebElement testRunCreatedSuccessMessage;

    @FindBy(xpath = "//img[@class='menu-open']")
    WebElement hamburgerMenuBtn;

    private WebElement suiteByName(String suiteName) {
        return driver.findElement(
                By.xpath("//div[contains(@class,'test-suite-row') and contains(normalize-space(.),'" + suiteName
                        + "')]"));
    }

    @FindBy(xpath = "//div[@id='testRunsWithCaseDetailsTable']")
    WebElement tableTestRunsWithCaseDetails;

    //  Locators for assigned to functionalities

    @FindBy(xpath = "//select[@class='assign-dropdown element-verify-the']")
    WebElement dropdownAssignTo;

    @FindBy(xpath = "//button[@title='Cancel']")
    WebElement assignToCrossButton;

    @FindBy(xpath = "//button[@title='Save']")
    WebElement assignToSaveButton;




    // ================= ACTIONS =================

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    Actions actions = new Actions(driver);


    // Action of Assign-to functionalities

    public void clickEditAssigneeByTR(String trId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the row using TR id

        String rowXpath = String.format(
                "//div[contains(@class,'testlistrow')][.//a[normalize-space()='%s']]",
                trId
        );

        WebElement row = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath))
        );

        // Locate Edit Assignee button inside that row

        WebElement editButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        row.findElement(By.xpath(".//button[@title='Edit assignee']"))
                )
        );

        editButton.click();
        editButton.click();
    }

    public void selectAssignTo(String empName) {
        Select s = new Select(dropdownAssignTo);
        dropdownAssignTo.click();
        s.selectByVisibleText(empName);
    }

    public void clickAssignToCancelButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(assignToCrossButton)).click();
    }

    public void clickAssignToSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(assignToSaveButton)).click();
    }

    // execute landing page

    public boolean isMentionedProjectNameVisible(String projectName) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(labelProjectName));
        String name = labelProjectName.getText();
        return name.equals(projectName);
    }

    public void clickHamburgerMenu() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(hamburgerMenuBtn)).click();

    }

    public void clickHelpBtn() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(helpBtn)).click();

    }

    private WebElement arrowRightToExpand(String moduleName) {
        return driver.findElement(
                By.xpath("//div[text()='" + moduleName + "']/..//i[@class='fa-solid fa-caret-right toggle-icon']"));
    }

    private WebElement arrowDownToCollapse(String moduleName) {
        return driver.findElement(
                By.xpath("//span[normalize-space()='" + moduleName + "']/..//i[contains(@class,'fa-caret-down')]"));
    }

    private WebElement releaseByName(String releaseName) {
        return driver.findElement(
                By.xpath("//div[contains(@class,'releases') and contains(normalize-space(.),'" + releaseName + "')]"));
    }

    private WebElement testCycleByName(String testCycleName) {
        return driver.findElement(By.xpath(
                "//div[contains(@class,'test-cycle-row') and contains(normalize-space(.),'" + testCycleName + "')]"));
    }

    // after clicking on the click test run button

    private WebElement requirementById(String requirementId) {
        return driver.findElement(By.xpath("//div[contains(text(),'" + requirementId + "')]"));
    }

    private WebElement testCaseCheckboxById(String testCaseId) {
        return driver.findElement(By.xpath("//div[contains(@class,'testlistrow1')]//a[normalize-space()='" + testCaseId
                + "']/ancestor::div[contains(@class,'testlistrow1')]//input[@type='checkbox']"));
    }

    private WebElement ClicktestCaseCheckboxById(String testCaseId) {
        return driver.findElement(By.xpath("//div[contains(@class,'testlistrow1')]//a[normalize-space()='" + testCaseId
                + "']/ancestor::div[contains(@class,'testlistrow1')]//input[@type='checkbox']"));
    }

    // ================= METHODS =================

    public void clickOnSuite(String suiteName) {
        suiteByName(suiteName).click();
    }

    public void clickExecuteTab() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();
        wait.until(ExpectedConditions.elementToBeClickable(tabexceute)).click();
        WaitUtils.waitFor1000Milliseconds();
    }

    public void clickArrowRightToExpandModule(String moduleName) {
        wait.until(ExpectedConditions.elementToBeClickable(arrowRightToExpand(moduleName))).click();
    }

    public void clickArrowDownToCollapseModule(String moduleName) {
        wait.until(ExpectedConditions.elementToBeClickable(arrowDownToCollapse(moduleName))).click();
    }

    // ================= METHOD =================

    // Methods for landing execute test case page
    public void clickProjectByName(String projectName) {
        WebElement project = allProjects.stream()
                .filter(p -> p.getText().trim().contains(projectName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Project not found: " + projectName));
        actions.moveToElement(project).click().perform();
    }

    public void clickOnProject() {
        driver.findElement(By.xpath("//select[@class='text select-dropdown']")).click();
    }

    // ================= METHODS =================

    public void clickToSelectProject(String moduleName) throws InterruptedException {
        //  arrowRightToExpand(moduleName).click();

        //  WaitUtils.waitFor1000Milliseconds();

        WebElement dropdown = driver.findElement(By.xpath("//select[@class='text select-dropdown']"));

        Select select = new Select(dropdown);
        select.selectByVisibleText(moduleName);

    }

    public WebElement selectedModuleOrReleaseName(String name) {
        By locator = By.xpath(
                "//div[(contains(@class,'project') or contains(@class,'releases')) and contains(normalize-space(.),'"
                        + name + "')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickRelease(String releaseName) {
        wait.until(ExpectedConditions.elementToBeClickable(releaseByName(releaseName))).click();
    }

    public void expandRelease(String releaseName) {
        WebElement release = wait.until(ExpectedConditions.elementToBeClickable(releaseByName(releaseName)));
        actions.moveToElement(release).click().perform();
    }

    public boolean isReleaseVisible(String releaseName) {
        return wait.until(ExpectedConditions.visibilityOf(releaseByName(releaseName))).isDisplayed();
    }

    public void expandSubTestCycle(String testCycleName) {
        WebElement cycle = wait.until(ExpectedConditions.elementToBeClickable(testCycleByName(testCycleName)));
        actions.moveToElement(cycle).click().perform();
    }

    public boolean isSubTestCycleVisible(String testCycleName) {
        return wait.until(ExpectedConditions.visibilityOf(testCycleByName(testCycleName))).isDisplayed();
    }

    public void clickTestCycle(String testCycleName) {
        WebElement testCycle = wait.until(ExpectedConditions.elementToBeClickable(testCycleByName(testCycleName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", testCycle);
        testCycle.click();
    }

    private WebElement TestsuiteByName(String suiteName) {
        return driver.findElement(
                By.xpath("//div[contains(@class,'test-suite-row') and contains(normalize-space(.),'" + suiteName
                        + "')]"));
    }

    public void expandTestSuit(String testSuitName) {
        WebElement cycle = wait.until(ExpectedConditions.elementToBeClickable(TestsuiteByName(testSuitName)));
        actions.moveToElement(cycle).click().perform();
    }

    public boolean isSuitVisible(String testsuitName) {
        return wait.until(ExpectedConditions.visibilityOf(TestsuiteByName(testsuitName))).isDisplayed();
    }

    // methods for individual release page
    public void selectAssignToMe() {
        wait.until(ExpectedConditions.elementToBeClickable(assignToMeRadio)).click();
    }

    public void selectViewAll() {
        wait.until(ExpectedConditions.elementToBeClickable(viewAllRadio)).click();
    }

    public boolean isAssignToMeSelected() {
        return assignToMeRadio.isSelected();
    }

    public void selectAssignedToMe() {
        wait.until(ExpectedConditions.elementToBeClickable(assignToMeRadio)).click();
    }

    public void ClickViewAllRadioButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewAllRadio)).click();
    }

    public boolean isViewAllSelected() {
        return viewAllRadio.isSelected();
    }

    public void selectStatus(String status) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By statusDropdownLocator = By.xpath(
                "//select[contains(@class,'test-run-text') and contains(@class,'select-dropdown')]"
        );

        WebElement dropdown = wait.until(
                ExpectedConditions.presenceOfElementLocated(statusDropdownLocator)
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
        js.executeScript("arguments[0].click();", dropdown);

        Select select = new Select(dropdown);
        select.selectByVisibleText(status);
    }


    public String getSelectedStatus() {
        return new Select(statusDropdown).getFirstSelectedOption().getText();
    }

    public void clickCreateTestRunButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createTestRunButton)).click();
    }

    public void selectTestCaseCheckbox(String testCaseId) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(testCaseCheckboxById(testCaseId)));
        if (!checkbox.isSelected())
            checkbox.click();
    }

    public boolean isTestCaseCheckboxSelected(String testCaseId) {
        return testCaseCheckboxById(testCaseId).isSelected();
    }

    public void clickTestRunById(String testRunId) throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();
        ;
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement testRun = testRunLinks.stream()
                .filter(e -> e.getText().trim().equals(testRunId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Test Run ID not found: " + testRunId));

        localWait.until(ExpectedConditions.elementToBeClickable(testRun));
        actions.moveToElement(testRun).click().perform();
    }

    public WebElement getTestRunById(String testRunId) {
        return testRunLinks.stream()
                .filter(e -> e.getText().trim().equals(testRunId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Test Run ID not found: " + testRunId));
    }

    // methods for pop after clicking the click test run button
    public void clickRequirementById(String requirementId) {
        wait.until(ExpectedConditions.elementToBeClickable(requirementById(requirementId))).click();
    }

    public void clickSaveInPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButtonInPopup)).click();
    }

    public void clickCancelInPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButtonInPopup)).click();
    }

    public boolean checkIfNextButtonIsClickable() {
        return "pointer"
                .equals(wait.until(ExpectedConditions.visibilityOf(arrowForwardNextPagination)).getCssValue("cursor"));
    }

    public void clickNextArrow() {
        actions.moveToElement(arrowForwardNextPagination).click().perform();
    }

    public void clickPreviousArrow() {
        if (checkIfPreviousButtonIsClickable()) {
            actions.moveToElement(arrowBackwardPrevious).click().perform();
        }
    }

    public boolean checkIfPreviousButtonIsClickable() {
        return "pointer"
                .equals(wait.until(ExpectedConditions.visibilityOf(arrowBackwardPrevious)).getCssValue("cursor"));
    }

    public void selectFeature(String featureName) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(dropdownFeature)));
        select.selectByVisibleText(featureName);
    }

    public int getCountRQInFeature() {
        return rqCountWrapper.size();
    }

    public String showPaginationOfRequirement() {
        return wait.until(ExpectedConditions.visibilityOf(divRequirementPagination)).getText();
    }

    public void searchTestCase(String testCaseID) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchfield));
        wait.until(ExpectedConditions.elementToBeClickable(searchfield));
        searchfield.clear();
        searchfield.sendKeys(testCaseID);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickOnTestRunById(String testRunId) {
        try {
            WebElement testRun = getTestRunById(testRunId);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", testRun);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(testRun));

            testRun.click();

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to find Test Run ID: " + testRunId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on Test Run ID: " + testRunId + " | " + e.getMessage());
        }
    }

    public int getTestRunIdCount() {
        By assignedToMe = By.xpath("//label[normalize-space()='Assigned to me']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(assignedToMe));
            return allTestRunIds.size();

        } catch (Exception e) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(assignedToMe));
            return allTestRunIds.size();
        }
    }

    public void clickOnClearButton() {
        By assignedToMe = By.xpath("//label[normalize-space()='Assigned to me']");
        Actions a = new Actions(driver);
        a.moveToElement(buttonClear).perform();
        buttonClear.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(assignedToMe));
    }

    public String[] getAllTestRunIds() {
        List<String> a = new ArrayList<>();
        for (WebElement element : allTestRunIds) {
            a.add(element.getText());
        }
        return a.toArray(new String[0]);
    }

    public boolean getVisibilityOfTestRunsWithCaseDetailsTable() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(tableTestRunsWithCaseDetails));
        return tableTestRunsWithCaseDetails.isDisplayed();
    }

    public String getCurrentPageNumber() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(currentPageNumber));
        return currentPageNumber.getText().trim();
    }

    public void clickPlayActionById(String tcIO) throws InterruptedException {
        WaitUtils.waitFor2000Milliseconds();
        ;
        new Actions(driver).moveToElement(buttonActionPlay(tcIO)).perform();
        WaitUtils.waitFor1000Milliseconds();
        buttonActionPlay(tcIO).click();
    }

    @FindBy(xpath = "(//button[@class='cell-4 runButton '])[1]")
    WebElement playButton;

    public void clickOnAnyPlayButton() throws InterruptedException {
        new Actions(driver).moveToElement(playButton).perform();
        WaitUtils.waitFor2000Milliseconds();
        ;
        wait.until(ExpectedConditions.elementToBeClickable(playButton)).click();
    }

    public void waitForTestRunInterfaceToLoad() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickOnTestSuite(String suiteName) {
        WebElement suiteElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='" + suiteName + "']")));
        suiteElement.click();
    }

    public List<String> getAllDisplayedStatuses() throws InterruptedException {
        List<String> statuses = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By tableContainer = By.xpath("//div[@class='table-content']");
        By statusCells = By.xpath("//div[@id='testRunsWithCaseDetailsTable']//div[@value]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(tableContainer));
        WebElement table = driver.findElement(tableContainer);

        js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", table);
        WaitUtils.waitFor1000Milliseconds();

        List<WebElement> statusElements = driver.findElements(statusCells);
        for (WebElement cell : statusElements) {
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", cell);
            WaitUtils.waitFor200Milliseconds();
            ;
            statuses.add(cell.getText().trim());
        }

        return statuses;
    }

    public int getTotalEntriesCount() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement paginationText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[@class='pagination-text']")));
            new Actions(driver).moveToElement(paginationText).perform();
            WaitUtils.waitFor2000Milliseconds();
            ;
            String text = paginationText.getText().trim(); // e.g. "Showing 1 to 10 of 27 entries"

            if (text.isEmpty() || text.contains("No entries")) {
                return 0;
            }

            // String[] parts = text.split(" ");

            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            System.out.println("Error while reading total entries: " + e.getMessage());
            return 0;
        }
    }

    public boolean isTestRunCreatedMessageDisplayed() {
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOf(testRunCreatedSuccessMessage));
            String message = msg.getText().trim();
            return message.equalsIgnoreCase("Test runs created successfully.");
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    @FindBy(xpath = "//div[@id='notification']")
    WebElement successNotification;

    public boolean waitForSuccessMessage(String expectedMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement notification = wait.until(ExpectedConditions.visibilityOf(successNotification));
            String actualMessage = notification.getText().trim();
            System.out.println("actual message "+ actualMessage);
            return actualMessage.equalsIgnoreCase(expectedMessage);
        } catch (TimeoutException e) {
            return false;
        }
    }

    @FindBy(xpath = "//div[contains(@class,'toast-body')]")
    private WebElement toastNotificationMessage;

    public String getToastNotificationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(toastNotificationMessage)).getText();
    }
    public boolean isNoMatchingTabVisible() {
        try {
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//div[text()='No matching results found']")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSaveButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(saveButtonInPopup));
            return saveButtonInPopup.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isCancelButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cancelButtonInPopup));
            return cancelButtonInPopup.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void validateHighlightedNode(String nodeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By highlightedNode = By.xpath(
                "//div[contains(@class,'active') and .//text()[contains(normalize-space(.),'" + nodeName + "')]]"
        );

        WebElement node = wait.until(ExpectedConditions.visibilityOfElementLocated(highlightedNode));

        if (!node.isDisplayed()) {
            throw new AssertionError("Node not highlighted: " + nodeName);
        }
    }

    private void goToLastPageIfAvailable() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By lastPageBtn = By.xpath(
                "//div[@id='paginationTest']//img[@alt='Last Page' and not(contains(@style,'opacity: 0.5'))]"
        );

        try {
            WebElement lastPage = wait.until(
                    ExpectedConditions.presenceOfElementLocated(lastPageBtn)
            );
            js.executeScript("arguments[0].click();", lastPage);
            wait.until(ExpectedConditions.stalenessOf(lastPage));
        } catch (TimeoutException ignored) {
        }
    }

    public void openLatestTestRunFromTable() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        goToLastPageIfAvailable();

        By allTrLinks = By.xpath(
                "//div[@class='table-content']" +
                        "//div[@id='testRunsWithCaseDetailsTable']" +
                        "//a[contains(@class,'text-wrapper-14') and starts-with(text(),'TR-')]"
        );

        List<WebElement> trLinks = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(allTrLinks, 0)
        );

        WebElement latestTr = trLinks.get(trLinks.size() - 1);

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", latestTr);
        wait.until(ExpectedConditions.elementToBeClickable(latestTr));

        js.executeScript("arguments[0].click();", latestTr);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("execute_rightPanel")
        ));
    }


    public String getOpenedTestRunId() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By trIdLocator = By.xpath(
                "//div[contains(@class,'test-run-text-2') and starts-with(text(),'TR-')]"
        );

        WebElement trIdElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(trIdLocator)
        );

        return trIdElement.getText().trim();
    }


    public void verifyTestRunCreationNotification(String trId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {
                WebElement bell = wait.until(
                        ExpectedConditions.elementToBeClickable(notificationBell)
                );
                js.executeScript("arguments[0].click();", bell);

                WebElement body = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(notificationBody)
                );

                List<WebElement> notifications = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(allNotifications)
                );

                for (WebElement element : notifications) {

                    String text = element.getText().trim();

                    if (text.contains(trId)) {
                        System.out.println(
                                "Test Run notification verified successfully: " + text
                        );
                        return;
                    }
                }

                js.executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollHeight",
                        body
                );

            } catch (StaleElementReferenceException ignored) {
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Test Run notification not found for TR ID: " + trId
        );
    }

    public String getLoggedInUserName() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By userLocator = By.xpath("//div[contains(@class,'JS') and @data-fullname]");

        WebElement userElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(userLocator)
        );

        String fullName = userElement.getAttribute("data-fullname").trim();

        if (fullName.isEmpty()) {
            throw new AssertionError("Logged-in user full name is empty");
        }

        return fullName;
    }

    public void verifyTestRunUpdateNotification(String trId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String updaterName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'" + trId + "'\\s+updated\\s+by\\s+"
                        + updaterName.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {
                WebElement bell = wait.until(
                        ExpectedConditions.elementToBeClickable(notificationBell)
                );
                js.executeScript("arguments[0].click();", bell);

                WebElement body = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(notificationBody)
                );

                List<WebElement> notifications = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(allNotifications)
                );

                for (WebElement element : notifications) {

                    String text = element.getText().trim();

                    if (text.contains(trId) && text.contains("updated by")) {

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Test Run update notification format mismatch.\nExpected Regex: "
                                            + expectedRegex +
                                            "\nActual Text: " + text
                            );
                        }

                        System.out.println(
                                "Test Run update notification verified successfully: " + text
                        );
                        return;
                    }
                }

                js.executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollHeight",
                        body
                );

            } catch (StaleElementReferenceException ignored) {}

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Test Run update notification not found for TR ID: " + trId
        );
    }


    public String verifyTestRunCreation_updationNotificationToast() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By toastBody = By.xpath(
                "//div[contains(@class,'toast-notification')]//div[contains(@class,'toast-body')]"
        );

        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toastBody)
        );

        String toastText = toast.getText().trim();

        if (toastText.isEmpty()) {
            throw new AssertionError("Toast message is empty");
        }

        System.out.println("Toast notification received: " + toastText);


        Pattern trPattern = Pattern.compile("TR-\\d+");
        Matcher matcher = trPattern.matcher(toastText);

        if (!matcher.find()) {
            throw new AssertionError(
                    "Toast notification does not contain a valid Test Run ID.\nActual: " + toastText
            );
        }

        String extractedTrId = matcher.group();
        System.out.println("Extracted Test Run ID from toast: " + extractedTrId);

        if (!(toastText.contains("created") || toastText.contains("updated"))) {
            throw new AssertionError(
                    "Toast notification does not indicate create or update action.\nActual: "
                            + toastText
            );
        }


        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastBody));

        return extractedTrId;
    }

  //Assign to

    public String getCurrentAssigneeFromTestRun(String trId) {

        By assigneeLocator = By.xpath(
                "//a[normalize-space()='" + trId + "']" +
                        "/ancestor::div[contains(@class,'testlistrow')]" +
                        "//span[contains(@class,'assign-username')]"
        );

        return wait.until(ExpectedConditions.visibilityOfElementLocated(assigneeLocator))
                .getText()
                .trim();
    }


    public void clickEditAssigneeIcon(String trId) {

        By editIcon = By.xpath(
                "//a[normalize-space()='" + trId + "']" +
                        "/ancestor::div[contains(@class,'testlistrow')]" +
                        "//button[contains(@class,'assign-icon-button') " +
                        "and not(contains(@class,'assign-save')) " +
                        "and not(contains(@class,'assign-cancel'))]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(editIcon)).click();
    }

    public void verifyAssignDropdownOpened(String trId)
    {

        By dropdown = By.xpath(
                "//a[normalize-space()='" + trId + "']" +
                        "/ancestor::div[contains(@class,'testlistrow')]" +
                        "//select[contains(@class,'assign-dropdown')]"
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdown));
    }

    public void selectUserFromAssignDropdown(String trId, String userName)
    {

        By dropdown = By.xpath(
                "//a[normalize-space()='" + trId + "']" +
                        "/ancestor::div[contains(@class,'testlistrow')]" +
                        "//select[contains(@class,'assign-dropdown')]"
        );

        WebElement selectElement =
                wait.until(ExpectedConditions.elementToBeClickable(dropdown));

        Select select = new Select(selectElement);
        select.selectByVisibleText(userName);
    }

    public void clickCancelAssignButton(String trId)
    {

        By cancelBtn = By.xpath(
                "//a[normalize-space()='" + trId + "']" +
                        "/ancestor::div[contains(@class,'testlistrow')]" +
                        "//button[contains(@class,'assign-cancel')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }

    public void verifyAssigneeUnchanged(String trId, String expectedAssignee)
    {

        String actualAssignee = getCurrentAssigneeFromTestRun(trId);

        if (!actualAssignee.equals(expectedAssignee)) {
            throw new AssertionError(
                    "Assignee changed after clicking Cancel.\nExpected: "
                            + expectedAssignee + "\nActual: " + actualAssignee
            );
        }

        System.out.println(
                "Assignee unchanged for " + trId + ": " + actualAssignee
        );
    }

    public void verifySaveButtonNotVisible(String trId) {

        By saveBtn = By.xpath(
                "//a[normalize-space()='" + trId + "']" +
                        "/ancestor::div[contains(@class,'testlistrow')]" +
                        "//button[contains(@class,'assign-save')]"
        );

        List<WebElement> elements = driver.findElements(saveBtn);

        if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
            throw new AssertionError("Save button is visible without selecting a user");
        }

        System.out.println("Save button is NOT visible before user selection");
    }

    public void verifySaveButtonVisible(String trId) {

        By saveBtn = By.xpath(
                "//a[normalize-space()='" + trId + "']" +
                        "/ancestor::div[contains(@class,'testlistrow')]" +
                        "//button[contains(@class,'assign-save')]"
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));

        System.out.println("Save button is visible after user selection");
    }

    public void verifyTestRunAssignedPopup(String testRunId, String assignedUser) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String loggedInUser = getLoggedInUserName();

        By popupLocator = By.xpath("//div[contains(@class,'toast-body')]");

        String expectedRegex =
                "'" + testRunId + "'\\s+has\\s+been\\s+assigned\\s+to\\s+"
                        + assignedUser.replace(" ", "\\s+")
                        + "\\s+by\\s+"
                        + loggedInUser.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 10000;

        while (System.currentTimeMillis() < endTime) {

            try {
                WebElement popup = wait.until(
                        ExpectedConditions.presenceOfElementLocated(popupLocator)
                );

                String actualText = popup.getText().trim();

                // Guard condition – ensure correct popup
                if (!actualText.contains("has been assigned")) {
                    continue;
                }

                if (!actualText.matches(expectedRegex)) {
                    throw new AssertionError(
                            "Test Run assign popup format mismatch.\nExpected Regex: "
                                    + expectedRegex +
                                    "\nActual Text: " + actualText
                    );
                }

                System.out.println(
                        "Test Run assigned popup verified successfully: " + actualText
                );
                return;

            } catch (StaleElementReferenceException ignored) {
            }
        }

        throw new AssertionError("Test Run assigned popup not found.");
    }

    public void verifyTestRunAssignedNotification(String trId, String assignedToUser) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String actionUser = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'" + trId + "'\\s+has\\s+been\\s+assigned\\s+to\\s+"
                        + assignedToUser.replace(" ", "\\s+")
                        + "\\s+by\\s+"
                        + actionUser.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {
                WebElement bell = wait.until(
                        ExpectedConditions.elementToBeClickable(notificationBell)
                );
                js.executeScript("arguments[0].click();", bell);

                WebElement body = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(notificationBody)
                );

                List<WebElement> notifications = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(allNotifications)
                );

                for (WebElement element : notifications) {

                    String text = element.getText().trim();

                    if (text.contains(trId) && text.contains("has been assigned")) {

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Test Run assigned notification format mismatch.\nExpected: "
                                            + expectedRegex +
                                            "\nActual: " + text
                            );
                        }

                        System.out.println(
                                "Test Run assigned notification verified successfully: " + text
                        );
                        return;
                    }
                }

                // Scroll to load older notifications
                js.executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollHeight",
                        body
                );

            } catch (StaleElementReferenceException ignored) {}

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Test Run assigned notification not found for TR: "
                        + trId + " assigned to: " + assignedToUser
        );
    }



}