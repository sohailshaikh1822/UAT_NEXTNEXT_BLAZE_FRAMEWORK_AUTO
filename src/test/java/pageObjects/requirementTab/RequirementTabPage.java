package pageObjects.requirementTab;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.BasePage;
import java.util.List;
import utils.WaitUtils;

import java.time.Duration;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.Select;

public class RequirementTabPage extends BasePage {
    public RequirementTabPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    @FindBy(xpath = "//span[@id='requirements']")
    WebElement tabRequirements;

    @FindBy(xpath = "//div[normalize-space()='ADD TESTCASE']")
    WebElement buttonAddTestCase;

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

    @FindBy(xpath = "//i[@class='fa-solid tree-arrow fa-caret-right']")
    List<WebElement> epicArrowRight;

    @FindBy(xpath = "//i[@class='fa-solid tree-arrow fa-caret-down']")
    List<WebElement> epicArrowDown;

    @FindBy(xpath = "//div[@class='top-nav-bar']")
    WebElement getnotPopUp;


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

    @FindBy(xpath = "(//i[@class='fa-solid fa-download'])[1]")
    List<WebElement> downloadAttachement;


    // Actions

    public void clickEpicDropdown() {
        if (!epicArrowRight.isEmpty()) {
            WebElement arrow = epicArrowRight.get(0);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", arrow);
            js.executeScript("arguments[0].click();", arrow);
        }
        // If arrow-down is present, Epic is already expanded
    }


    public boolean isAddTestCaseButtonVisible1() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOf(buttonAddTestCase));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public void clickRequirementTab() throws InterruptedException {
        WaitUtils.waitFor1000Milliseconds();
        ;
        tabRequirements.click();
        WaitUtils.waitFor1000Milliseconds();
        ;
    }

    public void clickDownloadAttachementButton(int index) {
        if (index < downloadAttachement.size()) {
            WebElement downloadIcon = downloadAttachement.get(index);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", downloadIcon);
            downloadIcon.click();
        } else {
            System.out.println("Invalid index: " + index);
        }
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

    public void clickArrowRightPointingForExpandModule(String moduleName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // //span[text()='STG- SPARK Modernization']//preceding-sibling::span[2]//i (
        By arrow = By.xpath("//span[text()='" + moduleName + "']//preceding-sibling::span[2]//i");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(arrow));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            Thread.sleep(300);
        } catch (Exception ignored) {
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickDropdownToSelectProject(String projectName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // //span[text()='STG- SPARK Modernization']//preceding-sibling::span[2]//i (
        By arrow = By.xpath("//option[text()='" + projectName + "']/parent::select");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(arrow));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            Thread.sleep(300);
        } catch (Exception ignored) {
        }
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String requirementXpath = "//p[normalize-space()='" + requirementId + "']";
        By requirementLocator = By.xpath(requirementXpath);

        By nextPageBtn = By.xpath("//img[@alt='Next' and contains(@class,'active')]");
        By loadingSpinner = By.id("loading-spinner");

        try {
            while (true) {
                if (!driver.findElements(requirementLocator).isEmpty()) {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(requirementLocator));
                    return true;
                }

                if (driver.findElements(nextPageBtn).isEmpty()) {
                    return false;
                }
                WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextPageBtn));
                nextBtn.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
            }
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

    public boolean isStatusFieldVisible() {
        return status.isDisplayed();
    }

    public boolean isPriorityFieldVisible() {
        return priority.isDisplayed();
    }

    public int getAllModulesOnly() {
        return allModulesIncludeProject.size() - 1;
    }

