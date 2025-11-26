package pageObjects.testPlanTab;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.text.SimpleDateFormat;
import java.time.Duration;

@Slf4j
public class IndividualTestSuitePage extends BasePage {
    public IndividualTestSuitePage(WebDriver driver) {
        super(driver);
    }

    // locators
    @FindBy(xpath = "//div[@class='test-plan-test-suites-text-3']")
    private WebElement testSuiteIdText;

    @FindBy(xpath = "//input[@class='test-plan-test-suites-supporting-text']")
    private WebElement testSuiteNameInput;

    @FindBy(id = "submitButton")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class='test-plan-test-suites-prototype']")
    private WebElement descriptionBeforeClick;

    @FindBy(xpath = "//div[@class='rte-editor ql-container ql-snow']/div[@contenteditable='true']")
    WebElement descriptionAfterClick;

    @FindBy(xpath = "(//input[@type='date'])[1]")
    private WebElement plannedStartDateInput;

    @FindBy(xpath = "(//input[@type='date'])[2]")
    private WebElement plannedEndDateInput;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement targetReleaseInput;

    @FindBy(xpath = "(//select[@class='test-execution-text select-dropdown'])[1]")
    private WebElement environmentDropdown;

    @FindBy(xpath = "(//select[@class='test-execution-text select-dropdown'])[2]")
    private WebElement executionTypeDropdown;

    @FindBy(xpath = "(//select[@class='test-execution-text select-dropdown'])[3]")
    private WebElement testDataSourceDropdown;

    @FindBy(xpath = "//div[@id='notification']")
    WebElement notificationDeletionUpdation;

    @FindBy(xpath = "//div[contains(text(),'Test Suite created successfully.')]")
    WebElement testSuiteCreatedSuccessMessage;

    // actions
    public String getTestSuiteId() {
        return testSuiteIdText.getText();
    }

    public void enterTestSuiteName(String name) {
        testSuiteNameInput.clear();
        testSuiteNameInput.sendKeys(name);
    }

    public void clickSaveButton() {
        saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(notificationDeletionUpdation));
    }

    public void enterDescription(String desc) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(descriptionBeforeClick));
        descriptionBeforeClick.click();

        wait.until(ExpectedConditions.elementToBeClickable(descriptionAfterClick));
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '';", descriptionAfterClick);
        descriptionAfterClick.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        descriptionAfterClick.sendKeys(Keys.BACK_SPACE);
        descriptionAfterClick.clear();
        descriptionAfterClick.sendKeys(desc);
    }

    public void setPlannedStartDate(String yyyymmdd) {
        try {
            plannedStartDateInput.sendKeys(
                    new SimpleDateFormat("MM/dd/yyyy").format(DateUtil.getJavaDate(Double.parseDouble(yyyymmdd))));
        } catch (Exception e) {
            plannedStartDateInput.sendKeys(yyyymmdd);
        }
    }

    public void setPlannedEndDate(String yyyymmdd) {

        try {
            plannedEndDateInput.sendKeys(
                    new SimpleDateFormat("MM/dd/yyyy").format(DateUtil.getJavaDate(Double.parseDouble(yyyymmdd))));
        } catch (Exception e) {
            plannedStartDateInput.sendKeys(yyyymmdd);
        }
    }

    public String getTargetRelease() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Thread.sleep(1000);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(targetReleaseInput, "title"));
        return targetReleaseInput.getAttribute("value");
    }

    public void selectEnvironment(String env) {
        Select select = new Select(environmentDropdown);
        select.selectByVisibleText(env);
    }

    public void selectExecutionType(String type) {
        Select select = new Select(executionTypeDropdown);
        select.selectByVisibleText(type);
    }

    public void selectTestDataSource(String source) {
        Select select = new Select(testDataSourceDropdown);
        select.selectByVisibleText(source);
    }

    public String getTestSuiteCreatedSuccessMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(testSuiteCreatedSuccessMessage));
        return testSuiteCreatedSuccessMessage.getText();
    }
}
