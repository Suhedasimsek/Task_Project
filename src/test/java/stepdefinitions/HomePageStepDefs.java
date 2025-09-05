package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.InsiderHomePage;
import utils.factory.DriverFactory;

/**
 * Insider Home Page Step Definitions sınıfı
 * 
 * Bu sınıf Insider ana sayfa ve careers sayfası ile ilgili 
 * Gherkin adımlarını Java metodlarına bağlar.
 */
public class HomePageStepDefs {
    
    private final Page page;
    private final InsiderHomePage insiderHomePage;
    
    public HomePageStepDefs() {
        this.page = DriverFactory.getPage();
        this.insiderHomePage = new InsiderHomePage(page);
    }
    
    @Given("Kullanıcı {string} adresine gider")
    public void kullaniciAdresineGider(String url) {
        insiderHomePage.navigateTo(url);
    }
    
    @Then("Insider ana sayfasının açıldığı doğrulanır")
    public void insiderAnaSayfasininAcildigiDogrulanir() {
        insiderHomePage.verifyHomePageIsOpened();
    }
    
    @When("Kullanıcı navigasyon barındaki {string} menüsünü seçer")
    public void kullaniciNavigasyonBarindakiMenusunuSecer(String menuName) {
        insiderHomePage.hoverCompanyMenu();
    }
    
    @And("{string} seçeneğine tıklar")
    public void secenegineTiklar(String optionName) {
        insiderHomePage.clickCareers();
    }
    
    @Then("Careers sayfasının açıldığı doğrulanır")
    public void careersSayfasininAcildigiDogrulanir() {
        insiderHomePage.verifyCareersPageIsOpened();
    }
    
    @And("{string} bloğunun görünür olduğu doğrulanır")
    public void blogunununGorunurOlduguDogrulanir(String blockName) {
        switch (blockName) {
            case "Locations":
                insiderHomePage.verifyLocationsBlockIsVisible();
                break;
            case "Life at Insider":
                insiderHomePage.verifyLifeAtInsiderBlockIsVisible();
                break;
        }
    }
}
