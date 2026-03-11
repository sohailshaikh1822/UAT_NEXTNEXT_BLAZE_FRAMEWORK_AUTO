package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.BaseClass;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ExportListener extends BaseClass {


    private WebDriver driver;
    private WebDriverWait wait;

    public ExportListener(WebDriver driver) {
        // super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30-second timeout

    }

    //Xpath
    @FindBy(id = "exportFileType")
    WebElement fileTypeDropdown;

    @FindBy(xpath = "//button[@class='export-save-button ']")
    WebElement saveExportBtn;

    @FindBy(xpath = "//div[contains(@class,'test-log-history-export-modal')]")
    WebElement exportModal;


    @FindBy(xpath = "//div[contains(@class,'test-log-history-export-modal-footer')]//button[.//div[normalize-space()='CANCEL']]")
    WebElement cancelButton;

    @FindBy(xpath = "//button[contains(@class,'export-save-button')]")
    WebElement saveButton;

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
                ExpectedConditions.elementToBeClickable(saveExportBtn)
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

}
