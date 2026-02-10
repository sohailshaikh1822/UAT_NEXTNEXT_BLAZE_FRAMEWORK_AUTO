package utils;

import org.openqa.selenium.*;
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
}
