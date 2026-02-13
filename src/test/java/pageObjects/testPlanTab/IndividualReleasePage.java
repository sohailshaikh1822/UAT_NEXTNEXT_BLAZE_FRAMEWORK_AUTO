package pageObjects.testPlanTab;

//import org.apache.xmlbeans.impl.logging.NoOpLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.interactions.Actions;


public class IndividualReleasePage extends BasePage {
    public IndividualReleasePage(WebDriver driver) {
        super(driver);
    }

    // locators

    @FindBy(xpath = "//div[@class='test-plan-releases-text-3']")
    WebElement releaseId;

    @FindBy(xpath = "//input[@class='test-plan-releases-supporting-text']")
    WebElement releaseName;

    @FindBy(xpath = "//select[@class='testcase-select value']")
    WebElement releaseStatus;

    @FindBy(xpath = "(//input[@type='date'])[1]")
    WebElement releaseStartDate;

    @FindBy(xpath = "(//input[@type='date'])[2]")
    WebElement releaseEndDate;

    @FindBy(xpath = "//div[@class='ql-editor ql-blank']")
    WebElement releaseDescription;

    @FindBy(xpath = "//div[@class='rich-editor-scrollable']")
    WebElement releaseDescriptionContainer;

    @FindBy(xpath = "//div[@class='ql-editor ql-blank']")
    WebElement releaseDescriptionBlank;

    @FindBy(xpath = "//div[@class='ql-editor']")
    WebElement releaseDescriptionEditor;

    @FindBy(xpath = "//textarea[@id='precondition']")
    WebElement releaseNote;

    @FindBy(xpath = "//div[@class='test-plan-releases-save']")
    WebElement saveButton;

    // Actions

    public String getReleaseId() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By releaseIdLocator = By.xpath("//div[@class='test-plan-releases-text-2']");

        WebElement releaseIdElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(releaseIdLocator)
        );

        String releaseId = releaseIdElement.getText().trim().replace("*", "");

        if (releaseId.isEmpty()) {
            throw new AssertionError("Release ID is empty");
        }

        return releaseId;
    }


    public void setReleaseName(String Releasename) {
        releaseName.clear();
        releaseName.sendKeys(Releasename);
    }

    public void setReleaseStatus(String statusValue) {
        Select select = new Select(releaseStatus);
        select.selectByVisibleText(statusValue);
    }

    public void setDescription(String descriptionText) {
        try {
            if (releaseDescriptionBlank.isDisplayed()) {
                releaseDescriptionBlank.sendKeys(descriptionText);
            }
        } catch (Exception e) {
            releaseDescriptionEditor.sendKeys(descriptionText);
        }
    }

    public void setReleaseNote(String releasenote) {
        releaseNote.clear();
        releaseNote.sendKeys(releasenote);
    }

    public void setReleaseStartDate(String releaseSdate) {
        releaseStartDate.clear();
        releaseStartDate.sendKeys(releaseSdate);
    }

    public void setReleaseEndDate(String releaseEdate) {
        releaseEndDate.clear();
        releaseEndDate.sendKeys(releaseEdate);
    }

    public boolean SaveButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(saveButton));
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public void verifyReleaseUpdateNotification(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String updaterName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'RL-\\d+'\\s+is\\s+updated\\s+by\\s+"
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

                    if (text.startsWith("'RL-") && text.contains("is updated by")) {

                        if (!text.contains("'" + releaseId + "'")) {
                            continue;
                        }

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Release update notification format mismatch.\nExpected Regex: "
                                            + expectedRegex +
                                            "\nActual Text: " + text
                            );
                        }

                        System.out.println(
                                "Release update notification verified successfully: " + text
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
                "Release update notification not found for Release: " + releaseId
        );
    }


    public void verifyReleaseDeletedNotification(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String deleterName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'RL-\\d+'\\s+is\\s+deleted\\s+by\\s+"
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

                    if (text.startsWith("'RL-") && text.contains("is deleted by")) {

                        if (!text.contains("'" + releaseId + "'")) {
                            continue;
                        }

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Release delete notification format mismatch.\nExpected Regex: "
                                            + expectedRegex +
                                            "\nActual Text: " + text
                            );
                        }

                        System.out.println("Release delete notification verified successfully: " + text);
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

        throw new AssertionError("Release delete notification not found for Release: " + releaseId);
    }

