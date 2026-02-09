package pageObjects.testPlanTab;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.time.Duration;
import java.util.List;

import utils.WaitUtils;
import org.openqa.selenium.interactions.Actions;


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

    public String getCycleId() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By cycleIdLocator = By.xpath("//div[@class='test-plan-test-cycles-text-2']");

        WebElement cycleIdElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cycleIdLocator)
        );

        String cycleId = cycleIdElement.getText().trim().replace("*", "");

        if (cycleId.isEmpty()) {
            throw new AssertionError("Cycle ID is empty");
        }

        return cycleId;
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

    public void verifyCycleUpdateNotification(String cycleId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String updaterName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'CL-\\d+'\\s+is\\s+updated\\s+by\\s+"
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

                    if (text.startsWith("'CL-") && text.contains("is updated by")) {

                        if (!text.contains("'" + cycleId + "'")) {
                            continue;
                        }

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Cycle update notification format mismatch.\nExpected Regex: "
                                            + expectedRegex +
                                            "\nActual Text: " + text
                            );
                        }

                        System.out.println(
                                "Cycle update notification verified successfully: " + text
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
                "Cycle update notification not found for Cycle: " + cycleId
        );
    }

    public void verifyTestCycleDeleteNotification(String cycleId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String deleterName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'CL-\\d+'\\s+is\\s+deleted\\s+by\\s+"
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

                    if (text.startsWith("'CL-") && text.contains("is deleted by")) {

                        if (!text.contains("'" + cycleId + "'")) {
                            continue;
                        }

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Test Cycle delete notification format mismatch.\nExpected Regex: "
                                            + expectedRegex +
                                            "\nActual Text: " + text
                            );
                        }

                        System.out.println("Test Cycle delete notification verified successfully: " + text);
                        return;
                    }
                }

                js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", body);

            } catch (StaleElementReferenceException ignored) {}

            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        throw new AssertionError("Test Cycle delete notification not found for Cycle: " + cycleId);
    }


    public void verifyTestCycleRestoreNotification(String testCycleId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String userName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'" + testCycleId + "'\\s+is\\s+deleted\\s+by\\s+"
                        + userName.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {
                WebElement bell =
                        wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
                js.executeScript("arguments[0].click();", bell);

                wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

                List<WebElement> notifications =
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allNotifications));

                for (WebElement notif : notifications) {
                    String text = notif.getText().trim();

                    if (text.matches(expectedRegex)) {
                        System.out.println(
                                "Test Cycle delete notification verified successfully: " + text
                        );
                        return;
                    }
                }

            } catch (StaleElementReferenceException ignored) {}

            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Delete notification not found for Test Cycle: " + testCycleId
        );
    }

    public void verifyDeletedCLNotificationNotClickable(String clId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        By notificationBell =
                By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody =
                By.xpath("//div[contains(@class,'notification-body')]");

        By deletedCLNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item') and contains(@class,'disabled')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + clId + "') "
                                + "and contains(text(),'is deleted by')]"
                );

        // Open notification panel
        WebElement bell =
                wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        WebElement body =
                wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement deletedNotification =
                wait.until(ExpectedConditions.visibilityOfElementLocated(deletedCLNotification));

        // Hover to show tooltip
        actions.moveToElement(deletedNotification).perform();

        // Attempt click (should not navigate)
        js.executeScript("arguments[0].click();", deletedNotification);
    }



}
