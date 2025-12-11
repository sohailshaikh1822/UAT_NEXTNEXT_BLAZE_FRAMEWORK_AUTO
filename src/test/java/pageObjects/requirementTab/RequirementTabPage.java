package pageObjects.requirementTab;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.BasePage;
import java.util.List;
import utils.WaitUtils;

import java.time.Duration;

public class RequirementTabPage extends BasePage {
    public RequirementTabPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    @FindBy(xpath = "//span[@id='requirements']")
    WebElement tabRequirements;

    @FindBy(xpath = "//img[@alt='Close Sidebar']")
    WebElement closeSideBar;

    @FindBy(xpath = "//img[@alt='Open Sidebar']")
    WebElement openSideBar;

    @FindBy(xpath = "(//i[@class='fa-solid tree-arrow fa-caret-right'])[1]")
    WebElement leftPanelProjectName;
    @FindBy(xpath = "//span[@class='entry-info']")
    WebElement totalEntriesRqCount;

    @FindBy(id = "confirmBtn")
    WebElement buttonYesConfirmDelete;

    @FindBy(id = "notification")
    WebElement notificationPopUp;

    @FindBy(xpath = "//button[normalize-space()='Help?']")
    WebElement clickHelp;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[1]")
    WebElement helpDropdown;

    @FindBy(xpath = "//button[@id='confirmBtn']")
    WebElement yesBtnConfirmationPopUp;
    @FindBy(xpath = "//button[@id='cancelBtn']")
    WebElement noBtnConfirmationPopUp;

    public WebElement arrowBeforeExpandRightPointing(String moduleName) {
        return driver.findElement(
                By.xpath("//span[text()='" + moduleName + "']/..//i[@class='fa-solid tree-arrow fa-caret-right']"));
    }

    public WebElement arrowAfterExpandDownPointing(String moduleName) {
        return driver.findElement(
                By.xpath("//span[text()='" + moduleName + "']/..//i[@class='fa-solid tree-arrow fa-caret-down']"));
    }

    @FindBy(xpath = "//i[@title='New Module']")
    WebElement iconNewModule;

    @FindBy(xpath = "//i[@title='Delete']")
    WebElement iconDelete;

    @FindBy(xpath = "//input[@class='supporting-text']")
    WebElement textModuleName;

    @FindBy(xpath = "//div[normalize-space()='SAVE']")
    WebElement buttonSave;

    @FindBy(xpath = "//div[@id='notification' and text()='Module created successfully.']")
    WebElement notificationAfterModuleCreation;

    @FindBy(xpath = "//div[@id='notification' and text()='Module updated successfully.']")
    WebElement notificationAfterModuleUpdation;
    @FindBy(xpath = "(//a[@class='text-wrapper-14'])[last()]")
    WebElement getNewRqIdText;

    @FindBy(id = "actionDialog-message")
    WebElement deleteMessage;

    @FindBy(id = "cancelBtn")
    WebElement btnNoInDeleteNotification;

    public WebElement leftModuleNameByName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='tree-node tree-node expanded']//span[normalize-space()='" + name + "']")));
    }

    @FindBy(xpath = "//div[contains(text(),'Priority')]")
    WebElement priority;

    @FindBy(xpath = "//div[contains(text(),'Status')]")
    WebElement status;

    @FindBy(xpath = "//div[contains(text(),'Type')]")
    WebElement type;

    @FindBy(xpath = "//span[@class='tree-label']")
    List<WebElement> allModulesIncludeProject;

    // Actions

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public void clickRequirementTab() throws InterruptedException {
        Thread.sleep(1500);
        tabRequirements.click();
        Thread.sleep(1500);
    }

    public void clickOnTheProjectName() throws InterruptedException {
        leftPanelProjectName.click();
        wait.until(ExpectedConditions.visibilityOf(buttonSave));

    }

    public void clickNewModule() throws InterruptedException {
         WaitUtils.waitFor1000Milliseconds();
        iconNewModule.click();
         WaitUtils.waitFor1000Milliseconds();

    }

    public void setModuleName(String moduleName) {
        textModuleName.clear();
        textModuleName.sendKeys(moduleName);
    }

    public void saveModule() {
        buttonSave.click();
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='notification' and normalize-space(text())='Module created successfully.']")));
    }

    public void clickDeleteModule() {
        wait.until(d -> iconDelete.getCssValue("cursor").equalsIgnoreCase("pointer"));
        iconDelete.click();
    }

