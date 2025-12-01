package pageObjects.testPlanTab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.BasePage;

import java.time.Duration;

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
        return releaseId.getText();
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

}
