package pageObjects.defectTab;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import pageObjects.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateDefectPage extends BasePage {

    public CreateDefectPage(WebDriver driver) {
        super(driver);
    }

    // LOCATORS

    // Buttons
    @FindBy(id = "createDefect")
    WebElement buttonSave;

    @FindBy(id = "closeButton")
    WebElement buttonClose;

    // Summary
    @FindBy(id = "DefSummary")
    WebElement textAreaSummary;

    // Dropdowns
    @FindBy(id = "AffectedReleaseDropdown")
    WebElement dropdownAffectedRelease;

    @FindBy(id = "ModuleDropdown")
    WebElement dropdownModule;

    @FindBy(id = "TargetReleaseBuildDropdown")
    WebElement dropdownTargetRelease;

    @FindBy(id = "SeverityDropdown")
    WebElement dropdownSeverity;

    @FindBy(id = "ReasonDropdown")
    WebElement dropdownReason;

    @FindBy(id = "StatusDropdown")
    WebElement dropdownStatus;

    @FindBy(id = "FixedReleaseBuildDropdown")
    WebElement dropdownFixedRelease;

    @FindBy(id = "CategoryDropdown")
    WebElement dropdownCategory;

    @FindBy(id = "PriorityDropdown")
    WebElement dropdownPriority;

    @FindBy(id = "TypeDropdown")
    WebElement dropdownType;

    @FindBy(id = "AssignToDropdown")
    WebElement dropdownAssignTo;

    // Description
    @FindBy(xpath = "//textarea[@rows='8']")
    WebElement textAreaDescription;

    // Attachments
    @FindBy(id = "browse-button")
    WebElement buttonBrowseFile;

    @FindBy(id = "uploadfile")
    WebElement inputUploadFile;

    @FindBy(css = "#file-info ul")
    WebElement fileListContainer;

    // Toast / Notification
    @FindBy(xpath = "//div[@id='notification']")
    WebElement successNotification;


    @FindBy(xpath = "//*[contains(text(),'Summary cannot be blank')]")
    WebElement msgSummaryCannotBeBlank;

    // ACTION OBJECTS

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    Actions actions = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // METHODS

    // ---------- Summary ----------
    public void enterSummary(String summary) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(textAreaSummary));
        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].focus();", element);
        element.clear();
        for (char c : summary.toCharArray()) {
            element.sendKeys(String.valueOf(c));
        }
        js.executeScript("arguments[0].blur();", element);
        wait.until(driver -> element.getAttribute("value").equals(summary));
        Assert.assertEquals(element.getAttribute("value"), summary, "FAILED: Summary text did not set properly.");
    }


    

    public String getSummary() {
        return textAreaSummary.getAttribute("value");
    }

    // ---------- Description ----------
    public void enterDescription(String description) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(textAreaDescription));
        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].focus();", element);
        element.clear();
        for (char c : description.toCharArray()) {
            element.sendKeys(String.valueOf(c));
        }
        js.executeScript("arguments[0].blur();", element);
        wait.until(driver -> element.getAttribute("value").equals(description));
        Assert.assertEquals(element.getAttribute("value"), description,
                "FAILED: Description text did not set properly.");
    }

    public String getDescription() {
        return textAreaDescription.getAttribute("value");
    }

    // ---------- Generic Dropdown Selector ----------
    private void selectDropdown(WebElement dropdown, String visibleText) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(dropdown)));
        select.selectByVisibleText(visibleText);
    }

    // ---------- Dropdown Methods ----------

    public void selectAffectedRelease(String value) {
        selectDropdown(dropdownAffectedRelease, value);
    }

    public void selectModule(String value) {
        selectDropdown(dropdownModule, value);
    }

    public void selectTargetRelease(String value) {
        selectDropdown(dropdownTargetRelease, value);
    }

    public void selectSeverity(String value) {
        selectDropdown(dropdownSeverity, value);
    }

    public void clickSeverityDropdown() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownSeverity));
        dropdown.click();

        // Print all values
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            System.out.println(" -> " + option.getText());
        }
    }
    public void clickReasonDropdown() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownReason));
        dropdown.click();

        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            System.out.println(" -> " + option.getText());
        }
    }

    public void clickTypeDropdown() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownType));
        dropdown.click();

        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        System.out.println("Type dropdown values:");
        for (WebElement option : options) {
            System.out.println(" -> " + option.getText());
        }
    }
    public void clickModuleDropdown() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownModule));
        dropdown.click();

        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            System.out.println(" -> " + option.getText());
        }
    }



    public void clickAffectedReleaseDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownAffectedRelease)).click();
    }


    public void clickTargetReleaseDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownTargetRelease)).click();
    }

    public void clickCategoryDropdown() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownCategory));
        dropdown.click();

        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            System.out.println(" -> " + option.getText());
        }
    }
    public void clickStatusDropdown() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownStatus));
        dropdown.click();

        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            System.out.println(" -> " + option.getText());
        }
    }
    public void clickPriorityDropdown() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownPriority));
        dropdown.click();

        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            System.out.println(" -> " + option.getText());
        }
    }


    public void selectReason(String value) {
        selectDropdown(dropdownReason, value);
    }

    public void selectStatus(String value) {
        selectDropdown(dropdownStatus, value);
    }

    public void selectFixedRelease(String value) {
        selectDropdown(dropdownFixedRelease, value);
    }

    public void selectCategory(String value) {
        selectDropdown(dropdownCategory, value);
    }

    public void selectPriority(String value) {
        selectDropdown(dropdownPriority, value);
    }

    public void selectType(String value) {
        selectDropdown(dropdownType, value);
    }

    public void selectAssignTo(String value) {
        selectDropdown(dropdownAssignTo, value);
    }

    // ---------- Button Clicks ----------
    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSave)).click();
    }

    public void clickClose() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonClose)).click();
    }

    // ---------- Attachment Upload ----------
    public void uploadFile(String filePath) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonBrowseFile)).click();
        inputUploadFile.sendKeys(filePath);
    }

    public boolean isFileUploaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(fileListContainer));
            return fileListContainer.findElements(By.tagName("li")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ---------- Toast / Notification ----------
    public boolean verifySuccessMessage(String expected) {
        try {
            WebElement notify = wait.until(ExpectedConditions.visibilityOf(successNotification));
            return notify.getText().trim().equalsIgnoreCase(expected);
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ---------- Utility Dropdown Options ----------
    public List<String> getDropdownOptions(WebElement dropdown) {
        List<String> options = new ArrayList<>();
        Select select = new Select(dropdown);

        for (WebElement element : select.getOptions()) {
            options.add(element.getText().trim());
        }
        return options;
    }

    // Check if Status dropdown is at default placeholder
    public void verifyStatusIsDefault() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownStatus)));
        String selectedText = select.getFirstSelectedOption().getText().trim();

        Assert.assertTrue(
                selectedText.equalsIgnoreCase("-- Select Status --"),
                "FAILED: Status dropdown is NOT at default value '-- Select Status --'. Actual value: " + selectedText);
    }

    public void verifyStatusErrorMessage() {
        try {
            WebElement msgElement = wait.until(ExpectedConditions.visibilityOf(successNotification));
            String actualMessage = msgElement.getText().trim();

            String expectedMessage = "Error: Please select a valid Status.";

            Assert.assertEquals(
                    actualMessage,
                    expectedMessage,
                    "FAILED: Incorrect validation message. Expected: '" + expectedMessage + "' but found: '"
                            + actualMessage + "'");

        } catch (TimeoutException e) {
            Assert.fail("FAILED: Status error notification did NOT appear after clicking Save.");
        }
    }

    public boolean isSummaryBlankErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(msgSummaryCannotBeBlank))
                .isDisplayed();
    }

    public boolean isMandatoryStarVisible(String fieldName) {
        String dynamicXpath = "//span[normalize-space(text())='" + fieldName + "']"
                + "/following-sibling::span[normalize-space(text())='*']";

        WebElement mandatoryStar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath))
        );

        return mandatoryStar.isDisplayed();
    }



}
