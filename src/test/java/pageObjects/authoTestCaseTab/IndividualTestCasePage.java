package pageObjects.authoTestCaseTab;

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

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import utils.WaitUtils;

public class IndividualTestCasePage extends BasePage {
    public IndividualTestCasePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//div[normalize-space()='CLOSE']")
    WebElement buttonClose;

    @FindBy(xpath = "//div[normalize-space()='CREATE TEST RUN']")
    WebElement buttonCreateTestRun;

    @FindBy(xpath = "//div[normalize-space()='SAVE']")
    WebElement buttonSave;

    @FindBy(xpath = "//input[@id='testCaseName']")
    WebElement headingTestCaseName;

    @FindBy(xpath = "//div[@class='test-plan-releases-prototype']")
    WebElement textDescriptionBeforeClick;

    @FindBy(xpath = "//div[@class='ql-editor ql-blank']")
    WebElement textDescriptionAfterClick;

    @FindBy(xpath = "//textarea[@id='precondition']")
    WebElement textPrecondition;

//    @FindBy(xpath = "//div[@id='priority']//select[@class='testcase-select value']")
//    WebElement dropDownPriority;

    @FindBy(xpath = "//div[@id='priority']//select")
    WebElement dropDownPriority;


    @FindBy(xpath = "//div[@id='priority']//select[@class='testcase-select value']/option")
    List<WebElement> OptionsDropDownPriority;

    @FindBy(xpath = "//div[@id='status']//select[@class='testcase-select value']")
    WebElement dropdownStatus;

    @FindBy(xpath = "//div[@id='status']//select[@class='testcase-select value']/option")
    List<WebElement> OptionsDropdownStatus;

    @FindBy(xpath = "//div[@id='type']//select[@class='testcase-select value']")
    WebElement dropdownType;

    @FindBy(xpath = "//div[@id='type']//select[@class='testcase-select value']/option")
    List<WebElement> OptionsDropdownType;

    @FindBy(xpath = "//div[@id='automation']//select[@class='testcase-select value']")
    WebElement dropdownAutomationProgress;

    @FindBy(xpath = "//div[@id='automation']//select[@class='testcase-select value']/option")
    List<WebElement> OptionsDropdownAutomationProgress;

    @FindBy(xpath = "//div[@id='qa-user']//select[@class='testcase-select value']")
    WebElement dropDownQaUser;

    @FindBy(xpath = "//div[@id='qa-user']//select[@class='testcase-select value']/option")
    List<WebElement> OptionsDropDownQaUser;

    @FindBy(xpath = "//input[@id='hideTechnical']")
    WebElement CheckboxHideTechnical;

    @FindBy(xpath = "//button[@id='addcalledTestCase']")
    WebElement buttonAddCalledTestCase;

    @FindBy(xpath = "//button[@id='addRowIcon']")
    WebElement buttonAddTestStep;

    @FindBy(xpath = "//span[@class='step-number']")
    List<WebElement> allSteps;
// New Locator

    @FindBy(xpath = "//div[contains (text(),'APPROVE')]")
    WebElement approveBtn;

    @FindBy(xpath = "//div[@id='version']")
    WebElement version;

    @FindBy(xpath = "//div[@id='notification']")
    WebElement confirmationMessage;




    public WebElement labelStepNo(String s) {
        return driver.findElement(By.xpath("//span[@class='step-number' and text()='" + s + "']"));
    }

    public WebElement textStepDescriptionBeforeClick(int stepNo) {
        return driver.findElement(By.xpath("//span[@class='step-number' and text()='" + stepNo
                + "']/ancestor::div[@class='table-row']//div[@class='table-cell description']"));
    }

    public WebElement textStepDescriptionAfterClick(int stepNo) {
        return driver.findElement(By.xpath("//span[@class='step-number' and text()='" + stepNo
                + "']/ancestor::div[@class='table-row']//div[@class='table-cell description']//div[@class='rte-editor ql-container ql-snow']/div[@contenteditable='true']"));
    }

    public WebElement textExpectedResultBeforeClick(int stepNo) {
        return driver.findElement(By.xpath("//span[@class='step-number' and text()='" + stepNo
                + "']/ancestor::div[@class='table-row']//div[@class='table-cell result']"));
    }

    public WebElement textStepResultAfterClick(int stepNo) {
        return driver.findElement(By.xpath("//span[@class='step-number' and text()='" + stepNo
                + "']/ancestor::div[@class='table-row']//div[@class='table-cell result']//div[@class='rte-editor ql-container ql-snow']/div[@contenteditable='true']"));
    }

