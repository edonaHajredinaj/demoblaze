package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DriverManager;
import pages.HomePage;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private WebDriver driver = DriverManager.getDriver();
    private HomePage homePage = new HomePage(driver);

    @After("@login")
    public void logoutAfterLoginTest() {
        try {
            if (homePage.isUserLoggedIn()) {
                homePage.logout();
                logger.info("Successfully logged out after login test");
            } else {
                logger.info("No logout needed - user was not logged in");
            }
        } catch (Exception e) {
            logger.error("Failed to handle logout after login test: {}", e.getMessage());
        }
    }

    @After
    public void takeScreenshotOnFailure(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                // Take screenshot if scenario fails
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                
                // Create screenshots directory if it doesn't exist
                java.nio.file.Path screenshotsDir = java.nio.file.Paths.get("target/screenshots");
                if (!java.nio.file.Files.exists(screenshotsDir)) {
                    java.nio.file.Files.createDirectories(screenshotsDir);
                }
                
                // Save screenshot with scenario name and timestamp
                String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String screenshotName = "scenario_failure_" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
                java.nio.file.Path screenshotPath = screenshotsDir.resolve(screenshotName);
                java.nio.file.Files.write(screenshotPath, screenshot);
                
                // Attach screenshot to Cucumber report
                scenario.attach(screenshot, "image/png", "Screenshot of failure");
                
                logger.error("Scenario failed: {} - Screenshot saved as: {}", scenario.getName(), screenshotPath);
            }
        } catch (Exception e) {
            logger.error("Error taking screenshot: {}", e.getMessage());
        }
    }
} 