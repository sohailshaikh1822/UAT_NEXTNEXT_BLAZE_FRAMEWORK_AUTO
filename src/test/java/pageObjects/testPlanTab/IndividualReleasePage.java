package pageObjects.testPlanTab;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.time.Duration;
import java.util.List;

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
            } catch (InterruptedException ignored) {}
        }

        throw new AssertionError(
                "Release update notification not found for Release: " + releaseId
        );
    }

}
