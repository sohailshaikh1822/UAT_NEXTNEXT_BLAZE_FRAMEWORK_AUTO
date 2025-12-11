package pageObjects.executeTestCaseTab;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;
import java.util.List;

import java.time.Duration;
import java.util.regex.Pattern;
import utils.WaitUtils;

public class IndividualTestRun extends BasePage {
    public IndividualTestRun(WebDriver driver) {
        super(driver);
    }

    // Locators

    @FindBy(xpath = "//body/div[@class='requirements']/div[@class='frame']/div[@id='execute_rightPanel']/div[@class='test-run-cases']/div[@id='test-run-frames']/div[@class='test-run-frame-5']/div[@class='test-run-frame-6']/div[@class='test-run-frame-6-1']/div[1]")
    WebElement QAUser;

    @FindBy(xpath = "//body/div[@class='requirements']/div[@class='frame']/div[@id='execute_rightPanel']/div[@class='test-run-cases']/div[@id='test-run-frames']/div[@class='test-run-frame-5']/div[@class='test-run-frame-6']/div[@class='test-run-frame-6-1']/div[2]")
    WebElement BusinessUser;

    @FindBy(xpath = "//div[@class='test-run-description']")
    WebElement PreCondition;

    @FindBy(xpath = "//div[@class='test-run-text-3']")
    WebElement labelTestRunId;

    @FindBy(xpath = "//div[@class='test-run-name']")
    WebElement headingTestRunName;

    @FindBy(xpath = "//div[@class='test-run-form-text-field']//select[@class='test-run-text select-dropdown']")
    WebElement dropdownStatus;

    @FindBy(xpath = "//button[@id='submitButton']")
    WebElement buttonSave;

    @FindBy(xpath = "//button[@id='newLogButton']")
    WebElement buttonCreateTestLog;

    @FindBy(xpath = "//button[@id='closeButton']")
    WebElement buttonClose;

    @FindBy(xpath = "//div[@id='notification']")
    WebElement notificationAfterEdit;

    @FindBy(xpath = "//button[@id='linkDefectBtn']")
    WebElement buttonLinkDefect;

    @FindBy(xpath = "//button[@id='linkAttachmentBtn']")
    WebElement buttonLinkAttachment;

    @FindBy(xpath = "//button[@class='test-run-div-wrapper']")
    WebElement displayDefectAfterLink;

    @FindBy(xpath = "//div[normalize-space()='Test Logs']")
    WebElement tabTestLogs;

    @FindBy(xpath = "//div[normalize-space()='Execution History']")
    WebElement tabExecutionHistory;

    @FindBy(xpath = "//input[@id='viewAll']")
    WebElement radioShowAllSteps;

    @FindBy(xpath = "//input[@id='businessStep']")
    WebElement radioShowBusinessSteps;

    @FindBy(xpath = "//div[@class='test-run-form-text-field-2']//select[@class='test-run-text select-dropdown']")
    WebElement dropdownOverallStatusBesidesUpdate;

    @FindBy(xpath = "//button[@id='selectedStepsButton']")
    WebElement buttonUpdate;

    @FindBy(xpath = "//input[@id='masterCheckbox']")
    WebElement checkBoxSelectAllSteps;

    public WebElement checkboxForSteps(int stepNo) {
        return driver.findElement(
                By.xpath("//div[normalize-space()='" + stepNo + "']/../div[@class='table-row-cell checkbox-cell']"));
    }

    public WebElement uatCategoryForSteps(int stepNo) {
        return driver.findElement(
                By.xpath("//div[normalize-space()='" + stepNo + "']/../div[@class='table-row-cell uat-category']"));
    }

    public WebElement descriptionForSteps(int stepNo) {
        return driver.findElement(
                By.xpath("//div[normalize-space()='" + stepNo + "']/../div[@class='table-row-cell name']"));
    }

    public WebElement expectedResultForSteps(int stepNo) {
        return driver.findElement(
                By.xpath("//div[normalize-space()='" + stepNo + "']/../div[@class='table-row-cell expected-result']"));
    }

    public WebElement actualResultForSteps(int stepNo) {
        return driver.findElement(
                By.xpath("//div[normalize-space()='" + stepNo + "']/..//textarea[@class='non-expandable-textarea']"));
    }

    public WebElement statusForSteps(int stepNo) {
        return driver.findElement(By
                .xpath("//div[normalize-space()='" + stepNo + "']/..//select[@class='test-run-text select-dropdown']"));
    }

    // Actions

    Actions a = new Actions(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public boolean isErrorAfterEmptyTestLogSaveVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(notificationAfterEdit));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTestRunName() {
        return labelTestRunId.getText();
    }

    public void clickSaveButton() {
        a.moveToElement(buttonSave).perform();
        buttonSave.click();
        wait.until(ExpectedConditions.visibilityOf(notificationAfterEdit));
    }

    public void clickCloseButton() {
        a.moveToElement(buttonClose).perform();
        buttonClose.click();
    }

    public void clickCreateTestLog() {
        a.moveToElement(buttonCreateTestLog).perform();
        buttonCreateTestLog.click();
    }

    public void selectStatus(String statusTxt) {
        Select s = new Select(dropdownStatus);
        dropdownStatus.click();
        s.selectByVisibleText(statusTxt);
    }

    public void clickLinkDefect() {
        Select s = new Select(dropdownStatus);
        System.out.println(buttonLinkDefect.isEnabled());
        if (!buttonLinkDefect.isEnabled()) {
            s.selectByVisibleText("Failed");
            clickSaveButton();
        }
        a.moveToElement(buttonLinkDefect).perform();
        buttonLinkDefect.click();
    }

