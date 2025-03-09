package elements;

import org.openqa.selenium.By;

public class PageElements {
    // Login related elements
    public static final By LOGIN_BUTTON = By.id("login2");
    public static final By LOGIN_MODAL = By.id("logInModal");
    public static final By USERNAME_FIELD = By.id("loginusername");
    public static final By PASSWORD_FIELD = By.id("loginpassword");
    public static final By SUBMIT_BUTTON = By.cssSelector("#logInModal .btn-primary");
    public static final By CLOSE_MODAL_BUTTON = By.cssSelector("#logInModal .close");
    public static final By WELCOME_MESSAGE = By.id("nameofuser");
    public static final By LOGOUT_BUTTON = By.id("logout2");

    // Sign up related elements
    public static final By SIGNUP_BUTTON = By.id("signin2");
    public static final By SIGNUP_MODAL = By.id("signInModal");
    public static final By USERNAME_FIELD_SIGN_UP = By.id("sign-username");
    public static final By PASSWORD_FIELD_SIGN_UP = By.id("sign-password");
    public static final By SUBMIT_BUTTON_SIGN_UP = By.cssSelector("#signInModal .btn-primary");
    public static final By CLOSE_MODAL_BUTTON_SIGN_UP = By.cssSelector("#signInModal .close");

    // About us related elements
    public static final By ABOUT_US_BUTTON = By.xpath("//a[contains(text(), 'About us')]");
    public static final By ABOUT_US_MODAL = By.id("videoModal");

    // Contact related elements
    public static final By CONTACT_BUTTON = By.xpath("//a[contains(text(), 'Contact')]");
    public static final By CONTACT_MODAL = By.id("exampleModal");

    // Messages
    public static final String WRONG_PASSWORD_MESSAGE = "Wrong password.";
    public static final String USER_NOT_EXIST_MESSAGE = "User does not exist.";
    public static final String EMPTY_FIELDS_MESSAGE = "Please fill out Username and Password.";
    public static final String WELCOME_PREFIX = "Welcome ";

    // URLs
    public static final String BASE_URL = "https://www.demoblaze.com";
    public static final String CART_URL = BASE_URL + "/cart.html";
} 