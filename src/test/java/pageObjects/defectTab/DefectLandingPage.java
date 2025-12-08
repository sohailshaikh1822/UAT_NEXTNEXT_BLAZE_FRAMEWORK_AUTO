package pageObjects.defectTab;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;
import java.time.Duration;

public class DefectLandingPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DefectLandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30-second timeout
    }

    // ================= LOCATORS DefectLanding page =================

    @FindBy(xpath = "//button[@class='help-btn']")
    WebElement helpBtn;

    @FindBy(id = "defect")
    WebElement defectTab;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement summary;

    @FindBy(id = "statusDropdown")
    WebElement statusDropdown;

    @FindBy(id = "severityDropdown")
    WebElement severityDropdown;

    @FindBy(id = "priorityDropdown")
    WebElement priorityDropdown;

    @FindBy(id = "assignToDropdown")
    WebElement assignToDropdown;

    @FindBy(id = "releaseDropdown")
    WebElement affectedReleaseDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Projects')]//following::select[@class='text select-dropdown'][1]")
    WebElement projectDropdown;

    @FindBy(id = "searchButton")
    WebElement searchBtn;

    @FindBy(xpath = "//div[@class='clear-button-text']")
    WebElement clearBtn;

    @FindBy(xpath = "//img[@alt='Last Page']")
    WebElement lastPageArrowBtn;

    @FindBy(xpath = "//img[@alt='Previous']")
    WebElement arrowBackwardPrevious;

    @FindBy(xpath = "//img[@alt='Next']")
    WebElement arrowForwardNextPagination;

    @FindBy(id = "createTestCaseButton")
    WebElement createTestCaseButton;

    @FindBy(xpath = "//p[@class='pagination-text']")
    WebElement totalDefectEntryCount;

    // ================= ACTIONS =================
    public void clickDefectTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(defectTab));
        defectTab.click();
    }

    public void enterSummary(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(summary));
        summary.clear();
        summary.sendKeys(text);
    }

    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();
    }

    public void clickClearButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(clearBtn));
        clearBtn.click();
    }

    public void clickLastPageArrow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(lastPageArrowBtn));
        lastPageArrowBtn.click();
    }

    public void clickPreviousArrow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowBackwardPrevious));
        arrowBackwardPrevious.click();
    }

    public void clickNextArrow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(arrowForwardNextPagination));
        arrowForwardNextPagination.click();
    }

    // public void clickCreateTestCaseButton() {
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    // WebElement button =
    // wait.until(ExpectedConditions.visibilityOf(createTestCaseButton));
    // wait.until(ExpectedConditions.elementToBeClickable(button));
    // if (button.isDisplayed() && button.isEnabled()) {
    // button.click();
    // }
    // }

    public void clickCreateTestCaseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(createTestCaseButton));
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", button);
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createTestCaseButton);
        }
    }

    public String getTotalDefectEntryCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(totalDefectEntryCount));
        return totalDefectEntryCount.getText();
    }

    public void selectStatus(String status) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)));
        select.selectByVisibleText(status);
    }

    public void selectSeverity(String severity) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(severityDropdown)));
        select.selectByVisibleText(severity);
    }

    public void selectPriority(String priority) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(priorityDropdown)));
        select.selectByVisibleText(priority);
    }

    public void selectAssignedTo(String assignedTo) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(assignToDropdown)));
        select.selectByVisibleText(assignedTo);
    }

    public void selectAffectedRelease(String affectedRelease) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(affectedReleaseDropdown)));
        select.selectByVisibleText(affectedRelease);
    }

    public void selectProject(String project) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(projectDropdown)));
        select.selectByVisibleText(project);
    }

    public void ClickDefectbyID(String defectId) {
        WebElement element = driver.findElement(
                By.xpath("//a[contains(normalize-space(.), '" + defectId + "')]"));
        element.click();
    }

    public void clickDefectByIndex(int defectIndex) {
        String xpath = "(//div[@class='testlistcell1'])[" + defectIndex + "]";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement defectElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        defectElement.click();
    }

    public void resizeColumn(String columnName, int offsetX) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String columnXpath = "//div[contains(@class,'text-wrapper') and normalize-space()='%s']";
        columnXpath = String.format(columnXpath, columnName);

        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(columnXpath)));

        String resizerXpath = columnXpath + "/parent::div/div[contains(@class,'resizer')]";
        WebElement resizerElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resizerXpath)));

        Actions actions = new Actions(driver);
        actions.clickAndHold(resizerElement)
                .moveByOffset(offsetX, 0)
                .release()
                .perform();
    }

    public void clickHelpBtn() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(helpBtn)).click();

    }

    public void clickStatusDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
    }

    public void clickSeverityDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(severityDropdown)).click();
    }

    public void clickPriorityDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(priorityDropdown)).click();
    }

    public void clickAssignedToDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(assignToDropdown)).click();
    }

    public void clickAffectedReleaseDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(affectedReleaseDropdown)).click();
    }

}
