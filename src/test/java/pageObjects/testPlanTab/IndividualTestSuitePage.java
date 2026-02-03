package pageObjects.testPlanTab;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By testSuiteIdLocator = By.xpath("//div[@class='test-plan-test-suites-text-2']");

        WebElement testSuiteIdElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(testSuiteIdLocator)
        );

        String testSuiteId = testSuiteIdElement.getText().trim().replace("*", "");

        if (testSuiteId.isEmpty()) {
            throw new AssertionError("Test Suite ID is empty");
        }

        return testSuiteId;
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
        //  WaitUtils.waitFor1000Milliseconds();
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

    public String getLoggedInUserName() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By userLocator = By.xpath("//div[contains(@class,'JS') and @data-fullname]");

        WebElement userElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(userLocator)
        );

        String fullName = userElement.getAttribute("data-fullname").trim();

        if (fullName.isEmpty()) {
            throw new AssertionError("Logged-in user full name is empty");
        }

        return fullName;
    }

    public void verifySuiteUpdateNotification(String suiteId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String updaterName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'TS-\\d+'\\s+is\\s+updated\\s+by\\s+"
                        + updaterName.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {

                WebElement bell = wait.until(
                        ExpectedConditions.elementToBeClickable(notificationBell)
                );
                js.executeScript("arguments[0].click();", bell);

                WebElement body = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(notificationBody)
                );

                List<WebElement> notifications = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(allNotifications)
                );

                for (WebElement el : notifications) {

                    String text = el.getText().trim();

                    if (text.startsWith("'TS-") && text.contains("is updated by")) {

                        if (!text.contains("'" + suiteId + "'")) {
                            continue;
                        }

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Suite update notification format mismatch.\nExpected Regex: "
                                            + expectedRegex +
                                            "\nActual Text: " + text
                            );
                        }

                        System.out.println(
                                "Suite update notification verified successfully: " + text
                        );
                        return;
                    }
                }

                js.executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollHeight",
                        body
                );

            } catch (StaleElementReferenceException ignored) {
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Suite update notification not found for Suite: " + suiteId
        );
    }

    public void verifyTestSuiteDeleteNotification(String suiteId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String deleterName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'TS-\\d+'\\s+is\\s+deleted\\s+by\\s+"
                        + deleterName.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {

                WebElement bell = wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
                js.executeScript("arguments[0].click();", bell);

                WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

                List<WebElement> notifications =
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allNotifications));

                for (WebElement el : notifications) {

                    String text = el.getText().trim();

                    if (text.startsWith("'TS-") && text.contains("is deleted by")) {

                        if (!text.contains("'" + suiteId + "'")) {
                            continue;
                        }

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Test Suite delete notification format mismatch.\nExpected Regex: "
                                            + expectedRegex +
                                            "\nActual Text: " + text
                            );
                        }

                        System.out.println("Test Suite delete notification verified successfully: " + text);
                        return;
                    }
                }

                js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", body);

            } catch (StaleElementReferenceException ignored) {}

            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        throw new AssertionError("Test Suite delete notification not found for Suite: " + suiteId);
    }


    public void verifyTestSuiteRestoreNotification(String testSuiteId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String restorerName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'" + testSuiteId + "'\\s+is\\s+restored\\s+by\\s+"
                        + restorerName.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {

                WebElement bell =
                        wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
                js.executeScript("arguments[0].click();", bell);

                WebElement body =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

                List<WebElement> notifications =
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allNotifications));

                WebElement latestNotification = notifications.get(0);
                String actualText = latestNotification.getText().trim();

                if (!actualText.contains("'" + testSuiteId + "'")) {
                    throw new AssertionError(
                            "Latest notification does not contain Test Suite ID.\nFound: " + actualText
                    );
                }

                if (!actualText.matches(expectedRegex)) {
                    throw new AssertionError(
                            "Test Suite restore notification format mismatch.\n"
                                    + "Expected Regex: " + expectedRegex + "\n"
                                    + "Actual Text: " + actualText
                    );
                }

                System.out.println(
                        "Test Suite restore notification verified successfully: " + actualText
                );
                return;

            } catch (StaleElementReferenceException ignored) {
            }

            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Test Suite restore notification not found for Suite: " + testSuiteId
        );
    }


}
