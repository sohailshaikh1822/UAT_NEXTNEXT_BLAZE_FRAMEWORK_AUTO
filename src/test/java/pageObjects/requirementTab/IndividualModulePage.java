package pageObjects.requirementTab;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;
import java.time.Duration;
import java.util.List;
import utils.WaitUtils;

public class IndividualModulePage extends BasePage {

    public IndividualModulePage(WebDriver driver) {
        super(driver);
    }

    // Locators

    @FindBy(xpath = "//div[@class='text-2']")
    WebElement headingModuleId;

    @FindBy(xpath = "//p[@class='supporting-text']/input")
    WebElement moduleNameInput;

    @FindBy(xpath = "//div[@class='label-3']")
    WebElement buttonAddRequirement;

    @FindBy(xpath = "//input[@class='supporting-text']")
    WebElement inputTitle;

    @FindBy(xpath = "(//input[@class='testcase-select value'])[2]")
    WebElement inputName;

    @FindBy(xpath = "//div[@class='test-execution-label-3' and text()='SAVE']")
    WebElement btnSave;

    @FindBy(xpath = "(//input[@class='testcase-select value'])[1]")
    WebElement inputDescription;

    @FindBy(xpath = "//div[@class='rich-editor-scrollable']")
    WebElement descriptionBeforeClick;

    @FindBy(xpath = "//div[@class='rte-editor ql-container ql-snow']/div[@contenteditable='true']")
    WebElement descriptionAfterClick;

    @FindBy(id = "rotatable-image")
    WebElement toggleSectionIcon;

    @FindBy(xpath = "//div[@class='test-case-pagination-item']")
    WebElement divRequirementPagination;

    @FindBy(xpath = "//img[@alt='Next']")
    WebElement arrowForwardNextPagination;

    @FindBy(xpath = "//img[@alt='Previous']")
    WebElement arrowBackwardPrevious;

    @FindBy(xpath = "//div[@class='wrapper']")
    List<WebElement> rqCountWrapper;

    @FindBy(xpath = "//img[@alt='Last Page']")
    WebElement lastPageArrowBtn;

    @FindBy(xpath = "//img[@alt='First Page']")
    WebElement firstPageArrowBtn;
    @FindBy(xpath = "//p[contains(text(), 'Please enter a module name to proceed.')]")
    WebElement AlertMessageForModuleName;

    @FindBy(xpath = "(//p[@id='actionDialog-message'])[1]")
    WebElement alertMessageWhenTryToDeleteModule;

    @FindBy(xpath = "//button[@id='closeButton']//div[@class='test-execution-clear'][normalize-space()='CLOSE']")
    WebElement closebutton;

    @FindBy(xpath = "//div[@id='notification']")
    WebElement notificationMessage;

    @FindBy(xpath = "//div[@class='rich-editor-scrollable']")
    WebElement textDescriptionBeforeClick;

    @FindBy(xpath = "//div[@class='ql-editor ql-blank']")
    WebElement textDescriptionAfterClick;

    @FindBy(id = "existingTestCasesTable")
    WebElement linkedRequirementTable;
    @FindBy(xpath = "(//div[contains(text(),'CLOSE')])[1]")
    WebElement alertBoxCloseBtnForModule;

    @FindBy(id = "actionDialogtp-message")
    WebElement moduleNameAlertMessage;

    public WebElement linkRequirementIdFromId(String id) {
        return driver.findElement(By.xpath("//div[@class='testlistcell']/a[text()='" + id + "']"));
    }

    public WebElement deleteRequirementIcon(String reqID) {
        return driver.findElement(By.xpath(
                "//a[text()='" + reqID
                        + "']/ancestor::div[@class='testlistrow']//button[@class='deleteRowButton' and @title='Delete Requirement']"));
    }

    @FindBy(xpath = "(//select[@class='testcase-select value'])[1]")
    WebElement priority;

    @FindBy(xpath = "(//select[@class='testcase-select value'])[2]")
    WebElement status;

    @FindBy(xpath = "(//select[@class='testcase-select value'])[3]")
    WebElement type;

    @FindBy(xpath = "//div[@class='requirements testcase-text-6']")
    WebElement noLinkedRequirement;

    @FindBy(xpath = "//span[@class='entry-info']")
    WebElement requirementCountFooter;

    @FindBy(id = "cancelBtnClose")
    WebElement cancelButton;

    @FindBy(xpath = "//i[@class='fa-solid fa-trash' and @title='Delete']")
    private WebElement deleteModuleIcon;

    @FindBy(id = "actionDialog-message")
    private WebElement confirmationMessage;

