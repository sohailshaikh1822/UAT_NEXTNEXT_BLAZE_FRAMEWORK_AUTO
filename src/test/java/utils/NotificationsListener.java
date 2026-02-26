package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.BaseClass;

import java.time.Duration;
import java.util.List;

public class NotificationsListener extends BaseClass {

    private WebDriver driver;
    private WebDriverWait wait;

    public NotificationsListener(WebDriver driver) {
       // super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30-second timeout
    }

    // Click notification directly using Id
    public void clickNotificationById(String id) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By notificationLocator = By.xpath(
                "//span[contains(@class,'notif-text') and contains(., '" + id + "')]"
        );

        WebElement notification =
                wait.until(ExpectedConditions.elementToBeClickable(notificationLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", notification);
    }

    // Open bell and click matching notification
    public void clickNotificationIcon() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");

        By notificationBody =
                By.xpath("//div[contains(@class,'notification-body')]");

        By allNotifications =
                By.xpath("//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]");

        // Open notification panel
        WebElement bell = wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

    }

    public boolean verifyNotificationHoverText(String id, String expectedTooltipText) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Dynamic notification locator
        By notificationLocator = By.xpath(
                "(//span[contains(@class,'notif-text') and contains(., '" + id + "')])[1]"
        );

        WebElement notification =
                wait.until(ExpectedConditions.visibilityOfElementLocated(notificationLocator));

        // Hover action
        Actions actions = new Actions(driver);
        actions.moveToElement(notification).perform();

        // Wait for tooltip to appear (adjust locator if needed)
        By tooltipLocator = By.xpath("//div[@class='notification-item disabled']");
        WebElement tooltip =
                wait.until(ExpectedConditions.visibilityOfElementLocated(tooltipLocator));

        String actualTooltipText = tooltip.getText().trim();
        logger.info("actual result"+ actualTooltipText);