//    public void clickArrowRightPointingForExpandModule(String moduleName) {
//        arrowBeforeExpandRightPointing(moduleName).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//div[@class='tree-children']") // children of the project
//        ));
//    }

    public void clickArrowRightPointingForExpandModule(String projectName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By arrow = By.xpath("//span[text()='" + projectName + "']/..//i[contains(@class,'tree-arrow')]");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(arrow));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try { Thread.sleep(300); } catch (Exception ignored) {}
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


    public void clickArrowDownPointingForCollapseModule(String moduleName) {
        arrowAfterExpandDownPointing(moduleName).click();
    }

    public void clickOnModule(String moduleName) throws InterruptedException {
        Actions a = new Actions(driver);
        try {
            a.moveToElement(leftModuleNameByName(moduleName)).perform();
            leftModuleNameByName(moduleName).click();
        } catch (Exception e) {
            WebElement ele = driver.findElement(By.xpath(
                    "//div[@class='tree-node tree-node collapsed']//span[contains(text(),'" + moduleName + "')]"));
            a.moveToElement(ele).perform();
            ele.click();
        }
    }

    public String getNewCreatedRqIdText() {
        return getNewRqIdText.getText();
    }

    public String totalCountOfAvailabelRq() {
        return totalEntriesRqCount.getText().trim();
    }

    public int extractNumber(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    public void clickProjectName() throws InterruptedException {
        clickOnTheProjectName();
    }

    public String getProjectNameText() {
        return leftPanelProjectName.getText().trim();
    }

    public List<String> getRequirementIDs() {
        List<WebElement> rows = getRequirementsFromModuleTable();
        return rows.stream().map(row -> row.findElement(By.cssSelector(".pid-col a.text-wrapper-14")).getText())
                .toList();
    }

    public List<WebElement> getRequirementsFromModuleTable() {
        WebElement tableContainer = wait
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("existingTestCasesInnerTable"))));
        return tableContainer.findElements(By.cssSelector("#existingTestCasesInnerTable .requirements-list-row"));
    }

    public boolean isRequirementVisible(String requirementId) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpath = "(//p[normalize-space()='" + requirementId + "'])[1]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getDeleteModuleAlertMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(deleteMessage));
        return deleteMessage.getText();
    }

    public void clickNoInDeleteNotification() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnNoInDeleteNotification));
        btnNoInDeleteNotification.click();
    }

    public void verifyHelpDropdown(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(clickHelp)).click();
        wait.until(ExpectedConditions.visibilityOf(helpDropdown));
        Assert.assertTrue(helpDropdown.isDisplayed(), "Help dropdown is not visible");
    }

    public void clicktoggleSidebar() throws InterruptedException {
         WaitUtils.waitFor1000Milliseconds();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(closeSideBar))).click();
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOf(openSideBar)));
        Assert.assertTrue(openSideBar.isDisplayed(), "Sidebar did not collapse");
        System.out.println("Sidebar closed successfully");
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(openSideBar))).click();
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOf(closeSideBar)));
        Assert.assertTrue(closeSideBar.isDisplayed(), "Sidebar did not expand");
        System.out.println("Sidebar opened successfully");
    }

    public boolean isTypeFieldVisible() {
        return type.isDisplayed();
    }

    public boolean isStatusFieldVisible()

    {
        return status.isDisplayed();
    }

    public boolean isPriorityFieldVisible() {
        return priority.isDisplayed();
    }

    public int getAllModulesOnly() {
        return allModulesIncludeProject.size() - 1;
    }

    public void unlinkRequirementById(String requirementId, int previousCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = "//div[@class='testlistrow']//a[normalize-space(text())='" + requirementId
                + "']/ancestor::div[@class='testlistrow']//button[contains(@class,'deleteRowButton')]";
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        deleteBtn.click();
        WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmBtn")));
        yesBtn.click();
        wait.until(driver -> getRequirementIDs().size() < previousCount);
    }

    public void clickYesBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        yesBtnConfirmationPopUp.click();

    }

    public void clickNoBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        noBtnConfirmationPopUp.click();

    }

    public void clickConfirmDelete() {
        new Actions(driver).moveToElement(buttonYesConfirmDelete);
        buttonYesConfirmDelete.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(notificationPopUp));
    }

}
