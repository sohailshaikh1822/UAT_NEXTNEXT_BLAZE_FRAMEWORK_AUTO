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

}
