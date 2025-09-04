package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.WebActions;

public class Login_Page {
    
    // Locators
    private final Locator homePageLogo;
    private final Locator signupLoginButton;
    private final Locator loginHeader;
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator loggedInAsText;

    public Login_Page(Page page) {
        this.homePageLogo = page.locator("img[alt='Website for automation practice']");
        this.signupLoginButton = page.locator("a:has-text('Signup / Login')");
        this.loginHeader = page.locator("h2:has-text('Login to your account')");
        this.emailInput = page.locator("input[data-qa='login-email']");
        this.passwordInput = page.locator("input[data-qa='login-password']");
        this.loginButton = page.locator("button[data-qa='login-button']");
        this.loggedInAsText = page.locator("a:has-text('Logged in as')");
    }

    public boolean isHomePageVisible() {
        return WebActions.waitUntilElementDisplayed(homePageLogo, 5);
    }

    public void clickSignupLogin() {
        WebActions.waitUntilElementDisplayed(signupLoginButton, 3);
        signupLoginButton.click();
    }

    public boolean isLoginHeaderVisible() {
        return WebActions.waitUntilElementDisplayed(loginHeader, 5);
    }

    public void enterCredentials(String email, String password) {
        emailInput.fill(email);
        passwordInput.fill(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isLoggedInVisible() {
        return WebActions.waitUntilElementDisplayed(loggedInAsText, 5);
    }

    public String getLoggedInUserName() {
        return loggedInAsText.locator("b").innerText().trim();
    }
}