    @FindBy(id = "confirmBtn")
    private WebElement confirmYesButton;

    @FindBy(id = "cancelBtn")
    private WebElement confirmNoButton;

    @FindBy(xpath = "//div[@id='partialTestCaseContainer']//div[@class='test-execution-label-3'][normalize-space()='YES']")
    WebElement buttonYesConfirmationUnsavedChanges;

    @FindBy(xpath = "//div[@id='partialTestCaseContainer']//div[@class='test-execution-clear'][normalize-space()='NO']")
    WebElement buttonNoConfirmationUnsavedChanges;

    private By actionDialog = By.id("actionDialog");

    private By inputTitleLocator = By.xpath("//input[@class='supporting-text']");

    // Actions

    public void clickAddRequirement() {
        buttonAddRequirement.click();
    }

    public void enterTitle(String title) {
        inputTitle.clear();
        inputTitle.sendKeys(title);
    }

    public String getModuleName() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOf(inputTitle)).getAttribute("value").trim();
    }

    public String getModuleId() {
        return headingModuleId.getText();
    }

    public void enterName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Wait until the description input is clickable
        wait.until(ExpectedConditions.elementToBeClickable(inputTitle));
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "A"));
        inputName.sendKeys(name);
    }

    public void clickSave() {
        btnSave.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement notification = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("notification")));
        } catch (Exception e) {
            WebElement alertMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("actionDialogtp-message")));
        }
    }

    public void enterDescription(String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Wait until the description input is clickable
        wait.until(ExpectedConditions.elementToBeClickable(inputDescription));
        inputDescription.sendKeys(Keys.chord(Keys.CONTROL, "A"));
        inputDescription.sendKeys(Keys.BACK_SPACE);
        inputDescription.sendKeys(description);
    }

    public void setActualDescription(String description) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.elementToBeClickable(descriptionBeforeClick));
        descriptionBeforeClick.click();
         WaitUtils.waitFor1000Milliseconds();
        wait.until(ExpectedConditions.elementToBeClickable(descriptionAfterClick));
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '';", descriptionAfterClick);
        descriptionAfterClick.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        descriptionAfterClick.sendKeys(Keys.BACK_SPACE);
        descriptionAfterClick.clear();
        descriptionAfterClick.sendKeys(description);

    }

    public void clearActualDescription() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.elementToBeClickable(descriptionBeforeClick));
        descriptionBeforeClick.click();
        //  WaitUtils.waitFor1000Milliseconds();
        wait.until(ExpectedConditions.elementToBeClickable(descriptionAfterClick));
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '';", descriptionAfterClick);
        descriptionAfterClick.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        descriptionAfterClick.sendKeys(Keys.BACK_SPACE);
        descriptionAfterClick.clear();
        inputTitle.click();
        Thread.sleep(2000);
    }

    public void clickInputTitle() {
        inputTitle.click();
    }

    public String getActualDescription() {
        return descriptionBeforeClick.getText().trim();
    }

    public void clickToggleSection() {
        toggleSectionIcon.click();
    }

    public void clickRequirement(String reqID) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions
                .elementToBeClickable(linkRequirementIdFromId(reqID)));
        element.click();
    }

    public void clickDeleteRequirement(String reqID) throws InterruptedException {
        Thread.sleep(2000);
        deleteRequirementIcon(reqID).click();
        Thread.sleep(2000);
    }

    public String showPaginationOfRequirement() throws InterruptedException {
        Thread.sleep(2000);
        return divRequirementPagination.getText();
    }

    public String checkIfNextButtonIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(arrowForwardNextPagination));
            String cursorStyle = arrowForwardNextPagination.getCssValue("cursor");

            System.out.println("Cursor style of Next button: " + cursorStyle);

            if ("pointer".equals(cursorStyle)) {
                System.out.println("Next button appears clickable (cursor: pointer).");
            } else {
                System.out.println("Next button appears NOT clickable (cursor: " + cursorStyle + ").");
            }

            return cursorStyle;

        } catch (TimeoutException e) {
            System.out.println("Previous button is NOT visible within the timeout.");
            return "not-visible";
        }
    }

    public String checkIfPreviousButtonIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(arrowBackwardPrevious));
            String cursorStyle = arrowBackwardPrevious.getCssValue("cursor");

            System.out.println("Cursor style of Previous button: " + cursorStyle);

            if ("pointer".equals(cursorStyle)) {
                System.out.println("Previous button appears clickable (cursor: pointer).");
            } else {
                System.out.println("Previous button appears NOT clickable (cursor: " + cursorStyle + ").");
            }

            return cursorStyle;

        } catch (TimeoutException e) {
            System.out.println("Previous button is NOT visible within the timeout.");
            return "not-visible";
        }
    }

    public void clickPreviousArrow() {
        arrowBackwardPrevious.click();
    }

    public boolean isClickableNextArrow() {
        new Actions(driver).moveToElement(arrowForwardNextPagination).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(arrowForwardNextPagination));
            String cursorStyle = arrowForwardNextPagination.getCssValue("cursor");

            System.out.println("Cursor style of Previous button: " + cursorStyle);

            return "pointer".equals(cursorStyle);

        } catch (TimeoutException e) {
            System.out.println("Previous button is NOT visible within the timeout.");
            return false;
        }
    }

    public void clickLastPageArrowBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(lastPageArrowBtn));
        lastPageArrowBtn.click();
    }

    public void setPriority(String priorityValue) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(priority));
            Select select = new Select(priority);
            select.selectByVisibleText(priorityValue);
            System.out.println("Priority set to: " + priorityValue);
        } catch (Exception e) {
            System.out.println("Failed to select priority: " + e.getMessage());
        }
    }

    public boolean isPriorityFieldVisible() {
        return priority.isDisplayed();
    }

    public boolean isLinkedRequirementTableVisible() {
        return linkedRequirementTable.isDisplayed();
    }

    public void clickCloseButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(closebutton));
            closebutton.click();
            System.out.println("Close button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Failed to click Close button: " + e.getMessage());
        }
    }

    public boolean isStatusFieldVisible()

    {
        return status.isDisplayed();
    }

    public boolean isRequirementUpdatedSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(notificationMessage));

            String messageText = notificationMessage.getText().trim();
            return messageText.equals("Requirement updated successfully.");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isModuleUpdatedSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(notificationMessage));

            String messageText = notificationMessage.getText().trim();
            return messageText.equals("Module updated successfully.");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTypeFieldVisible() {
        return type.isDisplayed();
    }

    public boolean noLinkedRequirementVisibility() {
        return noLinkedRequirement.isDisplayed();
    }

    public String getAlertMessage() {
        return AlertMessageForModuleName.getText();
    }

    public boolean isModuleIdClickable() {
        String editable = headingModuleId.getAttribute("contenteditable");
        return "true".equalsIgnoreCase(editable);
    }

    public int getRequirementCountFromFooter() {
        String footerText = requirementCountFooter.getText(); // e.g. "Total 37 entries"
        return Integer.parseInt(footerText.replaceAll("[^0-9]", ""));
    }

    public void clickDeleteModuleIcon() {
        Actions a = new Actions(driver);
        a.moveToElement(deleteModuleIcon).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(deleteModuleIcon)).click();
    }

    public void clearModuleName() {
        moduleNameInput.clear(); // now works
    }

    public String getModuleNameAlertMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(moduleNameAlertMessage));
        return moduleNameAlertMessage.getText();
    }

    public void clickCancelButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
    }

    public String getDeleteConfirmationMessage() throws InterruptedException {
        Thread.sleep(1500);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(actionDialog));
        return modal.findElement(By.id("actionDialog-message")).getText().trim();
    }

    public String alretMeaasgeForDeletingModule() {
        return alertMessageWhenTryToDeleteModule.getText();
    }

    public void clickCloseBtnOfALertModuleName() {
        alertBoxCloseBtnForModule.click();
    }

    public void confirmDelete() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(4000);

        try {
            WebElement dialog = wait.until(driver -> driver.findElement(By.id("actionDialog")));
            wait.until(driver -> {
                int w = dialog.getSize().getWidth();
                int h = dialog.getSize().getHeight();
                return w > 0 && h > 0;
            });
            WebElement yesButton = dialog.findElement(By.id("confirmBtn"));
            js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", yesButton);
            wait.until(ExpectedConditions.invisibilityOf(dialog));
            System.out.println("Module deletion confirmed successfully!");
        } catch (TimeoutException e) {
            System.err.println("Delete confirmation dialog did not appear or could not be clicked: " + e.getMessage());
            throw e;
        }
    }

    private By successNotification = By.id("notification");

    public String getSuccessNotificationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            WebElement notificationElement = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(successNotification));
            return notificationElement.getText().trim();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get success notification message", e);
        }
    }

    public void clickButtonConfirmationYesForUnsavedChanges() {
        buttonYesConfirmationUnsavedChanges.click();
    }

    public void clickButtonConfirmationNoForUnsavedChanges() {
        buttonNoConfirmationUnsavedChanges.click();
    }
}