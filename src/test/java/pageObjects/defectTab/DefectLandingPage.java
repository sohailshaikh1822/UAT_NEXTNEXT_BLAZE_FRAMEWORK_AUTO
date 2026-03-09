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

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import utils.WaitUtils;

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

    @FindBy(xpath = "//button[@id='createTestCaseButton']")
    WebElement createTestCaseButton;

    @FindBy(xpath = "//p[@class='pagination-text']")
    WebElement totalDefectEntryCount;

    @FindBy(xpath = "//button[@class='export-save-button ']")
    WebElement saveExportBtn;


    // ================= ACTIONS =================


    public void clickSaveExportButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveExportBtn));
        saveExportBtn.click();
    }

    public void clickDefectTab() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();
        wait.until(ExpectedConditions.elementToBeClickable(defectTab)).click();
        WaitUtils.waitFor1000Milliseconds();

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

    public void selectSeverityByIndex(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(severityDropdown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public void selectPriorityByIndex(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(priorityDropdown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public void selectAssignToByIndex(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(assignToDropdown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public void selectAffectedReleaseByIndex(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(affectedReleaseDropdown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    public void selectStatusByIndex(int index) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }


    //EXPORT And EXPORT ALL Button

    @FindBy(xpath = "//button[contains(@class,'export-defect') and .//div[contains(text(),'EXPORT')]]")
    WebElement exportButton;

    @FindBy(xpath = "//div[contains(@class,'test-log-history-export-modal')]")
    WebElement exportModal;

    @FindBy(xpath = "//div[contains(@class,'test-log-history-export-modal-header')]//span[text()='Export Defects']")
    WebElement exportModalTitle;

    @FindBy(id = "exportFileType")
    WebElement fileTypeDropdown;

    @FindBy(xpath = "//label[contains(@class,'download-background-wrapper')]//input[@type='checkbox']")
    WebElement downloadInBackgroundCheckbox;

    @FindBy(xpath = "//div[contains(@class,'test-log-history-export-modal-footer')]//button[.//div[normalize-space()='CANCEL']]")
    WebElement cancelButton;

    @FindBy(xpath = "//button[contains(@class,'export-save-button')]")
    WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class,'testlistrow')]")
    List<WebElement> defectRows;

    @FindBy(xpath = "//button[.//div[normalize-space()='EXPORT ALL']]")
    WebElement exportAllButton;

    public void clickExportButton() {
        exportButton.click();
    }

    public void verifyExportButtonVisibleAndClickable() {

        if (!exportButton.isDisplayed()) {
            throw new AssertionError("Export button is not visible.");
        }

        if (!exportButton.isEnabled()) {
            throw new AssertionError("Export button is not clickable.");
        }

        exportButton.click();
    }
    public int getVisibleDefectCount() {

        wait.until(ExpectedConditions.visibilityOfAllElements(defectRows));

        return defectRows.size();
    }

    public void selectExcelFileType() {

        wait.until(ExpectedConditions.visibilityOf(fileTypeDropdown));

        Select select = new Select(fileTypeDropdown);
        select.selectByVisibleText("Excel (.xlsx)");
    }

    public void selectCsvFileType() {

        wait.until(ExpectedConditions.visibilityOf(fileTypeDropdown));

        Select select = new Select(fileTypeDropdown);
        select.selectByVisibleText("CSV (.csv)");
    }

    public void selectPdfFileType() {

        wait.until(ExpectedConditions.visibilityOf(fileTypeDropdown));

        Select select = new Select(fileTypeDropdown);
        select.selectByVisibleText("PDF (.pdf)");
    }

    public void clickSaveButton() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        );

        button.click();
    }

    public void verifyExcelSelectedByDefault() {

        wait.until(ExpectedConditions.visibilityOf(exportModal));
        wait.until(ExpectedConditions.visibilityOf(fileTypeDropdown));

        Select select = new Select(fileTypeDropdown);

        String selectedOption = select.getFirstSelectedOption()
                .getText()
                .trim();

        if (!selectedOption.equals("Excel (.xlsx)")) {
            throw new AssertionError(
                    "Default selected file type is incorrect.\nExpected: Excel (.xlsx)\nActual: "
                            + selectedOption
            );
        }
    }

    public void verifyExportModalDisplayed() {

        if (!exportModal.isDisplayed()) {
            throw new AssertionError("Export Defects modal is not displayed.");
        }

        String actualTitle = exportModalTitle.getText().trim();

        if (!actualTitle.equals("Export Defects")) {
            throw new AssertionError(
                    "Modal title mismatch. Expected: Export Defects, Actual: " + actualTitle
            );
        }
    }

    public void verifyExportAllButtonVisibleAndClickable() {

        WebElement button = wait.until(
                ExpectedConditions.visibilityOf(exportAllButton)
        );

        if (!button.isDisplayed()) {
            throw new AssertionError("Export All button is not visible.");
        }

        if (!button.isEnabled()) {
            throw new AssertionError("Export All button is not clickable.");
        }

        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void verifyFileTypeDropdown() {

        if (!fileTypeDropdown.isDisplayed()) {
            throw new AssertionError("File Type dropdown is not visible.");
        }

        Select select = new Select(fileTypeDropdown);

        List<String> expectedOptions = Arrays.asList(
                "Excel (.xlsx)",
                "CSV (.csv)",
                "PDF (.pdf)"
        );

        List<String> actualOptions = select.getOptions()
                .stream()
                .map(WebElement::getText)
                .toList();

        if (!actualOptions.equals(expectedOptions)) {
            throw new AssertionError(
                    "Dropdown options mismatch.\nExpected: " + expectedOptions +
                            "\nActual: " + actualOptions
            );
        }
    }

    public void verifyCancelAndSaveButtons() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(cancelButton));
        wait.until(ExpectedConditions.visibilityOf(saveButton));

        if (!cancelButton.isDisplayed() || !cancelButton.isEnabled()) {
            throw new AssertionError("Cancel button is not visible or not enabled.");
        }

        if (!saveButton.isDisplayed() || !saveButton.isEnabled()) {
            throw new AssertionError("Save button is not visible or not enabled.");
        }
    }
    public void verifyFileTypeDropdownContainsAllFormatsforExportAll() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(exportAllButton));
        exportAllButton.click();
        wait.until(ExpectedConditions.visibilityOf(exportModal));
        wait.until(ExpectedConditions.visibilityOf(fileTypeDropdown));

        fileTypeDropdown.click();

        Select select = new Select(fileTypeDropdown);

        List<String> expectedOptions = Arrays.asList(
                "Excel (.xlsx)",
                "CSV (.csv)",
                "PDF (.pdf)"
        );

        List<String> actualOptions = select.getOptions()
                .stream()
                .map(option -> option.getText().trim())
                .toList();
        if (!actualOptions.containsAll(expectedOptions)
                || actualOptions.size() != expectedOptions.size()) {

            throw new AssertionError(
                    "File Type dropdown options mismatch.\nExpected: "
                            + expectedOptions + "\nActual: " + actualOptions
            );
        }

        System.out.println("File Type dropdown contains all expected formats.");
    }

    public void verifyFileTypeDropdownContainsAllFormats() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(exportButton));
        exportButton.click();
        wait.until(ExpectedConditions.visibilityOf(exportModal));
        wait.until(ExpectedConditions.visibilityOf(fileTypeDropdown));

        fileTypeDropdown.click();

        Select select = new Select(fileTypeDropdown);

        List<String> expectedOptions = Arrays.asList(
                "Excel (.xlsx)",
                "CSV (.csv)",
                "PDF (.pdf)"
        );

        List<String> actualOptions = select.getOptions()
                .stream()
                .map(option -> option.getText().trim())
                .toList();
        if (!actualOptions.containsAll(expectedOptions)
                || actualOptions.size() != expectedOptions.size()) {

            throw new AssertionError(
                    "File Type dropdown options mismatch.\nExpected: "
                            + expectedOptions + "\nActual: " + actualOptions
            );
        }

        System.out.println("File Type dropdown contains all expected formats.");
    }


    public boolean isFileDownloaded(int timeoutSeconds) {

        String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
        File dir = new File(downloadPath);
        System.out.println("file :"+dir);

        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);

        while (System.currentTimeMillis() < endTime) {

            File[] files = dir.listFiles();

            if (files != null) {
                for (File file : files) {
                    // Ignore temporary downloading file
                    if (!file.getName().endsWith(".crdownload")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
