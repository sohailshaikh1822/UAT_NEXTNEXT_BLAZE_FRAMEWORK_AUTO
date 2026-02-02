package pageObjects.testPlanTab;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import utils.WaitUtils;

public class TestPlanLandingPage extends BasePage {
    public TestPlanLandingPage(WebDriver driver) {
        super(driver);
    }

    // --- Locators ---
    @FindBy(xpath = "//span[@id='testplan']")
    private WebElement tabTestPlan;

    @FindBy(xpath = "//i[@title='New Release']")
    private WebElement btnNewRelease;

    @FindBy(xpath = "//i[@title='New TestCycle']")
    private WebElement btnNewTestCycle;

    @FindBy(xpath = "//span[@title='New TestSuite']")
    private WebElement btnNewTestSuite;

    @FindBy(xpath = "//i[@title='Delete']")
    private WebElement btnDelete;

    @FindBy(xpath = "//button[@id='confirmBtn']")
    WebElement buttonConfirmDelete;

    @FindBy(xpath = "//button[@id='cancelBtn']")
    WebElement buttonCancelDelete;

    @FindBy(xpath = "//img[@alt='Close Sidebar']")
    WebElement buttonCollapseToggle;

    @FindBy(xpath = "//img[@alt='Open Sidebar']")
    WebElement buttonExpandToggle;

    @FindBy(xpath = "//ul[@class='sidebar-tree']")
    WebElement sidebarTree;

    @FindBy(xpath = "//div[@id='notification']")
    private WebElement notification;

    public WebElement expandArrow(String name) {
        return driver.findElement(By.xpath("//div[text()='" + name + "']/span/i"));
    }

    @FindBy(xpath = "//div[@class='dashboard-card']//div[text()='Total Releases']/following-sibling::div[@class='card-value']")
    private WebElement totalReleasesValue;

    @FindBy(xpath = "//div[@class='dashboard-card']//div[text()='Total Test Cycles']/following-sibling::div[@class='card-value']")
    private WebElement totalTestCyclesValue;

    @FindBy(xpath = "//div[@class='dashboard-card']//div[text()='Total Test Suites']/following-sibling::div[@class='card-value']")
    private WebElement totalTestSuitesValue;

    @FindBy(xpath = "//div[contains(@class,'releases')]")
    private List<WebElement> allProjects;

    @FindBy(xpath = "//div[contains(@class,'test-plan-releases-name-parent')]/input")
    private WebElement inputReleaseName;

    @FindBy(xpath = "//select[contains(@class,'testcase-select')]")
    private WebElement dropdownReleaseStatus;

    @FindBy(xpath = "//div[.//div[text()='Start Date']]//input[@type='date']")
    private WebElement inputStartDate;

    @FindBy(xpath = "//div[.//div[text()='End Date']]//input[@type='date']")
    private WebElement inputEndDate;

    @FindBy(xpath = "//div[contains(@class,'rich-editor-scrollable')]//div[contains(@class,'test-plan-releases-prototype')]//div[em]")
    private WebElement inputDescription;

    @FindBy(id = "precondition")
    private WebElement inputReleaseNotes;

    @FindBy(xpath = "//button[.//div[text()='SAVE']]")
    private WebElement btnSaveRelease;

    @FindBy(xpath = "//div[@class='project ']")
    WebElement leftPanelProjectName;

    @FindBy(xpath = "//span[@class='text-wrapper-22 project-selector']")
    private WebElement projectDropdown;

    @FindBy(xpath = "//div[@class='project-dropdown-menu']")
    private WebElement projectDropdownMenu;

    @FindBy(xpath = "//div[@class='frame']")
    private WebElement testPlanIframe;

    // New Locators for recycle bin functionalities

    @FindBy(xpath = "//i[@title='Recycle Bin']")
    private WebElement recycleBinButton;

    @FindBy(xpath = "//option[@value='TestPlan']")
    private WebElement menuDropdown;

    @FindBy(xpath = "//option[@value='All']")
    private WebElement objectsDropdown;

    @FindBy(xpath = "//span[@class='rb-count']")
    private WebElement getCurrentItemsAndSelectedItem;

    @FindBy(xpath = "//table[@class='rb-table']/thead/tr")
    private WebElement getHeaderAllColumnName;

    @FindBy(xpath = "//button[contains(text(),'Restore')]")
    public WebElement restoreButton;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    private WebElement closeButton;

    @FindBy(xpath = "//label[text()='Object(s)']/following-sibling::select")
    WebElement objectDropdown;

    @FindBy(xpath = "//label[text()='Object(s)']/following-sibling::select/option")
    List<WebElement> objectDropdownOptions;


    @FindBy(xpath = "//table//tbody//tr")
    public List<WebElement> recycleBinRows;

