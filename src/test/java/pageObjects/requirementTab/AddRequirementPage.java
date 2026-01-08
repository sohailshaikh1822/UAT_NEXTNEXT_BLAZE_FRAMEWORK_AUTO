package pageObjects.requirementTab;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.time.Duration;

public class AddRequirementPage extends BasePage {
    public AddRequirementPage(WebDriver driver) {
        super(driver);
    }
    // locators

    @FindBy(xpath = "//div[@class='testcase-text-3']")
    WebElement getTextRequirementId;

    @FindBy(xpath = "(//input[@id='testCaseName'])[1]")
    WebElement textRequirementId;

    @FindBy(xpath = "//div[@class='testcase-prototype']")
    WebElement textRequirementDescriptionBeforeClick;

    @FindBy(xpath = "//div[@class='rte-editor ql-container ql-snow']/div[@contenteditable='true']")
    WebElement textRequirementDescriptionAfterClick;

    @FindBy(xpath = "//div[@class='ql-editor']")
    WebElement textRequirementDescriptionAfterClickWhenItIsNotBlank;

    @FindBy(xpath = "//button[@id='saveButton']")
    WebElement buttonSave;

    @FindBy(xpath = "//button[@id='createRequirementButton']")
    WebElement addRequirementBtn;

    @FindBy(xpath = "//select[option[contains(text(), 'Select Priority')]]")
    WebElement dropDownPriority;

    @FindBy(xpath = "//select[option[contains(text(), 'Select Status')]]")
    WebElement dropDownStatus;

    @FindBy(xpath = "//select[option[contains(text(), 'Select Type')]]")
    WebElement dropDownType;

    @FindBy(xpath = "//button[@id='closeButton']")
    WebElement buttonClose;

    @FindBy(xpath = "//img[@id='rotatable-image']")
    WebElement requirementExpandCollapseArrow;

    @FindBy(xpath = "//div[contains(text(),'Requirement updated successfully.')]")
    WebElement requirementUpdatedSuccessMessage;

    @FindBy(id = "notification")
    WebElement errorNotification;

    @FindBy(xpath = "(//div[normalize-space()='YES'])[1]")
    WebElement clickYes;

    @FindBy(xpath = "(//div[normalize-space()='NO'])[1]")
    WebElement clickNo;


    // actions

    public void ClickYesPopup()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(clickYes)).click();
    }

    public void ClickNoPopup()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(clickNo)).click();
    }

    public void setRequirementId(String id)
    {
        textRequirementId.clear();
        textRequirementId.sendKeys(id);

    }

    public void setDescription(String description) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(textRequirementDescriptionBeforeClick));
        textRequirementDescriptionBeforeClick.click();
        //  WaitUtils.waitFor1000Milliseconds();
        wait.until(ExpectedConditions.elementToBeClickable(textRequirementDescriptionAfterClick));
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '';",
                textRequirementDescriptionAfterClick);
        textRequirementDescriptionAfterClick.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        textRequirementDescriptionAfterClick.sendKeys(Keys.BACK_SPACE);
        textRequirementDescriptionAfterClick.clear();
        textRequirementDescriptionAfterClick.sendKeys(description);
    }

    public void clickAddRequirementBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        addRequirementBtn.click();
    }

    public void selectPriority(String priority) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownPriority));
        Select s = new Select(dropDownPriority);
        s.selectByVisibleText(priority);
    }

    public void selectStatus(String status) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownStatus));
        Select s = new Select(dropDownStatus);
        s.selectByVisibleText(status);
    }

    public void selectType(String type) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownType));
        Select s = new Select(dropDownType);
        s.selectByVisibleText(type);
    }

    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSave));
        buttonSave.click();
    }

    public void clickClose() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonClose));
        buttonClose.click();
    }

    public void requirementDetailsVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(requirementExpandCollapseArrow));
        requirementExpandCollapseArrow.click();
    }

    public String getRequirementIdName() {
        return getTextRequirementId.getText();
    }

    public boolean isModulePageReopened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(addRequirementBtn));
            return addRequirementBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getRequirementUpdatedSuccessMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(requirementUpdatedSuccessMessage));
        return requirementUpdatedSuccessMessage.getText();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(errorNotification));
        return errorNotification.getText().trim();
    }

    public String getRequirementDescription() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement editableField = wait.until(ExpectedConditions.visibilityOf(textRequirementDescriptionBeforeClick));
        return editableField.getText();
    }
    public void clearRequirementName()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("testCaseName"))
        );
        nameInput.clear();
    }

    public void clickOnRequirementIdLabel() {
        textRequirementId.click();
    }

    public String getRqId() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By rqIdLocator = By.xpath("//div[contains(@class,'testcase-text-3')]");

        WebElement rqIdElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(rqIdLocator)
        );

        return rqIdElement.getText().trim();
    }


    public String getModuleId() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By moduleIdLocator = By.xpath("//div[contains(@class,'text-3')]");

        WebElement moduleIdElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(moduleIdLocator)
        );

        String moduleId = moduleIdElement.getText().trim();

        if (moduleId.isEmpty()) {
            throw new AssertionError("Module ID is empty");
        }

        System.out.println("Captured Module ID: " + moduleId);
        return moduleId;
    }


}
