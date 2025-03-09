package pages;

import elements.PageElements;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);
    private static final int DEFAULT_TIMEOUT_SECONDS = 1;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void clickWithJS(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        logger.info("Element clicked using JS");
    }

    protected void clearAndType(By locator, String text) {
        WebElement element = waitForClickable(locator);
        element.clear();
        element.click();
        element.sendKeys(text);
    }

//   protected void refreshPage() {
//       driver.navigate().refresh();
//       waitForPageLoad();
//   }

   protected void waitForPageLoad() {
       wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
   }

    public String getAlertText() {
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        logger.info("Alert text retrieved: {}", alertText);
        return alertText;
    }

    public void acceptAlert() {
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        logger.info("Alert accepted");
    }

    protected boolean isElementVisible(By locator) {
        return driver.findElement(PageElements.LOGIN_MODAL).isDisplayed();
    }

    protected void takeScreenshot(String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            
            java.nio.file.Path screenshotsDir = java.nio.file.Paths.get("target/screenshots");
            if (!java.nio.file.Files.exists(screenshotsDir)) {
                java.nio.file.Files.createDirectories(screenshotsDir);
            }
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            java.nio.file.Path screenshotPath = screenshotsDir.resolve(name + "_" + timestamp + ".png");
            java.nio.file.Files.write(screenshotPath, screenshot);
            
            logger.info("Screenshot saved: {}", screenshotPath);
        } catch (Exception e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
        }
    }

    public void openLoginPopup() {
        WebElement loginButton = waitForClickable(PageElements.LOGIN_BUTTON);
        if (loginButton.isDisplayed()) {
            loginButton.click();
            logger.warn("Regular click");
        } else {
            clickWithJS(loginButton);
            logger.warn("Clicked with JS");
        }
        waitForVisible(PageElements.LOGIN_MODAL);
        logger.info("Login popup opened successfully");
    }


    public void enterUsername(String username) {
        clearAndType(PageElements.USERNAME_FIELD, username);
    }

    public void enterPassword(String password) {
        clearAndType(PageElements.PASSWORD_FIELD, password);
    }

    public void clickLogin() {
        WebElement submitBtn = waitForClickable(PageElements.SUBMIT_BUTTON);
        submitBtn.click();
    }

    public boolean isUserLoggedIn() {
        boolean loggedIn = isElementVisible(PageElements.WELCOME_MESSAGE);
        if (!loggedIn) {
            takeScreenshot("login_state_check_failure");
        }
        return loggedIn;
    }

    public String getWelcomeMessage() {
        WebElement welcomeElement = waitForVisible(PageElements.WELCOME_MESSAGE);
        String message = welcomeElement.getText();
        logger.info("Welcome message retrieved: {}", message);
        return message;
    }

    public boolean isLoginModalVisible() {
        return isElementVisible(PageElements.LOGIN_MODAL);
    }

    public void logout() {
        WebElement logoutButton = waitForClickable(PageElements.LOGOUT_BUTTON);

        if (logoutButton.isDisplayed()) {
            logoutButton.click();
            logger.info("Regular click to log out with JS");
        } else {
            clickWithJS(logoutButton);
            logger.info("User click log out with JS");
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(PageElements.WELCOME_MESSAGE));
        logger.info("User logged out successfully");
    }

    public void closeLoginModal() {
        WebElement closeButton = waitForClickable(PageElements.CLOSE_MODAL_BUTTON);

        if (closeButton.isDisplayed()) {
            closeButton.click();
            logger.info("Regular click to close Login modal.");
        } else {
            clickWithJS(closeButton);
            logger.warn("Closed login modal with JS.");
        }
    }

    public boolean isOnHomePage() {
        try {
            String currentUrl = driver.getCurrentUrl();

            currentUrl = currentUrl.replaceAll("/$", "");
            String baseUrl = PageElements.BASE_URL.replaceAll("/$", "");
            
            return currentUrl.equals(baseUrl);
        } catch (Exception e) {
            logger.error("Failed to check if on home page: {}", e.getMessage());
            takeScreenshot("homepage_check_failure");
            return false;
        }
    }

    public boolean isOnCartPage() {
        try {
            String currentUrl = driver.getCurrentUrl();

            currentUrl = currentUrl.replaceAll("/$", "");
            String cartUrl = PageElements.CART_URL.replaceAll("/$", "");
            
            return currentUrl.equals(cartUrl);
        } catch (Exception e) {
            logger.error("Failed to check if on cart page: {}", e.getMessage());
            takeScreenshot("cartpage_check_failure");
            return false;
        }
    }

    public void openSignupModal() {
        WebElement signupButton = waitForClickable(PageElements.SIGNUP_BUTTON);
        if (signupButton.isDisplayed()) {
            signupButton.click();
            logger.info("Regular click to open signup modal");
        } else {
            clickWithJS(signupButton);
            logger.warn("Clicked signup with JS");
        }
        waitForVisible(PageElements.SIGNUP_MODAL);
        logger.info("Signup popup opened successfully");
    }

    public void openAboutUsModal() {
        WebElement aboutUsButton = waitForClickable(PageElements.ABOUT_US_BUTTON);
        if (aboutUsButton.isDisplayed()) {
            aboutUsButton.click();
            logger.info("Regular click to open about us modal");
        } else {
            clickWithJS(aboutUsButton);
            logger.warn("Clicked about us with JS");
        }
        waitForVisible(PageElements.ABOUT_US_MODAL);
        logger.info("About us popup opened successfully");
    }

    public void openContactModal() {
        WebElement contactButton = waitForClickable(PageElements.CONTACT_BUTTON);
        if (contactButton.isDisplayed()) {
            contactButton.click();
            logger.info("Regular click to open contact modal");
        } else {
            clickWithJS(contactButton);
            logger.warn("Clicked contact with JS");
        }
        waitForVisible(PageElements.CONTACT_MODAL);
        logger.info("Contact popup opened successfully");
    }
}
