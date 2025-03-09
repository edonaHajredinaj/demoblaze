package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import utils.DriverManager;

public class LoginSteps {
    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    private final WebDriver driver;
    private final HomePage homePage;

    public LoginSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
    }

    @Given("the user opens the website")
    public void theUserOpensTheWebsite() {
        driver.get("https://www.demoblaze.com/");
    }

    @Given("the user is on the {string} page")
    public void theUserIsOnThePage(String pageName) {
        switch(pageName.toLowerCase()) {
            case "home":
                Assert.assertTrue("User is not on Home page", homePage.isOnHomePage());
                break;
            case "cart":
                Assert.assertTrue("User is not on Cart page", homePage.isOnCartPage());
                break;
            default:
                throw new IllegalArgumentException("Unknown page: " + pageName);
        }
    }

    @When("the user clicks on the {string} text")
    public void theUserOpensLoginModal(String text) {
        homePage.openLoginPopup();
    }

    @And("the user enters the {string} username")
    public void theUserEntersUsername(String username) {
        homePage.enterUsername(username);
    }

    @And("the user enters the {string} password")
    public void theUserEntersPassword(String password) {
        homePage.enterPassword(password);
    }

    @And("the user enters an incorrect {string}")
    public void theUserEntersIncorrectPassword(String password) {
        homePage.enterPassword(password);
    }

    @And("the user leaves the username field empty")
    public void theUserLeavesUsernameFieldEmpty() {
        homePage.enterUsername("");
    }

    @And("the user leaves the password field empty")
    public void theUserLeavesPasswordFieldEmpty() {
        homePage.enterPassword("");
    }

    @And("the user clicks the {string} button")
    public void theUserClicksLoginButton(String button) {
        homePage.clickLogin();
    }

    @Then("the user should see welcome {string}")
    public void theUserShouldSeeWelcomeMessage(String expectedMessage) {
        String actualMessage = homePage.getWelcomeMessage();
        Assert.assertEquals(
                String.format("Expected message '%s' but got '%s'", expectedMessage, actualMessage),
                expectedMessage,
                actualMessage
        );
    }

    @Then("the user should see {string} on alert popup")
    public void theUserShouldSeeErrorOnAlertPopup(String expectedError) {
        String actualError = homePage.getAlertText();
        Assert.assertEquals(
            String.format("Expected error message '%s' but got '%s'", expectedError, actualError),
            expectedError,
            actualError
        );
        homePage.acceptAlert();
        logger.info("Alert with message '{}' was accepted", actualError);
    }

    @And("the user should remain on the login popup")
    public void theUserShouldRemainOnTheLoginPopup() {
        Assert.assertTrue("Login popup should be visible after alert", homePage.isLoginModalVisible());
    }

    @And("the user clicks the {string} button on the login modal")
    public void theUserClosesLoginModal(String button) {
        homePage.closeLoginModal();
    }

    @And("the user logs out")
    public void theUserLogsOut() {
        homePage.logout();
    }
}