    public void clickLinkAttachment() {
        a.moveToElement(buttonLinkAttachment).perform();
        buttonLinkAttachment.click();
    }

    public void clickTabTestLogs() {
        a.moveToElement(tabTestLogs).perform();
        tabTestLogs.click();
    }

    public void clickTabExecutionHistory() {
        a.moveToElement(tabExecutionHistory).perform();
        tabExecutionHistory.click();
    }

    public void selectDropdownStatusBesidesUpdate(String statusText) {
        Select s = new Select(dropdownOverallStatusBesidesUpdate);
        dropdownOverallStatusBesidesUpdate.click();
        s.selectByVisibleText(statusText);
    }

    public void clickUpdate() {
        a.moveToElement(buttonUpdate).perform();
        buttonClose.click();
    }

    public void clickShowAllStepsRadio() {
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='viewAll']")));

        checkbox.click();

    }

    public void clickShowOnlyBusinessStepsRadio() {
        radioShowBusinessSteps.click();
    }

    public void selectCheckAllStepsCheckbox() {
        checkBoxSelectAllSteps.click();
    }

    public void clickCheckboxOfTheStep(int stepNo) {
        checkboxForSteps(stepNo).click();
    }

    public void EnterActualResultOfTheStep(int stepNo, String actualResultText) {
        a.moveToElement(actualResultForSteps(stepNo)).perform();
        actualResultForSteps(stepNo).click();
        actualResultForSteps(stepNo).sendKeys(actualResultText);
    }

    public String getDefectIdAfterLink() {
        try {
            wait.until(ExpectedConditions.visibilityOf(displayDefectAfterLink));
            return displayDefectAfterLink.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void SelectStatusOfTheStep(int stepNo, String stepText) {
        a.moveToElement(statusForSteps(stepNo)).perform();
        statusForSteps(stepNo).click();
        Select s = new Select(statusForSteps(stepNo));
        s.selectByVisibleText(stepText);
    }

    public boolean isTestLogUpdatedDisplayed() {
        try {
            String message = notificationAfterEdit.getText().trim();
            return message.equalsIgnoreCase("Test log updated successfully.");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isTestLogCreatedDisplayed() {
        try {
            WebElement notificationMessage = driver.findElement(By.xpath("//div[@id='notification']"));
            String message = notificationMessage.getText().trim();
            return message.equalsIgnoreCase("Test log created successfully.");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(xpath = "//table//tr[contains(@class, 'test-run-row')]")
    private List<WebElement> testRunRows;

    @FindBy(xpath = "//span[@class='pagination-next']")
    private WebElement nextPageButton;

    @FindBy(xpath = "//span[@class='pagination-prev']")
    private WebElement previousPageButton;

    private String testRunNameLocator = "//div[@id='testRunsWithCaseDetailsTable']//div[contains(@class,'testlistrow')]//div[contains(@class,'testlistcell1')]/a[contains(text(),'%s')]";

    public void enterSearchTerm(String testRunName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(testRunName);

    }

    public void clickSearchButton() {
        searchButton.click();

    }

    public boolean isTestRunVisible(String testRunName) {

        String locator = String.format(testRunNameLocator, testRunName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        return driver.findElement(By.xpath(locator)).isDisplayed();

    }

    public String getActualResultOfStep(int stepNo) {
        try {
            WebElement actualResultField = driver.findElement(
                    By.xpath("//div[normalize-space()='" + stepNo
                            + "']/..//textarea[@class='non-expandable-textarea']"));
            wait.until(ExpectedConditions.visibilityOf(actualResultField));
            return actualResultField.getAttribute("value").trim();
        } catch (NoSuchElementException e) {
            System.out.println("Actual result field not found for step: " + stepNo);
            return null;
        }
    }

    public boolean isLinkDefectButtonClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(buttonLinkDefect));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getExecutionHistoryCount() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            WebElement tableContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='test-run-frame-10']")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tableContainer);

            long lastHeight = -1;
            while (true) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;",
                        tableContainer);
                WaitUtils.waitFor500Milliseconds();;

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollHeight;",
                        tableContainer);
                if (newHeight == lastHeight)
                    break;
                lastHeight = newHeight;
            }

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath(
                            "//div[@class='test-run-frame-10']//div[contains(@class,'tr') or contains(@class,'table-row')]")));

            List<WebElement> rows = tableContainer.findElements(
                    By.xpath(".//div[contains(@class,'tr') or contains(@class,'table-row')]"));

            return rows.size();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isDefectPresent() {
        try {
            By defectLocator = By.xpath(
                    "//body/div[@class='requirements']/div[@class='frame']/div[@id='execute_rightPanel']/div[@class='test-run-cases']/div[@id='test-run-frames']/div[@class='test-run-form-text-field']/div[1]");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement defectSection = wait.until(ExpectedConditions.visibilityOfElementLocated(defectLocator));

            String defectText = defectSection.getText().trim();
            System.out.println("Defect section text: " + defectText);

            return Pattern.compile("DF-\\d+").matcher(defectText).find();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean areAllElementsVisible() {
        try {
            return labelTestRunId.isDisplayed() &&
                    headingTestRunName.isDisplayed() &&
                    dropdownStatus.isDisplayed() &&
                    buttonLinkAttachment.isDisplayed() &&
                    buttonLinkDefect.isDisplayed() &&
                    radioShowAllSteps.isDisplayed() &&
                    radioShowBusinessSteps.isDisplayed() &&
                    QAUser.isDisplayed() &&
                    BusinessUser.isDisplayed() &&
                    PreCondition.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
