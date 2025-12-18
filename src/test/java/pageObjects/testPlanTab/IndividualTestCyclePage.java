package pageObjects.testPlanTab;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.time.Duration;
import utils.WaitUtils;

public class IndividualTestCyclePage extends BasePage {
    public IndividualTestCyclePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "(//input[@class='test-plan-test-cycles-supporting-text'])[1]")
    WebElement testCycleNameInput;

    @FindBy(xpath = "(//div[@class='test-plan-test-cycles-text-3'])[1]")
    WebElement testCycleHeader;

    @FindBy(xpath = "//input[@class='testcase-select']")
    WebElement inputTargetRelease;

    @FindBy(xpath = "//div[@class='testPlan-prototype']")
    WebElement descriptionBeforeClick;

    @FindBy(xpath = "//div[@class='rte-editor ql-container ql-snow']/div[@contenteditable='true']")
    WebElement descriptionAfterClick;

    @FindBy(xpath = "(//button[@id='submitButton'])[1]")
    WebElement saveButton;

    @FindBy(xpath = "//div[@id='notification']")
    WebElement notificationAfterClickSave;

    @FindBy(xpath = "//div[contains(text(),'Test Cycle created successfully.')]")
    WebElement testCycleCreatedSuccessMessage;

    // Actions

    public String getTestCycleHeader() {
        return testCycleHeader.getText();
    }

    public void setTestCycleName(String cycleName) throws InterruptedException {
        WaitUtils.waitFor2000Milliseconds();
        testCycleNameInput.clear(); // as input not div
        testCycleNameInput.sendKeys(cycleName);
    }

    public String getTargetRelease() {
        return inputTargetRelease.getAttribute("value");
    }

    public void setDescription(String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(descriptionBeforeClick));
        descriptionBeforeClick.click();

        wait.until(ExpectedConditions.elementToBeClickable(descriptionAfterClick));
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '';", descriptionAfterClick);
        descriptionAfterClick.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        descriptionAfterClick.sendKeys(Keys.BACK_SPACE);
        descriptionAfterClick.clear();
        descriptionAfterClick.sendKeys(description);
    }

    public void clickSave() {
        saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(notificationAfterClickSave));
    }

    public String getTestCycleCreatedSuccessMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(testCycleCreatedSuccessMessage));
        return testCycleCreatedSuccessMessage.getText();
    }

}
