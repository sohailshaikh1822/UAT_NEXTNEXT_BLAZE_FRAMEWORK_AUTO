package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import utils.WaitUtils;

public class BaseClass {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Logger logger = LogManager.getLogger(this.getClass());
    public Properties p;

    // Getter for WebDriver
    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    @Parameters({ "browser", "mode" })
    public void setUp(String br, String mode) throws IOException, InterruptedException {
        FileReader config = new FileReader(".//src//test//resources//config.properties");
        p = new Properties();
        p.load(config);

        if (p.getProperty("execution_environment").equalsIgnoreCase("remote")) {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setBrowserName(br);
            dc.setPlatform(Platform.WIN11);
            URL url = new URL("http://localhost:4444/wd/hub");
            RemoteWebDriver remoteDriver = new RemoteWebDriver(url, dc);
            driver.set(remoteDriver);
        } else {
            WebDriver wd = null;
            switch (br.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    if (mode.equalsIgnoreCase("headless")) {
                        options.addArguments("--headless=new");
                        options.addArguments("--window-size=1920,1080");
                    }
                    wd = new ChromeDriver(options);
                    break;
                case "firefox":
                    wd = new FirefoxDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");
                    wd = new EdgeDriver();
                    break;
                default:
                    System.out.println("Invalid browser...");
                    return;
            }
            driver.set(wd);

        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(p.getProperty("appURL"));
        getDriver().manage().window().maximize();
        if (mode.equalsIgnoreCase("headless")) {
            WaitUtils.waitFor1000Milliseconds();
            getDriver().manage().window().setSize(new Dimension(1920, 1080));
        }

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        if (getDriver() != null) {
            logout();
            getDriver().quit();
            driver.remove();
        }
    }

//    public void logout() {
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
//        WebElement chevron = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//img[@id='chevron-logout']")
//        ));
//        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", chevron);
//        WebElement logOut = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//a[normalize-space()='Logout']")
//        ));
//        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logOut);
//    }

//    public void logout() {
//        WebDriver driver = getDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        WebElement chevron = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//img[@id='chevron-logout']"))
//        );
//        try {
//            chevron.click();
//        } catch (Exception e) {
//            js.executeScript("arguments[0].click();", chevron);
//        }
//        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//a[normalize-space()='Logout']"))
//        );
//        try {
//            logoutBtn.click();
//        } catch (Exception e) {
//            js.executeScript("arguments[0].click();", logoutBtn);
//        }
//    }

    public void logout() throws InterruptedException {
        WaitUtils.waitFor2000Milliseconds();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
                getDriver().findElement(By.xpath("//img[@id='chevron-logout']")));
        // Three locators to try
        By[] possibleLocators = {
                By.xpath("//a[normalize-space()='Logout']"),
                By.xpath("//div[@id='dropdown-menu-row']"),
                By.xpath("//a[contains(text(),'Logout')]")
        };
        // Try each locator until one works
        for (By locator : possibleLocators) {
            try {
                WebElement elem = getDriver().findElement(locator);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", elem);
                break;  // Stop when clicked
            } catch (Exception e) {
                // ignore and try next
            }
        }
        // Click the actual Logout link
        try {
            WebElement logOut = getDriver().findElement(By.xpath("//a[normalize-space()='Logout']"));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logOut);
        } catch (Exception e) {
            WebElement logOut = getDriver().findElement(By.xpath("//a[contains(text(),'Logout')]"));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logOut);
        }
    }



    public void login() throws InterruptedException {

        getDriver().findElement(By.xpath("//input[@type='email']")).sendKeys(p.getProperty("email"));
        getDriver().findElement(By.xpath("//input[@type='submit']")).click();

        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        getDriver().findElement(By.xpath("//input[@type='password']")).sendKeys(p.getProperty("password"));

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        submitBtn.click();

        WebElement clickToYes = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        clickToYes.click();
        Thread.sleep(6000);
        getDriver().navigate().refresh();
        Thread.sleep(2000);
    }

    // Capture screenshot (Thread-safe)
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String targetPath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File target = new File(targetPath);

        FileUtils.copyFile(source, target);
        return targetPath;
    }

    // Wait for page to fully load (useful for Blazor apps after navigation)
    public void waitForPageLoad() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(30))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }
}