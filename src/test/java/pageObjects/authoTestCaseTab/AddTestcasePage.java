package pageObjects.authoTestCaseTab;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import pageObjects.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddTestcasePage extends BasePage {
    public AddTestcasePage(WebDriver driver) {
        super(driver);
    }

    // locators

//    @FindBy(xpath = "(//input[@type='text'])[1]")
//    WebElement textName;
    @FindBy(xpath = "//table[@id='newTestCasesTable']//tbody//tr//td[1]//input[@type='text' and @maxlength='500']")
    WebElement textName;

//    @FindBy(xpath = "(//input[@type='text'])[2]")
//    WebElement textDescription;

    @FindBy(xpath = "(//tbody//tr//td/input[@type='text' and @required])[2]")
    WebElement textDescription;

    @FindBy(xpath = "(//select[@class='priorityDropdown testcase-text-wrapper-15 testcase-select'])[1]")
    WebElement dropDownPriority;

    @FindBy(xpath = "(//select[@class='priorityDropdown testcase-text-wrapper-15 testcase-select'])[2]")
    WebElement dropDownType;

//    @FindBy(xpath = "(//select)[4]/option")
//    List<WebElement> optionsDropDownType;

    @FindBy(xpath = "//table[@id='newTestCasesTable']//tbody//tr/td[4]//select/option")
    public List<WebElement> optionsDropDownType;


    @FindBy(xpath = "(//select[@class='priorityDropdown testcase-text-wrapper-15 testcase-select'])[3]")
    WebElement dropDownQAUser;

    @FindBy(xpath = "(//input[@type='text'])[3]")
    WebElement textPrecondition;

    @FindBy(xpath = "//button[@type='button' and contains(@class,'testcase-button') and normalize-space()='SAVE']")
    WebElement buttonSave;

    @FindBy(xpath = "//div[contains(text(), 'Error: Name is required.')]")
    WebElement tcNameRequiredWarningMessage;

    public WebElement linkTestCaseIdFromId(String id) {
        return driver.findElement(By.xpath("//div[@class='testlistcell']/a[text()='" + id + "']"));
    }

    public WebElement linkTestCaseIdFromName(String name) {
        return driver.findElement(By.xpath("//p[text()='" + name + "']/ancestor::div[@class='testlistrow']//a"));
    }

    // Actions

    public void setTestCaseName(String testCaseName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By testCaseNameInput = By.xpath(
                "//div[@id='createTestCasesModal']//table[@id='newTestCasesTable']//tbody//tr[1]//td[1]//input"
        );

        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(testCaseNameInput)
        );

        wait.until(ExpectedConditions.elementToBeClickable(nameField));

        try {
            nameField.click();
            nameField.clear();
            nameField.sendKeys(testCaseName);
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].focus();", nameField);
            js.executeScript("arguments[0].value='';", nameField);
            js.executeScript("arguments[0].value=arguments[1];", nameField, testCaseName);
        }
    }




    public void setDescription(String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        Actions actions = new Actions(driver);

        By[] descriptionLocators = {
                By.xpath("//table[@id='newTestCasesTable']//tbody/tr/td[2]/input"),
                By.xpath("(//tbody//tr//td/input[@type='text' and @required])[2]")
        };
        WebElement descriptionField = null;
        for (By locator : descriptionLocators) {
            try {
                descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            } catch (Exception ignored) {}
        }
        if (descriptionField == null) {
            throw new RuntimeException("Description input field not found using any provided locator.");
        }
        wait.until(ExpectedConditions.elementToBeClickable(descriptionField));
        actions.moveToElement(descriptionField).pause(200).click().perform();
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }




    public void selectPriority(String priority) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(dropDownPriority));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownPriority));
        Select select = new Select(dropDownPriority);
        select.selectByVisibleText(priority);
    }


    public void selectType(String type) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(dropDownType));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownType));
        Select select = new Select(dropDownType);
        select.selectByVisibleText(type);
    }


    public void selectQaUser(String qaUser) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(dropDownQAUser));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownQAUser));
        Select select = new Select(dropDownQAUser);
        select.selectByVisibleText(qaUser);
    }


    public void setPrecondition(String precondition) {
        textPrecondition.sendKeys(precondition);
    }

    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);
        actions.moveToElement(buttonSave).perform();
        wait.until(ExpectedConditions.visibilityOf(buttonSave));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSave));
        buttonSave.click();
    }


    public String getTestcaseId(String name) {
        return linkTestCaseIdFromName(name).getText();
    }

    public boolean isNameDisplayed() {
        return textName.isDisplayed();
    }

    public boolean isDescriptionDisplayed() {
        return textDescription.isDisplayed();
    }

    public boolean isPriorityDropdownDisplayed() {
        return dropDownPriority.isDisplayed();
    }

    public boolean isTypeDropdownDisplayed() {
        return dropDownType.isDisplayed();
    }

    public boolean isQAUserDropdownDisplayed() {
        return dropDownQAUser.isDisplayed();
    }

    public boolean isPreconditionDisplayed() {
        return textPrecondition.isDisplayed();
    }

    public boolean isAllTypeOptionsVisible() {

        List<String> actual = new ArrayList<>();
        for (WebElement ele : optionsDropDownType) {
            actual.add(ele.getText().trim());
        }
        List<String> expected = Arrays.asList(
                "Please Select",
                "Manual",
                "Automation",
                "Performance",
                "Scenario"
        );
        Collections.sort(actual);
        Collections.sort(expected);
        return actual.equals(expected);
    }


    public String waitForNameFieldRequiredError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("notification")));
        return element.getText().trim();
    }

    public String getTcNameRequiredWarningMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(tcNameRequiredWarningMessage));
        return tcNameRequiredWarningMessage.getText();
    }

    public String verifyTestCaseNameMaxLength() {
        String longName = "A".repeat(600);
        textName.clear();
        textName.sendKeys(longName);
        return textName.getAttribute("value");
    }

    public String waitAndGetAddTestCaseHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        By formLocator = By.id("createTestCasesForm");
        By headerLocator = By.xpath(".//h3");

        WebElement form = wait.until(
                ExpectedConditions.visibilityOfElementLocated(formLocator)
        );

        WebElement header = wait.until(
                ExpectedConditions.visibilityOfNestedElementsLocatedBy(form, headerLocator)
        ).getFirst();

        return header.getText().trim();
    }


}