    @FindBy(xpath = "//table//thead//th[normalize-space()]")
    List<WebElement> recycleBinColumnHeaders;

    @FindBy(xpath = "//div[@class='rb-table-wrapper']")
    WebElement scrollRecycleBinList;



// Actions for the Recycle bin functionalities

    public void clickRecycleBin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(recycleBinButton)).click();
    }

    public void selectMenuOption(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//select[option[@value='" + value + "']]")));

        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public void selectObjectType(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//select[option[@value='" + value + "']]")));

        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public String getRecycleBinItemCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(getCurrentItemsAndSelectedItem)).getText();
    }

    public List<String> getAllColumnHeaders() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(getHeaderAllColumnName));

        List<WebElement> headers = driver.findElements(
                By.xpath("//table[@class='rb-table']/thead/tr/th"));

        List<String> headerNames = new ArrayList<>();
        for (WebElement header : headers) {
            headerNames.add(header.getText().trim());
        }
        return headerNames;
    }

    public void clickRestoreButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(restoreButton)).click();
    }
    public void clickCloseButtonOfRecycleBinPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }

    public void smoothScrollRecycleBin() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long height = (long) js.executeScript("return arguments[0].scrollHeight;", scrollRecycleBinList);

        for (int i = 0; i < height; i += 500) {
            js.executeScript("arguments[0].scrollTop = arguments[1];", scrollRecycleBinList, i);
            Thread.sleep(200);
        }
    }

    public void selectRadioById(String idValue) {
    String dynamicXpath = "//tr[.//td[normalize-space(.)='" + idValue + "']]//input[@type='radio']";

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement radioBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));

    // In case normal click fails (common in tables)
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioBtn);
}

    public void clickOnRecycleBinIcon() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();;
        recyclebinIcon.click();
        WaitUtils.waitFor1000Milliseconds();;
    }

    public void clickOnObjectDropdown() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(objectDropdown))
                .click();
    }

    public List<String> getObjectDropdownValues() {
        Select select = new Select(objectDropdown);
        List<String> values = new ArrayList<>();
        for (WebElement option : select.getOptions()) {
            values.add(option.getText().trim());
        }
        return values;
    }

    public void selectObjectDropdownValue(String value) {
        Select select = new Select(objectDropdown);
        select.selectByVisibleText(value);
        WaitUtils.waitFor2000Milliseconds();
    }

    public boolean verifyOnlySelectedObjectDisplayed(String expectedType) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(recycleBinRows));

        for (WebElement row : recycleBinRows) {
            String actualType = row.findElement(By.xpath("./td[2]")).getText().trim();

            if (!expectedType.equalsIgnoreCase("All")) {
                if (!actualType.equalsIgnoreCase(expectedType)) {
                    System.out.println("Mismatch found. Expected: " + expectedType + " but found: " + actualType);
                    return false;
                }
            }
        }
        return true;
    }
    //For column header names
    public List<String> getRecycleBinColumnHeaders() {
        List<String> headers = new ArrayList<>();
        for (WebElement header : recycleBinColumnHeaders) {
            String text = header.getText().trim();
            if (!text.isEmpty()) {
                headers.add(text);
            }
        }
        return headers;
    }

    //verifies each rows- ID, Name, Type, Deleted By, Deleted Date has valid data

    public boolean validateRecycleBinGridData() {

        boolean hasAtLeastOneValidRow = false;

        for (WebElement row : recycleBinRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() < 6) {
                continue;
            }
            String id = cells.get(1).getText().trim();
            if (id.isEmpty()) {
                continue;
            }
            hasAtLeastOneValidRow = true;
            String name = cells.get(2).getText().trim();
            String type = cells.get(3).getText().trim();
            String deletedBy = cells.get(4).getText().trim();
            String deletedDate = cells.get(5).getText().trim();
            if (name.isEmpty()) {
                System.out.println("Empty Name found for ID: " + id);
                return false;
            }
            if (type.isEmpty()) {
                System.out.println("Empty Type found for ID: " + id);
                return false;
            }
            if (deletedDate.isEmpty()) {
                System.out.println("Empty Deleted Date found for ID: " + id);
                return false;
            }
        }
        if (!hasAtLeastOneValidRow) {
            System.out.println("No valid data rows found in Recycle Bin");
            return false;
        }
        return true;
    }


    public WebElement releaseTestCycleTestSuite(String releaseOrTestCycleOrTestSuite) {
        return driver.findElement(By.xpath("//div[text()='" + releaseOrTestCycleOrTestSuite + "']"));
    }

    @FindBy(xpath = "//body/div[@class='requirements']/div[@class='frame']" +
            "/div[@id='sidebar']/div[@id='project']/ul[@class='sidebar-tree']/li")
    private List<WebElement> projectReleaseNodes;

    //Recycle bin
    @FindBy(xpath = "//i[@title='Recycle Bin']")
    WebElement recyclebinIcon;

    // --- Actions ---

    public void clickOnReleaseOrTestCycleOrTestSuite(String releaseOrTestCycleOrTestSuite) throws InterruptedException {
        WaitUtils.waitFor2000Milliseconds();;
        releaseTestCycleTestSuite(releaseOrTestCycleOrTestSuite).click();

    }

    public void expandOnReleaseOrTestCycleOrTestSuite(String releaseOrTestCycleOrTestSuite) {
        expandArrow(releaseOrTestCycleOrTestSuite).click();
    }

    public void selectTestPlanTab() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1920, 1080));
        WaitUtils.waitFor2000Milliseconds();
        tabTestPlan.click();
        WaitUtils.waitFor2000Milliseconds();
    }

    public void expandSidebarIfCollapsed() {
        if (!sidebarTree.isDisplayed()) {
            buttonExpandToggle.click();
        }
    }

    public void clickCollapseToggle() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();
        buttonCollapseToggle.click();
        WaitUtils.waitFor2000Milliseconds();
    }

    public void clickExpandToggle() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();
        buttonExpandToggle.click();
        WaitUtils.waitFor2000Milliseconds();
    }

    public boolean getSidebarVisibility() {
        return sidebarTree.isDisplayed();
    }

    public void expandProjectSTG(String projectName) {

        WebElement arrow = expandArrow(projectName);
        String rotateStyle = arrow.getAttribute("style");

        if (rotateStyle == null || !rotateStyle.contains("rotate(90deg)")) {
            arrow.click();
            System.out.println("Expanded project: " + projectName);
        } else {
            System.out.println("Project '" + projectName + "' is already expanded.");
        }

    }

    public void expandRelease(String releaseName) {
        expandArrow(releaseName).click();
    }

    public void expandTestCycle(String testcycle) {
        expandArrow(testcycle).click();
    }

    public void clickNewRelease() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();;
        btnNewRelease.click();
        WaitUtils.waitFor1000Milliseconds();;
    }

    public void clickNewTestCycle() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();;
        btnNewTestCycle.click();
        WaitUtils.waitFor1000Milliseconds();;
    }

    public void clickNewTestSuite() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();;
        btnNewTestSuite.click();
        WaitUtils.waitFor1000Milliseconds();;
    }

    public void clickDelete() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();;
        btnDelete.click();
        WaitUtils.waitFor1000Milliseconds();;
    }

    public void clickOnConfirmDeleteYes(String releaseCycleSuiteName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        buttonConfirmDelete.click();
    }

    public int getTotalReleases() {
        return Integer.parseInt(totalReleasesValue.getText().trim());
    }

    public int getTotalTestCycles() {
        return Integer.parseInt(totalTestCyclesValue.getText().trim());
    }

    public int getTotalTestSuites() {
        return Integer.parseInt(totalTestSuitesValue.getText().trim());
    }

    public void selectProjectByName(String projectName) {
        for (WebElement project : allProjects) {
            if (project.getText().trim().equalsIgnoreCase(projectName)) {
                project.click();
                return;
            }
        }
        throw new RuntimeException("Project '" + projectName + "' not found in the dropdown!");
    }

    public boolean isProjectSelected(String projectName) throws InterruptedException {
        for (WebElement project : allProjects) {
            if (project.getText().trim().equalsIgnoreCase(projectName)) {
                project.click();
                WaitUtils.waitFor1000Milliseconds();
                String classAttr = project.getAttribute("class");
                return classAttr.contains("active");
            }
        }
        return false;
    }

    public List<String> getAllProjectNames() {
        List<String> projectNames = new ArrayList<>();
        for (WebElement project : allProjects) {
            projectNames.add(project.getText().trim());
        }
        return projectNames;
    }

    private String releaseChildNodesXpath(String releaseName) {
        return "//div[contains(@class,'releases') and contains(normalize-space(.),'" + releaseName + "')]"
                + "/following-sibling::ul//div[contains(@class,'test-cycle-row')]";
    }

    public List<String> getChildNodesOfRelease(String releaseName) {
        List<WebElement> children = driver.findElements(By.xpath(releaseChildNodesXpath(releaseName)));
        List<String> childNames = new ArrayList<>();
        for (WebElement child : children) {
            childNames.add(child.getText().trim());
        }
        return childNames;
    }

    public void enterReleaseName(String releaseName) {
        inputReleaseName.clear();
        inputReleaseName.sendKeys(releaseName);
    }

    public void selectReleaseStatus(String status) {
        Select select = new Select(dropdownReleaseStatus);
        select.selectByVisibleText(status);
    }

    public String convertToHTML5Date(String dateDDMMYYYY) {
        String[] parts = dateDDMMYYYY.split("-");
        return parts[2] + "-" + parts[1] + "-" + parts[0]; // yyyy-MM-dd
    }

    public void selectStartDate(String dateDDMMYYYY) {
        String html5Date = convertToHTML5Date(dateDDMMYYYY);
        inputStartDate.sendKeys(html5Date);
    }

    public void selectEndDate(String dateDDMMYYYY) {
        String html5Date = convertToHTML5Date(dateDDMMYYYY);
        inputEndDate.sendKeys(html5Date);
    }

    public void enterDescription(String description) {
        WebElement descriptionBeforeClick = driver.findElement(By.xpath("//div[@class='testPlan-prototype']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(descriptionBeforeClick));
        descriptionBeforeClick.click();

        WebElement descriptionAfterClick = driver
                .findElement(By.xpath("//div[@class='rte-editor ql-container ql-snow']/div[@contenteditable='true']"));
        wait.until(ExpectedConditions.elementToBeClickable(descriptionAfterClick));
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '';", descriptionAfterClick);
        descriptionAfterClick.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        descriptionAfterClick.sendKeys(Keys.BACK_SPACE);
        descriptionAfterClick.clear();
        descriptionAfterClick.sendKeys(description);
    }

    public void enterReleaseNotes(String notes) {
        inputReleaseNotes.clear();
        inputReleaseNotes.sendKeys(notes);
    }

    public void clickSaveRelease() throws InterruptedException {
        btnSaveRelease.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(notification));
        WaitUtils.waitFor2000Milliseconds();
    }

    public void clickOnTheProjectName() {
        leftPanelProjectName.click();
    }

    public boolean isReleasePresentInList(String releaseName) {
        try {
            String releaseXpath = "//div[contains(@class,'releases') and contains(normalize-space(.),'" + releaseName
                    + "')]";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(releaseXpath)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isTestingCycleVisible(String releaseName, String cycleName) {
        try {
            String xpath = "//div[text()='" + releaseName + "']/following::div[text()='" + cycleName + "']";
            WebElement cycleElement = driver.findElement(By.xpath(xpath));
            return cycleElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTestingSuiteVisible(String cycleName, String suiteName) {
        try {
            String xpath = "//div[text()='" + cycleName + "']/following::div[text()='" + suiteName + "']";
            WebElement suiteElement = driver.findElement(By.xpath(xpath));
            return suiteElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNotificationMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(notification));
            return notification.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isReleaseCreatedSuccessfully() {
        String message = getNotificationMessage();
        return message != null && message.contains("Release created successfully");
    }

    public boolean areCyclesDisplayedUnderRelease(String releaseName) {
        try {
            for (WebElement node : projectReleaseNodes) {
                if (node.getText().contains(releaseName)) {
                    List<WebElement> cycles = node.findElements(By.xpath(".//ul/li"));
                    return cycles.size() > 0;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception in areCyclesDisplayedUnderRelease: " + e.getMessage());
            return false;
        }
    }

    public int getDuplicateReleaseCount(String releaseName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement sidebarTree = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='sidebar-tree']")));

            List<WebElement> releases = sidebarTree.findElements(
                    By.xpath(".//div[contains(@class,'releases') and contains(normalize-space(.),'" + releaseName
                            + "')]"));
            return releases.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void selectProjectFromDropdown(String projectName) {

        projectDropdown.click();

        WebElement projectOption = driver.findElement(By.xpath(
                "//div[@class='project-dropdown-menu']//div[@class='project-dropdown-item' and normalize-space(text())='"
                        + projectName + "']"));
        projectOption.click();
    }

    public String getLeftPanelProjectName() {
        return leftPanelProjectName.getText().trim();
    }

    public List<String> getExpectedProjectsFromSidebar() {
        List<WebElement> projectElements = driver.findElements(
                By.xpath("//div[@id='project']//div[contains(@class,'releases')]"));

        List<String> expectedProjects = new ArrayList<>();
        for (WebElement el : projectElements) {
            String text = el.getText().trim();
            expectedProjects.add(text);
        }

        return expectedProjects;
    }
    //iframe
    public void switchToIFrame() {
        try {
            driver.switchTo().frame(testPlanIframe);
            System.out.println("Switched to Test Plan iframe successfully");
        } catch (Exception e) {
            System.out.println("Failed to switch to Test Plan iframe: " + e.getMessage());
            throw e;
        }
    }

    public boolean verifyRestoreToastMessage(String expectedId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By toastLocator = By.xpath("//div[contains(@class,'toast-body')]");

        try {
            WebElement toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(toastLocator)
            );

            String toastText = toast.getText().trim();
            System.out.println("Toast message displayed: " + toastText);

            return toastText.contains(expectedId)
                    && toastText.toLowerCase().contains("restored");

        } catch (TimeoutException e) {
            System.out.println("Restore toast notification not displayed");
            return false;
        }
    }

}