        return actualTooltipText.equals(expectedTooltipText);
    }


    public void clickCreatedNotification(String tcId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By createdNotificationLocator = By.xpath(
                "//span[@class='notif-text' and contains(text(), \"'" + tcId + "'\") and contains(text(),'created by')]"
        );

        WebElement notification = wait.until(
                ExpectedConditions.elementToBeClickable(createdNotificationLocator)
        );

        // Scroll into view (important if inside panel)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                notification
        );

        // Click using JS (more reliable for notifications)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                notification
        );
    }

    public void clickUpdatedNotification(String tcId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By createdNotificationLocator = By.xpath(
                "//span[@class='notif-text' and contains(text(), \"'" + tcId + "'\") and contains(text(),'updated by')]"
        );

        WebElement notification = wait.until(
                ExpectedConditions.elementToBeClickable(createdNotificationLocator)
        );

        // Scroll into view (important if inside panel)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                notification
        );

        // Click using JS (more reliable for notifications)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                notification
        );
    }

    public void clickRestoredNotification(String tcId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By createdNotificationLocator = By.xpath(
                "//span[@class='notif-text' and contains(text(), \"'" + tcId + "'\") and contains(text(),'is restored by')]"
        );

        WebElement notification = wait.until(
                ExpectedConditions.elementToBeClickable(createdNotificationLocator)
        );

        // Scroll into view (important if inside panel)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                notification
        );

        // Click using JS (more reliable for notifications)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                notification
        );
    }

    public String getRestoredNotificationText(String tcId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By deletedNotificationLocator = By.xpath(
                "//span[@class='notif-text' and contains(text(), \"'" + tcId + "'\") and contains(text(),'is restored by')]"
        );

        WebElement notification = wait.until(
                ExpectedConditions.visibilityOfElementLocated(deletedNotificationLocator)
        );

        // Scroll into view (important if inside panel)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                notification
        );

        // Return the captured text
        return notification.getText();
    }

    public void verifyAllUpdatedNotifications(String rqId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String baseXpath = "//span[@class='notif-text' and contains(text(), \""
                + rqId + "\") and contains(text(),'updated by')]";

        // Wait until at least one notification is present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(baseXpath)));

        int totalNotifications = driver.findElements(By.xpath(baseXpath)).size();
        System.out.println("Total notifications found for " + rqId + " : " + totalNotifications);

        for (int i = 1; i <= totalNotifications; i++) {

            By indexedNotification = By.xpath("(" + baseXpath + ")[" + i + "]");

           // clickNotificationIcon();

            wait.until(ExpectedConditions.elementToBeClickable(indexedNotification));

            WebElement notification = driver.findElement(indexedNotification);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});",
                    notification
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    notification
            );
            WaitUtils.waitFor3000Milliseconds();
            clickNotificationIcon();

        }
    }

    public void clickUpdatedModuleNotification(String entityId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");

        WebElement bell = wait.until(
                ExpectedConditions.elementToBeClickable(notificationBell)
        );
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        By updatedNotificationLocator = By.xpath(
                "//span[contains(@class,'notif-text') and " +
                        "contains(text(),\"'" + entityId + "'\") and " +
                        "contains(text(),'updated by')]"
        );

        WebElement notification = wait.until(
                ExpectedConditions.elementToBeClickable(updatedNotificationLocator)
        );
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                notification
        );
        js.executeScript(
                "arguments[0].click();",
                notification
        );
        System.out.println("Clicked on updated notification for: " + entityId);
    }


    public void verifyDeletedRequirementTooltip(String rqId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String cleanRqId = rqId.replace("*", "").trim();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By disabledNotifications = By.xpath(
                "//div[contains(@class,'notification-item') and contains(@class,'disabled')]"
        );

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {
                WebElement bell =
                        wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
                js.executeScript("arguments[0].click();", bell);

                WebElement body =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

                List<WebElement> notifications =
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(disabledNotifications));

                for (WebElement notification : notifications) {

                    String text = notification.findElement(
                            By.xpath(".//span[contains(@class,'notif-text')]")
                    ).getText().trim();

                    if (text.contains("'" + cleanRqId + "'") && text.contains("deleted")) {

                        String tooltipText = notification.getAttribute("title");

                        if (tooltipText == null || tooltipText.isEmpty()) {
                            throw new AssertionError(
                                    "Tooltip attribute is missing for deleted Requirement notification."
                            );
                        }

                        if (!tooltipText.equals("This item no longer exists")) {
                            throw new AssertionError(
                                    "Tooltip text mismatch.\nExpected: This item no longer exists\nActual: " + tooltipText
                            );
                        }

                        System.out.println(
                                "Deleted Requirement tooltip verified successfully: " + tooltipText
                        );
                        return;
                    }
                }
                js.executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollHeight",
                        body
                );

            } catch (StaleElementReferenceException ignored) {}

            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Deleted Requirement notification not found for Requirement: " + rqId
        );
    }
    public void verifyDeletedModuleTooltip(String moduleId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String cleanModuleId = moduleId.replace("*", "").trim();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By disabledNotifications = By.xpath(
                "//div[contains(@class,'notification-item') and contains(@class,'disabled')]"
        );

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {
                WebElement bell =
                        wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
                js.executeScript("arguments[0].click();", bell);

                WebElement body =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

                List<WebElement> notifications =
                        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(disabledNotifications));

                for (WebElement notification : notifications) {

                    String text = notification.findElement(
                            By.xpath(".//span[contains(@class,'notif-text')]")
                    ).getText().trim();
                    if (text.contains("'" + cleanModuleId + "'") && text.contains("deleted")) {

                        String tooltipText = notification.getAttribute("title");

                        if (tooltipText == null || tooltipText.isEmpty()) {
                            throw new AssertionError(
                                    "Tooltip attribute is missing for deleted Module notification."
                            );
                        }

                        if (!tooltipText.equals("This item no longer exists")) {
                            throw new AssertionError(
                                    "Tooltip text mismatch.\nExpected: This item no longer exists\nActual: " + tooltipText
                            );
                        }

                        System.out.println(
                                "Deleted Module tooltip verified successfully: " + tooltipText
                        );
                        return;
                    }
                }
                js.executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollHeight",
                        body
                );

            } catch (StaleElementReferenceException ignored) {}

            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Deleted Module notification not found for Module: " + moduleId
        );
    }
    public void verifyDeletedRequirementNotificationDisabled(String rqId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String cleanRqId = rqId.replace("*", "").trim();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By disabledNotifications = By.xpath(
                "//div[contains(@class,'notification-item') and contains(@class,'disabled')]"
        );

        WebElement bell = wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        WebElement body =
                wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));

        List<WebElement> notifications =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(disabledNotifications));

        for (WebElement notification : notifications) {

            String text = notification.findElement(
                    By.xpath(".//span[contains(@class,'notif-text')]")
            ).getText().trim();

            if (text.contains("'" + cleanRqId + "'") && text.contains("deleted")) {
                if (!notification.getAttribute("class").contains("disabled")) {
                    throw new AssertionError("Notification is NOT disabled.");
                }
                String tooltipText = notification.getAttribute("title");

                if (!"This item no longer exists".equals(tooltipText)) {
                    throw new AssertionError(
                            "Tooltip mismatch. Actual: " + tooltipText
                    );
                }
                String beforeUrl = driver.getCurrentUrl();
                notification.click();
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
                String afterUrl = driver.getCurrentUrl();

                if (!beforeUrl.equals(afterUrl)) {
                    throw new AssertionError(
                            "Deleted Requirement notification is navigating but should NOT."
                    );
                }

                System.out.println(
                        "Deleted Requirement notification verified successfully (Disabled + No Navigation)."
                );
                return;
            }
        }

        throw new AssertionError(
                "Deleted Requirement notification not found for Requirement: " + rqId
        );
    }

    public void verifyDeletedModuleNotificationNotClickable(String moduleId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String cleanModuleId = moduleId.replace("*", "").trim();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");

        By deletedModuleNotification = By.xpath(
                "//div[contains(@class,'notification-item') and contains(@class,'disabled')]"
                        + "//span[contains(@class,'notif-text') "
                        + "and contains(text(),\"'" + cleanModuleId + "'\") "
                        + "and contains(text(),'deleted by')]"
                        + "/ancestor::div[contains(@class,'notification-item')]"
        );
        WebElement bell = wait.until(ExpectedConditions.elementToBeClickable(notificationBell));
        js.executeScript("arguments[0].click();", bell);

        wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBody));
        WebElement notification = wait.until(
                ExpectedConditions.visibilityOfElementLocated(deletedModuleNotification)
        );
        if (!notification.getAttribute("class").contains("disabled")) {
            throw new AssertionError("Deleted Module notification is NOT disabled.");
        }
        String tooltip = notification.getAttribute("title");

        if (tooltip == null || !tooltip.equals("This item no longer exists")) {
            throw new AssertionError(
                    "Tooltip mismatch.\nExpected: This item no longer exists\nActual: " + tooltip
            );
        }
        String beforeUrl = driver.getCurrentUrl();

        notification.click();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        String afterUrl = driver.getCurrentUrl();

        if (!beforeUrl.equals(afterUrl)) {
            throw new AssertionError(
                    "Deleted Module notification navigated to another page but should NOT."
            );
        }

        System.out.println(
                "Deleted Module notification verified successfully (Disabled + Not Clickable)."
        );
    }
}
