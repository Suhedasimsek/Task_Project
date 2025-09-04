package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;
import pages.Login_Page;
import utils.WebActions;
import utils.factory.DriverFactory;

import static org.junit.Assert.assertTrue;

public class LoginStepDefs {

    Page page = DriverFactory.getPage();
    Login_Page loginPage = new Login_Page(page);

    @Given("Tarayıcı başlatılır")
    public void tarayici_baslatilir() {
        // Browser already started by hooks
        System.out.println("Tarayıcı başlatıldı");
    }

    @When("{string} adresine gidilir")
    public void adresine_gidilir(String url) {
        page.navigate(url, new Page.NavigateOptions().setTimeout(30000));
    }

    @Then("Ana sayfa başarılı şekilde görünür olduğu doğrulanır")
    public void ana_sayfa_basarili_sekilde_gorunur_oldugu_dogrulanir() {
        assertTrue("Ana sayfa görünür değil", loginPage.isHomePageVisible());
    }

    @When("{string} butonuna tıklanır")
    public void butonuna_tiklanir(String buttonText) {
        if (buttonText.equals("Signup / Login")) {
            loginPage.clickSignupLogin();
        } else if (buttonText.equals("login")) {
            loginPage.clickLoginButton();
        }
    }

    @Then("{string} görünür olduğu doğrulanır")
    public void gorunur_oldugu_dogrulanir(String text) {
        if (text.equals("Login to your account")) {
            assertTrue("Login başlığı görünür değil", loginPage.isLoginHeaderVisible());
        } else if (text.equals("Logged in as username")) {
            assertTrue("Logged in metni görünür değil", loginPage.isLoggedInVisible());
        }
    }

    @When("Doğru email adresi ve şifre girilir")
    public void dogru_email_adresi_ve_sifre_girilir() {
        String email = WebActions.getProperty("username");
        String password = WebActions.getProperty("password");
        loginPage.enterCredentials(email, password);
    }
}
