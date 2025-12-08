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
    @FindBy(xpath = "//button[contains(., 'SAVE')]")
    WebElement buttonSave;

    @FindBy(id = "updateDefect")
    WebElement buttonSaveForUpdateDefect;

    @FindBy(id = "createDefect")
    WebElement buttonSavefornewDefect;

    @FindBy(xpath = "//button[@id='closeButton']")
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

    @FindBy(xpath = "//select[@id='AssignToDropdown']")
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

    @FindBy(xpath = "(//div[normalize-space()='YES'])[1]")
    WebElement popupYes;

    // Popup Buttons
    @FindBy(id = "confirmBtn")
    WebElement popupYesButton;

    @FindBy(id = "cancelBtn")
    WebElement popupNoButton;

    @FindBy(xpath = "//div[contains(@class,'unsaved-button-container')]")
    WebElement unsavedPopupContainer;

    // ACTION OBJECTS
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    Actions actions = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // METHODS

    // ------------------PAGE SCROLLING METHODS------------------
    public void scrollUp() {
        By containerLocator = By.xpath("//div[@class='test-execution-frame-2']");
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(containerLocator));
        js.executeScript("arguments[0].scrollTop = 0;", container);
    }

    // ---------- Summary ----------
    public void enterSummary(String summary) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(textAreaSummary));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
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

    public boolean textAreaSummaryIsDisplayed() {
        return textAreaSummary.isDisplayed();
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

    public boolean textAreaDescriptionIsDisplayed() {
        return textAreaDescription.isDisplayed();
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

    public void selectSeverity(String value) {
        selectDropdown(dropdownSeverity, value);
    }

    public void clickAffectedReleaseDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownAffectedRelease)).click();
    }

    public boolean dropdownAffectedReleaseIsDisplayed() {
        return dropdownAffectedRelease.isDisplayed();
    }

    public boolean dropdownModuleIsDisplayed() {
        return dropdownModule.isDisplayed();
    }

    public void selectTargetRelease(String value) {
        selectDropdown(dropdownTargetRelease, value);
    }

    public boolean dropdownTargetReleaseIsDisplayed() {
        return dropdownTargetRelease.isDisplayed();
    }

    public boolean dropdownSeverityIsDisplayed() {
        return dropdownSeverity.isDisplayed();
    }

    public void selectReason(String value) {
        selectDropdown(dropdownReason, value);
    }

    public boolean dropdownReasonIsDisplayed() {
        return dropdownReason.isDisplayed();
    }

    public void selectStatus(String value) {
        selectDropdown(dropdownStatus, value);
    }

    public boolean dropdownStatusIsDisplayed() {
        return dropdownStatus.isDisplayed();
    }

    public void selectFixedRelease(String value) {
        selectDropdown(dropdownFixedRelease, value);
    }

    public boolean dropdownFixedReleaseIsDisplayed() {
        return dropdownFixedRelease.isDisplayed();
    }

    public void selectCategory(String value) {
        selectDropdown(dropdownCategory, value);
    }

    public boolean dropdownCategoryIsDisplayed() {
        return dropdownCategory.isDisplayed();
    }

    public void selectPriority(String value) {
        selectDropdown(dropdownPriority, value);
    }

    public boolean dropdownPriorityIsDisplayed() {
        return dropdownPriority.isDisplayed();
    }

    public void selectType(String value) {
        selectDropdown(dropdownType, value);
    }

    public boolean dropdownTypeIsDisplayed() {
        return dropdownType.isDisplayed();
    }

    public void selectAssignTo(String value) {
        selectDropdown(dropdownAssignTo, value);
    }

    public boolean dropdownAssignToIsDisplayed() {
        return dropdownAssignTo.isDisplayed();
    }

    // ---------- Button Clicks ----------
    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSave)).click();
    }

    public void selectYes() {
        wait.until(ExpectedConditions.elementToBeClickable(popupYes)).click();
    }

    public void clickSaveforNewDefect() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSavefornewDefect)).click();
    }

    public boolean buttonSaveIsDisplayed() {
        return buttonSave.isDisplayed();
    }

    public void clickClose() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonClose)).click();
    }

    public boolean buttonCloseIsDisplayed() {
        return buttonClose.isDisplayed();
    }

    // ---------- Attachment Upload ----------
    public void uploadFile(String filePath) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonBrowseFile)).click();
        inputUploadFile.sendKeys(filePath);
    }

    public boolean buttonBrowseFileIsDisplayed() {
        return buttonBrowseFile.isDisplayed();
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

    public boolean isSummaryBlankErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(msgSummaryCannotBeBlank))
                .isDisplayed();
    }

    public boolean isMandatoryStarVisible(String fieldName) {
        String dynamicXpath = "//span[normalize-space(text())='" + fieldName + "']"
                + "/following-sibling::span[normalize-space(text())='*']";

        WebElement mandatoryStar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

        return mandatoryStar.isDisplayed();
    }

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

    public String getSelectedAffectedRelease() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownAffectedRelease)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedModule() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownModule)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedTargetRelease() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownTargetRelease)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedSeverity() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownSeverity)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedReason() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownReason)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedStatus() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownStatus)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedFixedRelease() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownFixedRelease)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedCategory() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownCategory)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedPriority() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownPriority)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedAssignTo() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownAssignTo)));
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getSelectedType() {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dropdownType)));
        return select.getFirstSelectedOption().getText().trim();
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

    public void clickTargetReleaseDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownTargetRelease)).click();
    }

    public boolean verifySummaryAndDescription(String expectedSummary, String expectedDescription)
            throws InterruptedException {
        Thread.sleep(1500);

        String actualSummary = getSummary().trim();
        String actualDescription = getDescription().trim();

        System.out.println("DEBUG SUMMARY -> actual: [" + actualSummary + "] | expected: [" + expectedSummary + "]");
        System.out.println(
                "DEBUG DESCRIPTION -> actual: [" + actualDescription + "] | expected: [" + expectedDescription + "]");

        return actualSummary.equals(expectedSummary) && actualDescription.equals(expectedDescription);
    }

    public static By textAreaDescriptionLocator() {
        return By.xpath("//textarea[@rows='8']");
    }

    public String getDescription() {
        return textAreaDescription.getAttribute("value");
    }

    public WebElement getDescriptionField() {
        return textAreaDescription;
    }

    public String getSuccessNotificationText() throws InterruptedException {
        return successNotification.getText().trim();
    }

    public String getNotificationText() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(@id,'notification') or contains(@class,'notification')]")));
            return element.getText().trim();
        } catch (Exception e) {
            System.out.println("Notification NOT found.");
            return "";
        }
    }

    public boolean isSaveButtonVisible() throws InterruptedException {
        Thread.sleep(1500);
        try {
            return buttonSave.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSaveButtonClickable() throws InterruptedException {
        Thread.sleep(1500);
        try {
            return buttonSave.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSaveIfVisibleAndClickable() throws InterruptedException {
        Thread.sleep(1500);

        if (buttonSave.isDisplayed() && buttonSave.isEnabled()) {
            buttonSave.click();
        } else {
            throw new RuntimeException("Save button is not visible or not clickable.");
        }
    }

    public void scrollToSaveButton() {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", buttonSavefornewDefect);
    }

    public String getRawSummary() {
        return textAreaSummary.getAttribute("value");
    }

    public String getSuccessNotificationMessage() {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOf(successNotification));
        return element.getText().trim();
    }

    public void verifySuccessNotificationMessage(String expectedMessage) {
        String actualMessage = getSuccessNotificationMessage();
        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "FAILED: Success notification message mismatch.");
    }

    public boolean isUnsavedPopupVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(unsavedPopupContainer));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPopupYes() {
        wait.until(ExpectedConditions.elementToBeClickable(popupYesButton)).click();
    }

    public void clickPopupNo() {
        wait.until(ExpectedConditions.elementToBeClickable(popupNoButton)).click();
    }

    public void clearDescriptionField() {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(textAreaDescription));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            element.clear();
        } catch (Exception e) {
            js.executeScript("arguments[0].value='';", element);
        }
        wait.until(driver -> element.getAttribute("value").isEmpty());
        Assert.assertEquals(element.getAttribute("value"), "", "FAILED: Description field was not cleared.");
    }
}