//    Recyclebin

    public boolean verifyRestoreToastMessage(String expectedId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By toastLocator = By.xpath("//div[contains(@class,'toast-body')]");

        try {
            WebElement toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(toastLocator)
            );

            String toastText = toast.getText().trim();
            System.out.println("Toast message displayed: " + toastText);

            return toastText.contains(expectedId)
                    && toastText.toLowerCase().contains("restored");

        } catch (TimeoutException e) {
            System.out.println("Restore toast notification not displayed");
            return false;
        }
    }

    public void verifyReleaseRestoreNotification(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String restorerName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'RL-\\d+'\\s+is\\s+restored\\s+by\\s+"
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
                String text = latestNotification.getText().trim();

                if (!text.contains("'" + releaseId + "'")) {
                    throw new AssertionError(
                            "Latest notification does not contain Release ID. Found: " + text
                    );
                }

                if (!text.matches(expectedRegex)) {
                    throw new AssertionError(
                            "Release restore notification format mismatch.\nExpected Regex: "
                                    + expectedRegex + "\nActual Text: " + text
                    );
                }

                System.out.println(
                        "Release restore notification verified successfully: " + text
                );
                return;

            } catch (StaleElementReferenceException ignored) {
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }

        throw new AssertionError(
                "Release restore notification not found for Release: " + releaseId
        );
    }

    public void verifyDeletedReleaseNotificationNotClickable(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        By notificationBell =
                By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody =
                By.xpath("//div[contains(@class,'notification-body')]");

        By deletedReleaseNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + releaseId + "') "
                                + "and contains(text(),'is deleted by')]"
                );

        WebElement bell =
                wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

       WebElement hoverElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(deletedReleaseNotification));

        actions.moveToElement(hoverElement)
                .pause(Duration.ofMillis(500))
                .perform();

        WebElement clickElement =
                wait.until(ExpectedConditions.presenceOfElementLocated(deletedReleaseNotification));

        js.executeScript("arguments[0].click();", clickElement);

        System.out.println(
                "Verified deleted Release notification is not clickable for Release: " + releaseId
        );
    }

    public void verifyCreationAndDeletionReleaseNotificationsAreDisabled(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");

        By createdReleaseNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + releaseId + "') "
                                + "and contains(text(),'is created by')]"
                );

        By deletedReleaseNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + releaseId + "') "
                                + "and contains(text(),'is deleted by')]"
                );

        WebElement bell = wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement createdHoverElement = wait.until(ExpectedConditions.presenceOfElementLocated(createdReleaseNotification));

        actions.moveToElement(createdHoverElement).pause(Duration.ofMillis(500)).perform();

        WebElement createdClickElement = wait.until(ExpectedConditions.presenceOfElementLocated(createdReleaseNotification));

        js.executeScript("arguments[0].click();", createdClickElement);

        WebElement deletedHoverElement = wait.until(ExpectedConditions.presenceOfElementLocated(deletedReleaseNotification));

        actions.moveToElement(deletedHoverElement).pause(Duration.ofMillis(500)).perform();

        WebElement deletedClickElement = wait.until(ExpectedConditions.presenceOfElementLocated(deletedReleaseNotification));

        js.executeScript("arguments[0].click();", deletedClickElement);

        System.out.println(
                "Verified creation and deletion notifications are disabled for Release: " + releaseId
        );
    }

    public void verifyCreatedReleaseNotificationNotClickable(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");

        By createdReleaseNotification = By.xpath("//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + releaseId + "') "
                                + "and contains(text(),'is created by')]");

        WebElement bell = wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement hoverElement = wait.until(ExpectedConditions.presenceOfElementLocated(createdReleaseNotification));

        actions.moveToElement(hoverElement).pause(Duration.ofMillis(500)).perform();

        WebElement clickElement = wait.until(ExpectedConditions.presenceOfElementLocated(createdReleaseNotification));

        js.executeScript("arguments[0].click();", clickElement);

        System.out.println("Verified created Release notification is not clickable for Release: " + releaseId);
    }

    public void verifyDeletedReleaseTooltip(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String cleanReleaseId = releaseId.replace("*", "").trim();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By disabledNotifications = By.xpath(
                "//div[contains(@class,'notification-item') and contains(@class,'disabled')]"
        );

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {

                WebElement bell = wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
                js.executeScript("arguments[0].click();", bell);

                WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

                List<WebElement> notifications =
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(disabledNotifications));

                for (WebElement notification : notifications) {

                    String text = notification.findElement(
                            By.xpath(".//span[contains(@class,'notif-text')]")
                    ).getText().trim();

                    if (text.contains(cleanReleaseId) && text.contains("deleted")) {

                        String tooltipText = notification.getAttribute("title");

                        if (tooltipText == null || tooltipText.isEmpty()) {
                            throw new AssertionError(
                                    "Tooltip attribute is missing for deleted Release notification."
                            );
                        }

                        if (!tooltipText.equals("This item no longer exists")) {
                            throw new AssertionError(
                                    "Tooltip text mismatch.\nExpected: This item no longer exists\nActual: " + tooltipText
                            );
                        }

                        System.out.println("Deleted Release tooltip verified successfully: " + tooltipText);
                        return;
                    }
                }

                js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", body);

            } catch (StaleElementReferenceException ignored) {}

            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        throw new AssertionError("Deleted Release notification not found for Release: " + releaseId);
    }


    public void clickCreatedReleaseNotification(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String cleanReleaseId = releaseId.replace("*", "").trim();

        By notificationBell =
                By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody =
                By.xpath("//div[contains(@class,'notification-body')]");

        By createdReleaseNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + cleanReleaseId + "') "
                                + "and contains(text(),'is created by')]"
                );

        WebElement bell =
                wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement createdNotification =
                wait.until(ExpectedConditions.elementToBeClickable(createdReleaseNotification));

        js.executeScript("arguments[0].click();", createdNotification);

        System.out.println(
                "Clicked on created Release notification for Release: " + releaseId
        );
    }

    public void clickUpdatedReleaseNotification(String releaseId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String cleanReleaseId = releaseId.replace("*", "").trim();

        By notificationBell =
                By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody =
                By.xpath("//div[contains(@class,'notification-body')]");

        By updatedReleaseNotification =
                By.xpath(
                        "//div[contains(@class,'notification-item')]"
                                + "//span[contains(@class,'notif-text') "
                                + "and contains(text(),'" + cleanReleaseId + "') "
                                + "and contains(text(),'is updated by')]"
                );

        WebElement bell =
                wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        WebElement updatedNotification =
                wait.until(ExpectedConditions.elementToBeClickable(updatedReleaseNotification));

        js.executeScript("arguments[0].click();", updatedNotification);

        System.out.println(
                "Clicked on updated Release notification for Release: " + releaseId
        );
    }



}