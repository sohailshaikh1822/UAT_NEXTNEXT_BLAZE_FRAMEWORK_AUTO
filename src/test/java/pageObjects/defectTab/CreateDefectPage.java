package pageObjects.defectTab;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import pageObjects.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateDefectPage extends BasePage {

    public CreateDefectPage(WebDriver driver) {
        super(driver);
    }

    // ======================================================
    // LOCATORS
    // ======================================================

    @FindBy(id = "createDefect")
    WebElement buttonSave;

    @FindBy(id = "closeButton")
    WebElement buttonClose;

    @FindBy(id = "DefSummary")
    WebElement textAreaSummary;

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

    @FindBy(xpath = "//div[@id='notification']")
    WebElement successNotification;

    // ACTION OBJECTS

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    Actions actions = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // METHODS

    // ---------- Summary Input ----------
    public void enterSummary(String summary) {
        wait.until(ExpectedConditions.visibilityOf(textAreaSummary)).clear();
        textAreaSummary.sendKeys(summary);
    }

    public String getSummaryText() {
        return textAreaSummary.getAttribute("value");
    }

    public boolean isSummaryMandatoryIndicatorVisible() {
        try {
            return driver.findElement(By.xpath("//p[contains(.,'Summary')]//span[contains(text(), '*')]"))
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ---------- Dropdown Helpers ----------
    private void selectDropdownValue(WebElement dropdown, String visibleText) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(dropdown)));
        select.selectByVisibleText(visibleText);
    }

    // ---------- Dropdown Selections ----------
    public void selectAffectedRelease(String releaseName) {
        selectDropdownValue(dropdownAffectedRelease, releaseName);
    }

    public void selectModule(String moduleName) {
        selectDropdownValue(dropdownModule, moduleName);
    }

    public void selectTargetRelease(String releaseName) {
        selectDropdownValue(dropdownTargetRelease, releaseName);
    }

    public void selectSeverity(String severity) {
        selectDropdownValue(dropdownSeverity, severity);
    }

    public void selectReason(String reason) {
        selectDropdownValue(dropdownReason, reason);
    }

    // ---------- Click Buttons ----------
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSave)).click();
    }

    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonClose)).click();
    }

    // ---------- Status Checks ----------
    public boolean isSaveButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(buttonSave));
            return buttonSave.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isCloseButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(buttonClose));
            return buttonClose.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ---------- Success Notification ----------
    public boolean waitForSuccessMessage(String expectedMessage) {
        try {
            WebElement notif = wait.until(ExpectedConditions.visibilityOf(successNotification));
            return notif.getText().trim().equalsIgnoreCase(expectedMessage);
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ---------- Utility: Get All Modules from Dropdown ----------
    public List<String> getAllModuleOptions() {
        List<String> modules = new ArrayList<>();

        Select select = new Select(dropdownModule);
        for (WebElement option : select.getOptions()) {
            modules.add(option.getText().trim());
        }

        return modules;
    }

    // ---------- Utility: Scroll to Element ----------
    public void scrollTo(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
