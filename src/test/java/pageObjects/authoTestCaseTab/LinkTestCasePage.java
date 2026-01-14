package pageObjects.authoTestCaseTab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;
import org.openqa.selenium.*;
import utils.WaitUtils;

import java.time.Duration;

public class LinkTestCasePage extends BasePage {

    public LinkTestCasePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//input[@id='searchInputTCModal']")
    WebElement searchTC;

    @FindBy(xpath = "//*[@id='searchTCButton']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='defect-modal-text-wrapper-3']")
    WebElement Pid;

    @FindBy(xpath = "//p[@id='actionDialogtp-message']")
    WebElement alert;

    @FindBy(xpath = "//div[@id='notification']")
    WebElement LinkingTcNotification;


    // Actions
    public void enterSearchText(String tcName) {
        searchTC.clear();
        searchTC.sendKeys(tcName);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void searchTestCase(String tcName) {
        enterSearchText(tcName);
        clickSearch();
    }

    public void clickPid(String pidFromExcel) {
        WebElement pidElement = driver.findElement(
                By.xpath("//div[@class='defect-modal-text-wrapper-3' and text()='" + pidFromExcel + "']"));
        pidElement.click();
    }

    @FindBy(id = "actionDialogtp-message")
    WebElement alertMessageLinkingTC;


    public String getAlertMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement message = wait.until(
                ExpectedConditions.visibilityOf(alertMessageLinkingTC)
        );

        return message.getText().trim();
    }


    public boolean isTestCaseAlreadyLinked() {

        String message = getAlertMessage();
        return message.equalsIgnoreCase(
                "This test case is already linked to the requirement."
        );
    }

    public String getAlertMessageWhileLinkingNewTc() {
        return LinkingTcNotification.getText();
    }


}
