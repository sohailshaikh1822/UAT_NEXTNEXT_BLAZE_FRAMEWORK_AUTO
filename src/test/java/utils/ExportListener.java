package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testBase.BaseClass;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExportListener extends BaseClass {


    private WebDriver driver;
    private WebDriverWait wait;

    public ExportListener(WebDriver driver) {
        // super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30-second timeout

    }

    //Xpath
    @FindBy(xpath = "//select[@id='exportFileType']")
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
        if (fileTypeDropdown == null) {
            throw new RuntimeException("fileTypeDropdown is not initialized. Check locator or PageFactory.");
        }

        wait.until(ExpectedConditions.visibilityOf(fileTypeDropdown));
        new Select(fileTypeDropdown).selectByVisibleText("Excel (.xlsx)");
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
                ExpectedConditions.elementToBeClickable(cancelButton)
        );

        button.click();
    }
    public void clickCancelButton() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(cancelButton)
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


    public static String getLatestFileFromDir(String dirPath) {

        File dir = new File(dirPath);
        File[] files = dir.listFiles();

        File lastModifiedFile = files[0];

        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }

        return lastModifiedFile.getAbsolutePath();
    }

    public  void verifyExcelColumns(String filePath, List<String> expectedColumns) {

        try {

            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            Row headerRow = sheet.getRow(0);

            List<String> actualColumns = new ArrayList<>();

            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                actualColumns.add(headerRow.getCell(i).getStringCellValue().trim());
            }

            Assert.assertEquals(actualColumns, expectedColumns, "Excel columns mismatch!");
            System.out.println(actualColumns);

            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to verify Excel columns: " + e.getMessage());
        }
    }

    public void pressSaveButton() throws Exception {
        Robot robot = new Robot();
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}


