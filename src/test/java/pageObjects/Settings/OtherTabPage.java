package pageObjects.Settings;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;
import java.time.Duration;

public class OtherTabPage extends BasePage {

    public OtherTabPage(WebDriver driver) {
        super(driver);
    }

    // ----------------------------- Diff Tab locators
    // ---------------------------------------------
    @FindBy(xpath = "//div[normalize-space()='Global Field Setting']")
    WebElement globalFieldSetting;

    @FindBy(xpath = "//div[@class='releases '][normalize-space()='Module']")
    WebElement module;

    @FindBy(xpath = "//i[@class='fas fa-rocket']")
    WebElement Release;

    @FindBy(xpath = "//div[normalize-space()='Requirement']")
    WebElement requirement;

    @FindBy(xpath = "//div[contains(@class,'releases')][normalize-space()='Test Case']")
    WebElement testCase;

    @FindBy(xpath = "//div[contains(@class,'releases')][normalize-space()='Test Step']")
    WebElement testStep;

    @FindBy(xpath = "//div[contains(@class,'releases')][normalize-space()='Test Cycle']")
    WebElement testCycle;

    @FindBy(xpath = "//div[contains(@class,'releases')][normalize-space()='Test Suite']")
    WebElement testSuite;

    @FindBy(xpath = "//div[contains(@class,'releases')][normalize-space()='Test Runs']")
    WebElement testRuns;

    @FindBy(xpath = "//div[normalize-space()='Defect']")
    WebElement defect;

    @FindBy(xpath = "(//button[@id='confirmBtn'])[1]")
    WebElement confirmationOnDefaultFieldDelete;