    public WebElement buttonDeleteAction(int stepNo) {
        return driver.findElement(By.xpath("//span[@class='step-number' and text()='" + stepNo
                + "']/ancestor::div[@class='table-row']//div[@class='table-cell action']//button"));
    }

    @FindBy(xpath = "//i[@class='fa-solid fa-circle-plus']")
    WebElement buttonAddRow;

    @FindBy(xpath = "//div[contains(text(), 'CANCEL')]")
    WebElement cancelBtn;

    // Actions
    public void clickCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonClose)).click();
    }
    public void clickApproveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(approveBtn)).click();
    }

    public String getVersion() {
        return version.getText();
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public boolean isApproveBtnDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(buttonSave)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @FindBy(xpath = "//div[normalize-space()='APPROVE']/ancestor::button")
    WebElement approveButton;

    public boolean isApproveButtonDisabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(approveButton));
        String classAttribute = approveButton.getAttribute("class");
        return classAttribute != null && classAttribute.contains("disabled");
    }


    public boolean isModelDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(buttonClose)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddTestStep() throws InterruptedException {
        Actions a = new Actions(driver);
        a.moveToElement(buttonAddTestStep).perform();
        WaitUtils.waitFor1000Milliseconds();
        a.click(buttonAddTestStep).perform();
    }

    public void clickAddRow() throws InterruptedException {
        WaitUtils.waitFor2000Milliseconds();
        Actions a = new Actions(driver);
        a.moveToElement(buttonAddTestStep).perform();
        buttonAddTestStep.click();
    }

    public String getStepCount(String s) {

        return labelStepNo(s).getText();
    }

    public void setStepDescription(String description, int step) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        WebElement beforeClick = wait.until(ExpectedConditions
                .elementToBeClickable(textStepDescriptionBeforeClick(step)));
        actions.moveToElement(beforeClick).perform();
        beforeClick.click();
        WebElement afterClick = wait.until(ExpectedConditions
                .elementToBeClickable(textStepDescriptionAfterClick(step)));
        WaitUtils.waitFor1000Milliseconds();
        afterClick.clear();
        afterClick.sendKeys(description);
    }

    public void setStepExpectedResult(String expectedResult, int step) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        WebElement beforeClick = wait.until(ExpectedConditions
                .elementToBeClickable(textExpectedResultBeforeClick(step)));
        actions.moveToElement(beforeClick).perform();
        beforeClick.click();
        WebElement afterClick = wait.until(ExpectedConditions
                .elementToBeClickable(textStepResultAfterClick(step)));
        WaitUtils.waitFor1000Milliseconds();
        afterClick.clear();
        afterClick.sendKeys(expectedResult);
    }

    public void clickDeleteButton(int stepCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        WebElement deleteButton = wait.until(ExpectedConditions
                .elementToBeClickable(buttonDeleteAction(stepCount)));
        actions.moveToElement(deleteButton).perform();
        deleteButton.click();
    }

