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

    // Test Cycle History Navigation Tab
    @FindBy(xpath = "//div[contains(@class,'testcase-history-nav') and .//span[text()='Test Cycle History']]")
    WebElement testCycleHistoryTab;

    @FindBy(xpath = "//div[contains(@class,'history-section')]")
    WebElement historySection;

    @FindBy(xpath = "//div[contains(@class,'version-table-header-row')]//div[contains(text(),'Version')]")
    WebElement versionColumnHeader;

    @FindBy(xpath = "//div[contains(@class,'version-table-header-row')]//div[contains(text(),'Updated By')]")
    WebElement updatedByColumnHeader;

    @FindBy(xpath = "//div[contains(@class,'version-table-header-row')]//div[contains(text(),'Updated At')]")
    WebElement updatedAtColumnHeader;

    @FindBy(xpath = "//div[contains(@class,'version-table-row')]")
    List<WebElement> versionRows;

    @FindBy(xpath = ".//span[@class='version-title']")
    List<WebElement> versionNumbers;

    @FindBy(xpath = ".//span[@class='version-meta']")
    List<WebElement> updatedByList;

    @FindBy(xpath = ".//span[contains(@class,'rd-history-date')]")
    List<WebElement> updatedAtList;

    @FindBy(xpath = "(//span[contains(@class,'version-title')])[last()]")
    WebElement latestVersionValue;

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
            } catch (InterruptedException ignored) {
            }
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

            } catch (StaleElementReferenceException ignored) {
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
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

            } catch (StaleElementReferenceException ignored) {
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
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

        WebElement bell =
                wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        WebElement body =
                wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement deletedNotification =
                wait.until(ExpectedConditions.visibilityOfElementLocated(deletedCLNotification));

        actions.moveToElement(deletedNotification).perform();

        js.executeScript("arguments[0].click();", deletedNotification);
    }


    public void verifyDeletedTestCycleNotificationNotClickable(String cycleId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        By notificationBell =
                By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody =
                By.xpath("//div[contains(@class,'notification-body')]");

        By deletedCycleNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + cycleId + "') "
                                + "and contains(text(),'is deleted by')]"
                );

        WebElement bell =
                wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement hoverElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(deletedCycleNotification));

        actions.moveToElement(hoverElement)
                .pause(Duration.ofMillis(500))
                .perform();

        WebElement clickElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(deletedCycleNotification));

        js.executeScript("arguments[0].click();", clickElement);

        System.out.println(
                "Verified deleted Test Cycle notification is not clickable for Cycle: " + cycleId
        );
    }

    public void verifyCreationAndDeletionTestCycleNotificationsAreDisabled(String cycleId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");

        By createdTestCycleNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + cycleId + "') "
                                + "and contains(text(),'is created by')]"
                );

        By deletedTestCycleNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + cycleId + "') "
                                + "and contains(text(),'is deleted by')]"
                );

        WebElement bell = wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement createdHoverElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(createdTestCycleNotification));

        actions.moveToElement(createdHoverElement)
                .pause(Duration.ofMillis(500))
                .perform();

        WebElement createdClickElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(createdTestCycleNotification));

        js.executeScript("arguments[0].click();", createdClickElement);

        WebElement deletedHoverElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(deletedTestCycleNotification));

        actions.moveToElement(deletedHoverElement)
                .pause(Duration.ofMillis(500))
                .perform();

        WebElement deletedClickElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(deletedTestCycleNotification));

        js.executeScript("arguments[0].click();", deletedClickElement);

        System.out.println(
                "Verified creation and deletion notifications are disabled for Test Cycle: " + cycleId
        );
    }

    public void VerifyTooltipForADisabledNotificationWhenATestCycleIsDeletedFromTestPlanTab(String cycleId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");

        By deletedCycleNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item') and contains(@class,'disabled') "
                                + "and .//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + cycleId + "') "
                                + "and contains(text(),'is deleted by')]]"
                );

        WebElement bell =
                wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement notification =
                wait.until(ExpectedConditions.visibilityOfElementLocated(deletedCycleNotification));

        String tooltipText = notification.getAttribute("title");

        if (tooltipText == null || !tooltipText.equals("This item no longer exists")) {
            throw new AssertionError(
                    "Tooltip text mismatch.\nExpected: This item no longer exists\nActual: "
                            + tooltipText
            );
        }

        System.out.println("Verified tooltip for deleted Test Cycle: " + cycleId);
    }

    public void clickOnTestCycleHistoryTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(testCycleHistoryTab));
        testCycleHistoryTab.click();

        wait.until(ExpectedConditions.visibilityOf(historySection));
    }
    public void verifyHistorySectionIsDisplayed() {
        if (!historySection.isDisplayed()) {
            throw new AssertionError("History section is not displayed");
        }
    }

    public void verifyHistoryTableColumns() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(versionColumnHeader));
        wait.until(ExpectedConditions.visibilityOf(updatedByColumnHeader));
        wait.until(ExpectedConditions.visibilityOf(updatedAtColumnHeader));

        if (!versionColumnHeader.isDisplayed()) {
            throw new AssertionError("Version column is not displayed");
        }

        if (!updatedByColumnHeader.isDisplayed()) {
            throw new AssertionError("Updated By column is not displayed");
        }

        if (!updatedAtColumnHeader.isDisplayed()) {
            throw new AssertionError("Updated At column is not displayed");
        }

        System.out.println("All history table columns are displayed correctly");
    }

    public void verifyVersionNumbersAreDisplayedCorrectly() {

        if (versionRows.isEmpty()) {
            throw new AssertionError("No version rows found in history table");
        }

        int expectedVersion = 1;

        for (WebElement row : versionRows) {

            String versionText = row.findElement(
                    By.xpath(".//span[@class='version-title']")
            ).getText().trim();

            if (versionText.isEmpty()) {
                throw new AssertionError("Version number is empty");
            }

            int actualVersion = Integer.parseInt(versionText);

            if (actualVersion != expectedVersion) {
                throw new AssertionError(
                        "Version sequence mismatch. Expected: "
                                + expectedVersion + " but found: " + actualVersion
                );
            }

            expectedVersion++;
        }

        System.out.println("Version numbers are displayed correctly in sequence");
    }

    public boolean verifyDefaultVersionIsOne() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            String version = wait.until(
                            ExpectedConditions.visibilityOf(latestVersionValue))
                    .getText().trim();

            return version.equals("1");

        } catch (Exception e) {
            return false;
        }
    }


}