    // ----------------------------- Diff Tab actions
    // ---------------------------------------------
    public void clickGlobalFieldSetting() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(globalFieldSetting)).click();
    }

    public void clickYesDefaultValueDelete() {
        confirmationOnDefaultFieldDelete.click();
    }

    public void clickModule() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(module)).click();
    }

    public void clickRequirement() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(requirement)).click();
    }

    public void clickOnRelease() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement releaseElement = wait.until(ExpectedConditions.elementToBeClickable(Release));
        releaseElement.click();
    }

    public void clickTestCase() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(testCase)).click();
    }

    public void clickTestStep() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(testStep)).click();
    }

    public void clickTestCycle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(testCycle)).click();
    }

    public void clickTestSuite() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(testSuite)).click();
    }

    public void clickTestRuns() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(testRuns)).click();
    }

    public void clickDefect() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(defect)).click();
    }

    // ----------------------------- Common tab locators
    // ------------------------------------------
    @FindBy(xpath = "//button[@id='createRequirementButton']")
    WebElement addCustomField;

    @FindBy(xpath = "//span[normalize-space()='CREATE FIELD']")
    WebElement createfieldbutton;

    @FindBy(xpath = "//div[@class='bulk-actions-container']//button[1]")
    WebElement saveChangesButton;

    @FindBy(xpath = "//span[normalize-space()='SELECT ALL']")
    WebElement selectAllButton;

    @FindBy(xpath = "//span[normalize-space()='CLEAR ALL']")
    WebElement clearAllButton;

    @FindBy(xpath = "//span[normalize-space()='RESET']")
    WebElement resetButton;

    @FindBy(xpath = "//button[normalize-space()='ADD DEFAULT VALUE']")
    WebElement editDefaultValueButton;

    @FindBy(xpath = "//button[normalize-space()='CLOSE']")
    WebElement editCloseButton;

    @FindBy(xpath = "//div[@id='addFieldValueModal']//button[1]")
    WebElement DefaultSaveButton;

    @FindBy(xpath = "//input[@placeholder='Enter default value']")
    WebElement editFillDefaultValue;

    @FindBy(xpath = "//div[@title='Remove default value']//i[@class='fa-solid fa-trash']")
    WebElement editDefaultDeleteIcon;

    @FindBy(xpath = "//button[@id='confirmBtn']")
    WebElement confirmYesButton;

    // ----------------------------- Common tab actions
    // -------------------------------------------
    public void clickOnSaveChanges() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(saveChangesButton)).click();
    }

    public WebElement checkboxForRow(String rowName) {
        return driver.findElement(By.xpath(
                "//p[normalize-space()='" + rowName + "']/../../..//input[@type='checkbox']"));
    }

    public WebElement editButtonForRow(String rowName) {
        String xpath = "//p[normalize-space(text())='" + rowName + "']/../../..//i[@class='fa-solid fa-pencil']";
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement deleteButtonForRow(String rowName) {
        String xpath = "//p[normalize-space(text())='" + rowName + "']/../../..//i[@class='fa-solid fa-trash']";
        return driver.findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "(//button[@type='button'])[3]")
    WebElement SaveChangesButtonInsideActionEdit;

    @FindBy(xpath = "(//button[normalize-space()='CLOSE'])[1]")
    WebElement CloseActionEditButton;

    // locators for Add custom Fields

    @FindBy(xpath = "(//input[@placeholder='Enter field name (e.g., Priority, Category)'])[1]")
    WebElement FieldNAme;

    @FindBy(xpath = "(//select[@class='assignToDropdown testcase-text-wrapper-15 testcase-select'])[1]")
    WebElement DataType;

    @FindBy(xpath = "(//button[@type='button'])[1]")
    WebElement CreateField;

    @FindBy(xpath = "(//button[normalize-space()='CANCEL'])[1]")
    WebElement Cancelbutton;

    // Locators for add field value
    @FindBy(xpath = "(//button[normalize-space()='ADD FIELD VALUE'])[1]")
    WebElement AddFieldValueButton;

    @FindBy(xpath = "(//input[@placeholder='Enter option value'])[1]")
    WebElement EnterFieldValue;

    @FindBy(xpath = "(//input[@title='Set as default'])[1]")
    WebElement DefaultButton;

    @FindBy(xpath = "(//i[@class='fa-solid fa-trash'])[6]")
    WebElement deleteButton;

    // Actions

    public void clickOnAddCustomField() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(addCustomField)).click();
    }

    public void clickOnSelectAll() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(selectAllButton)).click();
    }

    public void clickEdit(String rowName) {
        editCloseButton.click();
    }

    public void clickOnClearAll() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(clearAllButton)).click();
    }

    public boolean SelectAllAndClearAllButtonsClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement selectAllBtn = wait.until(ExpectedConditions.elementToBeClickable(selectAllButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
                    selectAllBtn);
            new Actions(driver).moveToElement(selectAllBtn).perform();

            WebElement clearAllBtn = wait.until(ExpectedConditions.elementToBeClickable(clearAllButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", clearAllBtn);
            new Actions(driver).moveToElement(clearAllBtn).perform();

            return selectAllBtn.isDisplayed() && selectAllBtn.isEnabled()
                    && clearAllBtn.isDisplayed() && clearAllBtn.isEnabled();

        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnReset() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }

    public void clickOnCheckbox(String rowName) {
        WebElement checkbox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(checkboxForRow(rowName)));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    // public void clickOnEdit(String rowName) {
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // WebElement editBtn =
    // wait.until(ExpectedConditions.visibilityOf(editButtonForRow(rowName)));
    //
    // ((JavascriptExecutor)
    // driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
    // editBtn);
    // Actions actions = new Actions(driver);
    // actions.moveToElement(editBtn).perform();
    //
    // try {
    // wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
    // } catch (Exception firstAttempt) {
    // try {
    // Thread.sleep(1000);
    // ((JavascriptExecutor)
    // driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
    // editBtn);
    // actions.moveToElement(editBtn).perform();
    // wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
    // } catch (Exception retryAttempt) {
    // ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
    // editBtn);
    // }
    // }
    // }

    public void clickOnEdit(String rowName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement editBtn = wait.until(ExpectedConditions.visibilityOf(editButtonForRow(rowName)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editBtn);
            Thread.sleep(500);
            boolean inView = (Boolean) js.executeScript(
                    "var rect = arguments[0].getBoundingClientRect();" +
                            "return (rect.top >= 0 && rect.left >= 0 && " +
                            "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                            "rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
                    editBtn);

            if (inView) {
                actions.moveToElement(editBtn).perform();
            }
            wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();

        } catch (Exception e1) {
            try {
                Thread.sleep(1000);
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editBtn);
                Thread.sleep(500);
                wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
            } catch (Exception e2) {
                js.executeScript("arguments[0].click();", editBtn);
            }
        }
    }

    public void clickOnDelete(String rowName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonForRow(rowName)));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", deleteBtn);
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteBtn).perform();
        try {
            deleteBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
        }
    }

    @FindBy(xpath = "//div[@class='test-execution-label-3']")
    private WebElement deleterowconfirmation;

    public void clickOnDeleteRowConfirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(deleterowconfirmation));
        confirmBtn.click();
    }

    public void clickDefaultAddValue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(editDefaultValueButton));
        WebElement addValueBtn = wait.until(ExpectedConditions.elementToBeClickable(editDefaultValueButton));
        addValueBtn.click();
    }

    public void clickDefaultCloseButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(editCloseButton)).click();
    }

    public void clickDefaultSaveChanges() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(DefaultSaveButton)).click();
    }

    public void clickDefaultSaveButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(DefaultSaveButton)).click();
    }

    public void enterDefaultValue(String value) {
        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(editFillDefaultValue));
        input.clear();
        input.sendKeys(value);
    }

    public void clickDefaultDeleteIcon() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(editDefaultDeleteIcon)).click();
    }

    public void clickConfirmYesButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton));
        confirmYesButton.click();
    }

    // ----------------------------- Add Custom Field locators
    // ------------------------------------
    @FindBy(xpath = "//input[@placeholder='Enter field name (e.g., Priority, Category)']")
    WebElement createCustomFieldName;

    @FindBy(xpath = "//span[normalize-space()='CREATE FIELD']")
    WebElement createCustomFieldButton;

    @FindBy(xpath = "//button[normalize-space()='CANCEL']")
    WebElement createCustomCancelButton;

    @FindBy(xpath = "//select[@class='assignToDropdown testcase-text-wrapper-15 testcase-select']")
    WebElement createCustomDataTypeDropdown;

    // ----------------------------- Add Custom Field actions
    // -------------------------------------
    public void createCustomEnterFieldName(String fieldName) {
        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(createCustomFieldName));
        input.clear();
        input.sendKeys(fieldName);
    }

    public void createCustomSelectDataType(String value) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(createCustomDataTypeDropdown));
        Select dropdown = new Select(createCustomDataTypeDropdown);
        dropdown.selectByVisibleText(value);
    }

    public void clickcreatefieldButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(createfieldbutton)).click();
    }

    public void createCustomClickCancelButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(createCustomCancelButton)).click();
    }

    public boolean isCustomFieldPresent(String fieldName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By fieldLocator = By.xpath("//p[normalize-space()='" + fieldName + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
            return driver.findElements(fieldLocator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTestStepsPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By pageHeaderLocator = By.xpath("//div[contains(@class,'releases') and normalize-space()='Test Step']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
            return driver.findElements(pageHeaderLocator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTestRunsTabVisible(String expectedTabName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement testRunsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'" + expectedTabName + "')]")));
            return testRunsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public void deleteCustomFieldAndVerify(String fieldName) throws InterruptedException {
        clickOnDelete(fieldName);
        Thread.sleep(500);
        clickYesDefaultValueDelete();

        boolean isDeleted = waitUntilCustomFieldIsDeleted(fieldName, 10);
        assert isDeleted : "Custom field was not deleted successfully.";
    }

    private boolean waitUntilCustomFieldIsDeleted(String fieldName, int timeoutSeconds) throws InterruptedException {
        int waited = 0;
        while (waited < timeoutSeconds * 1000) {
            if (!isCustomFieldPresent(fieldName)) {
                return true;
            }
            Thread.sleep(500);
            waited += 500;
        }
        return false;
    }

}