//
public void clickSaveButton() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    Actions actions = new Actions(driver);

    List<WebElement> saveButtons = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[normalize-space()='SAVE']")
            )
    );

    int attempts = Math.min(3, saveButtons.size());

    for (int i = 0; i < attempts; i++) {
        try {
            WebElement saveBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(saveButtons.get(i))
            );

            actions.moveToElement(saveBtn).pause(Duration.ofMillis(300)).perform();
            saveBtn.click();

            return;

        } catch (Exception e) {
            System.out.println("SAVE button attempt " + (i + 1) + " failed, trying next...");
        }
    }

    throw new RuntimeException("Unable to click any SAVE button after 3 attempts");
}


    public void addTestStepsFromExcelForNewTestCase(String description, String expectedResult)
            throws InterruptedException {
        String[] descriptionArray = description.split(",");
        String[] expectedResultArray = expectedResult.split(",");

        for (int i = 0; i <= expectedResultArray.length - 1; i++) {
            clickAddTestStep();
            setStepDescription(descriptionArray[i], i + 1);
            driver.findElement(By.xpath("//div[@class='table-header-cell description']")).click();
            WaitUtils.waitFor1000Milliseconds();;
            setStepExpectedResult(expectedResultArray[i], i + 1);
        }
    }

    public void addTestStepsAtEndForExistingTestCase(String description, String expectedResult)
            throws InterruptedException {
        int currentStep = allSteps.size();
        String[] descriptionArray = description.split(",");
        String[] expectedResultArray = expectedResult.split(",");

        for (int i = 0; i <= expectedResultArray.length - 1; i++) {
            clickAddTestStep();
            setStepDescription(descriptionArray[i], currentStep + 1);
            WaitUtils.waitFor1000Milliseconds();
            setStepExpectedResult(expectedResultArray[i], currentStep + 1);
            currentStep = currentStep + 1;
        }
    }

    public void editSpecificTestStep(int stepNo, String description, String expectedResult)
            throws InterruptedException {
        if (description != null) {
            setStepDescription(description, stepNo);
        }
        if (expectedResult != null) {
            setStepExpectedResult(expectedResult, stepNo);
        }
    }

    public int getCountPriorityOptions() throws InterruptedException {
        WaitUtils.waitFor2000Milliseconds();
        return OptionsDropDownPriority.size();
    }

    public int getCountStatusOptions() {
        return OptionsDropdownStatus.size();
    }

    public int getCountTypeOptions() {
        return OptionsDropdownType.size();
    }

    public int getCountTypeAutomationProgress() {
        return OptionsDropdownAutomationProgress.size();
    }

    public boolean clickAddCalledTestcaseBtn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonAddCalledTestCase));
            button.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCancelBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }

    public void setTestCaseName(String newName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(headingTestCaseName));
        nameField.clear();
        nameField.sendKeys(newName);
    }

    public String getTestCaseName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOf(headingTestCaseName)).getAttribute("value").trim();
    }

    public void closebutton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonClose)).click();
    }
    public void clickCreateTestRunButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreateTestRun)).click();
    }

    public boolean verifyTestCaseDetailsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(textDescriptionBeforeClick));
        wait.until(ExpectedConditions.visibilityOf(textPrecondition));
        wait.until(ExpectedConditions.visibilityOf(dropDownPriority));
        wait.until(ExpectedConditions.visibilityOf(dropdownStatus));

        return textDescriptionBeforeClick.isDisplayed()
                && textPrecondition.isDisplayed()
                && dropDownPriority.isDisplayed()
                && dropdownStatus.isDisplayed();
    }

    public boolean isSaveButtonClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(buttonSave));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCloseButtonClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(buttonClose));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getStepCountInt() {
        return allSteps.size();
    }

    public boolean isCreateTestRunVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOf(buttonCreateTestRun));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCreateTestRunClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(buttonCreateTestRun));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



    public void selectPriority(String priority) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownPriority));

        Select select = new Select(dropDownPriority);

        switch (priority.trim().toLowerCase()) {
            case "undecided":
                select.selectByValue("721");
                break;
            case "low":
                select.selectByValue("722");
                break;
            case "medium":
                select.selectByValue("723");
                break;
            case "high":
                select.selectByValue("724");
                break;
            case "urgent":
                select.selectByValue("725");
                break;
            default:
                throw new NoSuchElementException("Invalid priority: " + priority);
        }
    }



    public String getTestCaseVersion() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By versionLocator = By.xpath("//div[@id='version' and contains(@class,'testcase-input')]");

        WebElement versionElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(versionLocator)
        );

        return versionElement.getText().trim();
    }

//    public void selectReleaseAndClickSave(String releaseName) {
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        WebElement sidebar = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.id("sidebar"))
//        );
//
//        By releaseLocator = By.xpath(
//                ".//div[contains(@class,'releases')][.//text()[normalize-space(.)='" + releaseName + "']]"
//        );
//
//        WebElement releaseElement = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        sidebar.findElement(releaseLocator)
//                )
//        );
//
//        js.executeScript("arguments[0].scrollIntoView({block:'center'});", releaseElement);
//        js.executeScript("arguments[0].click();", releaseElement);
//
//        By saveButton = By.xpath(
//                "//div[contains(@class,'test-run-popup-text-wrapper-5') and normalize-space()='SAVE']"
//        );
//
//        WebElement saveElement = wait.until(
//                ExpectedConditions.elementToBeClickable(saveButton)
//        );
//
//        js.executeScript("arguments[0].click();", saveElement);
//    }

    public void selectReleaseAndClickSave(String releaseName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement sidebar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("sidebar"))
        );

        By releaseLocator = By.xpath(
                ".//div[contains(@class,'releases')][.//text()[normalize-space(.)='" + releaseName.trim() + "']]"
        );

        WebElement releaseElement = wait.until(
                ExpectedConditions.elementToBeClickable(
                        sidebar.findElement(releaseLocator)
                )
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", releaseElement);
        js.executeScript("arguments[0].click();", releaseElement);

        wait.until(driver ->
                Objects.requireNonNull(releaseElement.getAttribute("class")).contains("active")
                        || Objects.requireNonNull(releaseElement.getAttribute("class")).contains("selected")
        );

        By saveButton = By.xpath(
                "//div[contains(@class,'test-run-popup-text-wrapper-5') and normalize-space()='SAVE']"
        );

        WebElement saveElement = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        );

        js.executeScript("arguments[0].click();", saveElement);
    }


}