//    public void unlinkRequirementById(String requirementId, int previousCount) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        String xpath = "//div[@class='testlistrow']//a[normalize-space(text())='" + requirementId
//                + "']/ancestor::div[@class='testlistrow']//button[contains(@class,'deleteRowButton')]";
//        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//        deleteBtn.click();
//        WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmBtn")));
//        yesBtn.click();
//        wait.until(driver -> getRequirementIDs().size() < previousCount);
//    }

    public void unlinkRequirementById(String requirementId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Locate the row using correct DOM
        By rowLocator = By.xpath(
                "//div[contains(@class,'requirements-list-row')]" +
                        "[.//a[contains(@class,'text-wrapper-14') and normalize-space()='" + requirementId + "']]"
        );

        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator));

        // Hover to reveal delete/unlink button
        new Actions(driver).moveToElement(row).perform();

        // Locate delete/unlink button inside the row
        By deleteBtnLocator = By.xpath(
                ".//button[contains(@class,'delete') or contains(@class,'unlink')]"
        );

        WebElement deleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(row.findElement(deleteBtnLocator))
        );

        deleteBtn.click();

        // Confirm unlink
        WebElement yesBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("confirmBtn"))
        );
        yesBtn.click();
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

    //Notification
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

    public void verifyDeleteNotification(String entityId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By notificationItems = By.xpath("//div[contains(@class,'notification-item')]");
        By notificationText = By.xpath(".//span[contains(@class,'notif-text')]");
        String deleterName = getLoggedInUserName();

        String expectedRegex =
                "'" + entityId + "' deleted by "
                        + deleterName.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {

                WebElement bell = wait.until(
                        ExpectedConditions.elementToBeClickable(notificationBell));
                js.executeScript("arguments[0].click();", bell);

                WebElement body = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(notificationBody));

                List<WebElement> items = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(notificationItems));

                for (WebElement item : items) {

                    String text = item.findElement(notificationText).getText().trim();

                    if (text.contains(entityId) && text.contains("deleted by")) {

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Delete notification format mismatch.\nExpected: "
                                            + expectedRegex + "\nActual: " + text);
                        }

                        System.out.println("Delete notification verified successfully: " + text);
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

        throw new AssertionError(" Delete notification not found for: " + entityId);
    }


    public String handleToastNotification() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String toastMessage = " ";

        try {
            By toastLocator = By.xpath("//div[@class='toast-body']");

            WebElement toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(toastLocator)
            );

            toastMessage = toast.getText();
            System.out.println("Toast Message: " + toastMessage);

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(toastLocator)
            );

        } catch (TimeoutException e) {
            System.out.println("Toast notification not found");
        }

        return toastMessage;
    }


    public void verifyCreationNotification(String entityId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By notificationItems = By.xpath("//div[contains(@class,'notification-item')]");
        By notificationText = By.xpath(".//span[contains(@class,'notif-text')]");
        String deleterName = getLoggedInUserName();

        String expectedRegex =
                "'" + entityId + "' created by "
                        + deleterName.replace(" ", "\\s+")
                        + "\\.";

        long endTime = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < endTime) {

            try {

                WebElement bell = wait.until(
                        ExpectedConditions.elementToBeClickable(notificationBell));
                js.executeScript("arguments[0].click();", bell);

                WebElement body = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(notificationBody));

                List<WebElement> items = wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(notificationItems));

                for (WebElement item : items) {

                    String text = item.findElement(notificationText).getText().trim();

                    if (text.contains(entityId) && text.contains("created by")) {

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Create notification format mismatch.\nExpected: "
                                            + expectedRegex + "\nActual: " + text);
                        }

                        System.out.println("Create notification verified successfully: " + text);
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

        throw new AssertionError(" Create notification not found for: " + entityId);
    }

    public void verifyRequirementCreationNotification(String rqId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String creatorName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'" + rqId + "' created by "
                        + creatorName.replace(" ", "\\s+")
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

                int limit = Math.min(10, notifications.size());

                for (int i = 0; i < limit; i++) {
                    String text = notifications.get(i).getText().trim();

                    if (text.contains(rqId) && text.contains("created by")) {

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Requirement creation notification format mismatch.\nExpected: "
                                            + expectedRegex +
                                            "\nActual: " + text
                            );
                        }

                        System.out.println(
                                "Requirement creation notification verified successfully: " + text
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
                "Requirement creation notification not found for Requirement: " + rqId
        );
    }

    public void verifyRequirementUpdateNotification(String rqId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String updaterName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'" + rqId + "' updated by "
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

                int limit = Math.min(10, notifications.size());

                for (int i = 0; i < limit; i++) {
                    String text = notifications.get(i).getText().trim();

                    if (text.contains(rqId) && text.contains("updated by")) {

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Requirement update notification format mismatch.\nExpected: "
                                            + expectedRegex +
                                            "\nActual: " + text
                            );
                        }

                        System.out.println(
                                "Requirement update notification verified successfully: " + text
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
                "Requirement update notification not found for Requirement: " + rqId
        );
    }


    public void DeleteRequirementById(String rqId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By lastPageBtn = By.xpath("//img[@alt='Last Page' and not(contains(@class,'disabled'))]");

        if (!driver.findElements(lastPageBtn).isEmpty()) {
            WebElement lastPage = driver.findElement(lastPageBtn);
            js.executeScript("arguments[0].click();", lastPage);

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.id("loading-spinner")
            ));
        }

        By requirementRow = By.xpath(
                "//div[@id='existingRequirementsTable']//div[contains(@class,'requirements-list-row')]" +
                        "[.//a[normalize-space()='" + rqId + "']]"
        );

        WebElement row = wait.until(
                ExpectedConditions.presenceOfElementLocated(requirementRow)
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", row
        );

        js.executeScript("window.scrollBy(0,-150);");

        WebElement deleteButton = row.findElement(
                By.xpath(".//button[contains(@class,'deleteRowButton')]")
        );

        js.executeScript("arguments[0].click();", deleteButton);

        clickConfirmDelete();

        wait.until(ExpectedConditions.invisibilityOf(row));
    }

    public String getModuleID() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By moduleIdLocator = By.xpath("//div[contains(@class,'text-3')]");

        WebElement moduleIdElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(moduleIdLocator)
        );

        String moduleId = moduleIdElement.getText().trim();

        if (moduleId.isEmpty()) {
            throw new AssertionError("Module ID is empty");
        }

        System.out.println("Captured Module ID: " + moduleId);
        return moduleId;
    }

    public void verifyModuleCreationNotification(String moduleName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String creatorName = getLoggedInUserName();

        By notificationBell = By.xpath("//i[contains(@class,'fa-bell')]");
        By notificationBody = By.xpath("//div[contains(@class,'notification-body')]");
        By allNotifications = By.xpath(
                "//div[contains(@class,'notification-item')]//span[contains(@class,'notif-text')]"
        );

        String expectedRegex =
                "'" + moduleName + "' created by "
                        + creatorName.replace(" ", "\\s+")
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

                for (WebElement element : notifications) {
                    String text = element.getText().trim();

                    if (text.contains(moduleName) && text.contains("created by")) {

                        if (!text.matches(expectedRegex)) {
                            throw new AssertionError(
                                    "Module creation notification format mismatch.\nExpected: "
                                            + expectedRegex +
                                            "\nActual: " + text
                            );
                        }

                        System.out.println(
                                "Module creation notification verified successfully: " + text
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
                "Created Module notification not found for module: " + moduleName
        );
    }

    public void clickEpic() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        By epicLocator = By.xpath("//span[@title='Epic']");

        WebElement epicElement = wait.until(
                ExpectedConditions.elementToBeClickable(epicLocator)
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", epicElement);
        js.executeScript("arguments[0].click();", epicElement);
    }

  //Recycle Bin

    By recycleBinButton = By.cssSelector("i.fa-dumpster[title='Recycle Bin']");
    By recycleBinPopup = By.cssSelector("div.recycle-bin-popup");
    By recycleBinHeader = By.xpath("//div[@class='rb-topbar' and contains(text(),'RECYCLE BIN')]");

    By menuDropdown = By.xpath("//label[text()='Menu']/following-sibling::select");
    By objectDropdown = By.xpath("//label[text()='Object(s)']/following-sibling::select");

    By itemCounter = By.cssSelector("span.rb-count");
    By tableRows = By.cssSelector(".rb-table tbody tr");
    By objectDropdown1 = By.xpath("//label[text()='Object(s)']/following-sibling::select");


    public void selectObjectFromDropdown(String visibleText) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(objectDropdown)
        );

        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableRows));
    }




    public void clickRecycleBinButton() {
        wait.until(ExpectedConditions.elementToBeClickable(recycleBinButton)).click();
    }

    public void verifyRecycleBinPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(recycleBinPopup));
    }


    public void verifyHeaderSectionDisplayed() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(recycleBinHeader));
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuDropdown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(objectDropdown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemCounter));
    }


    public void verifyItemCounterDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemCounter));
    }

    public void verifyAllFilterDisplayedCorrectly() {

        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows)
        );

        if (rows.isEmpty()) {
            throw new AssertionError("Recycle Bin list is empty for ALL filter");
        }

        for (WebElement row : rows) {

            String text = row.getText().toLowerCase();

            if (!text.contains("module") && !text.contains("requirement")) {
                throw new AssertionError("Unexpected item type found in ALL filter");
            }
        }

        System.out.println("ALL filter displayed valid data successfully");
    }


    public List<String> getObjectDropdownOptions() {

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(objectDropdown)
        );

        Select select = new Select(dropdown);

        return select.getOptions()
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }
    public void verifyOnlyRequirementsDisplayed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(driver -> {
            List<WebElement> rows = driver.findElements(tableRows);

            if (rows.size() == 0)
                return false;

            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));

                if (cols.size() > 3) {
                    String typeValue = cols.get(3).getText().trim();
                    if (!typeValue.equalsIgnoreCase("Requirement")) {
                        return false;
                    }
                }
            }
            return true;
        });

        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));

            if (cols.size() > 3) {
                String typeValue = cols.get(3).getText().trim();

                if (!typeValue.equalsIgnoreCase("Requirement")) {
                    throw new AssertionError(
                            "Non-requirement record found. Actual Type: " + typeValue
                    );
                }
            }
        }
    }



    public List<String> getRecycleBinColumnHeaders() {

        List<WebElement> headers = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".rb-table thead th")
                )
        );

        return headers.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())   // removes empty checkbox column
                .map(String::toLowerCase)
                .toList();
    }

    public void verifyRecycleBinGridData() {

        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".rb-table tbody tr")
                )
        );

        for (WebElement row : rows) {

            List<WebElement> columns = row.findElements(By.tagName("td"));

            Assert.assertFalse(columns.get(1).getText().trim().isEmpty(), "ID is empty");
            Assert.assertFalse(columns.get(2).getText().trim().isEmpty(), "Name is empty");
            Assert.assertFalse(columns.get(3).getText().trim().isEmpty(), "Type is empty");
            Assert.assertFalse(columns.get(4).getText().trim().isEmpty(), "Deleted By is empty");
            Assert.assertFalse(columns.get(5).getText().trim().isEmpty(), "Deleted Date is empty");
        }
    }
    public void verifyOnlyOneItemSelectable() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By radioLocator = By.cssSelector(".rb-table tbody input[type='radio']");

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(radioLocator, 1));

        List<WebElement> radioButtons = driver.findElements(radioLocator);

        Assert.assertTrue(radioButtons.size() >= 2,
                "Not enough records to perform selection test");

        radioButtons.get(0).click();

        wait.until(ExpectedConditions.elementToBeSelected(radioButtons.get(0)));

        Assert.assertTrue(radioButtons.get(0).isSelected(),
                "First radio not selected");

        radioButtons.get(1).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(radioLocator));
        radioButtons = driver.findElements(radioLocator);

        Assert.assertTrue(radioButtons.get(1).isSelected(),
                "Second radio not selected");

        Assert.assertFalse(radioButtons.get(0).isSelected(),
                "First radio should be automatically deselected");

        long selectedCount = radioButtons.stream()
                .filter(WebElement::isSelected)
                .count();

        Assert.assertEquals(selectedCount, 1,
                "More than one radio button is selected");

        System.out.println("Single selection validation passed successfully.");
    }

}