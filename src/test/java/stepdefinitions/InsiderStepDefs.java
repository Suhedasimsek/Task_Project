package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CareersPage;
import pages.InsiderHomePage;
import pages.QAJobsPage;
import utils.factory.DriverFactory;

/**
 * Insider Step Definitions sınıfı
 * 
 * Bu sınıf Gherkin adımlarını Java metodlarına bağlar.
 * Tüm Playwright etkileşimleri Page Object sınıfları üzerinden yapılır.
 */
public class InsiderStepDefs {
    
    private final Page page;
    private final InsiderHomePage insiderHomePage;
    private final CareersPage careersPage;
    private final QAJobsPage qaJobsPage;
    
    public InsiderStepDefs() {
        this.page = DriverFactory.getPage();
        this.insiderHomePage = new InsiderHomePage(page);
        this.careersPage = new CareersPage(page);
        this.qaJobsPage = new QAJobsPage(page);
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
        careersPage.verifyCareersPageIsOpened();
    }
    
    @And("{string} bloğunun görünür olduğu doğrulanır")
    public void blogunununGorunurOlduguDogrulanir(String blockName) {
        switch (blockName) {
            case "Locations":
                careersPage.verifyLocationsBlockIsVisible();
                break;
            case "Life at Insider":
                careersPage.verifyLifeAtInsiderBlockIsVisible();
                break;
        }
    }
    
    @Given("QA sayfasına gidilir {string}")
    public void qaJobs_sayfasinaGidilir(String url) {
        qaJobsPage.navigateTo(url);
    }
    
    @When("{string} butonuna tıklar")
    public void butonunaTiklar(String buttonName) {
        qaJobsPage.clickSeeAllQAJobs();
    }
    
    @And("Lokasyon olarak {string} filtreler")
    public void lokasyonOlarakFiltreler(String location) {
        qaJobsPage.filterByLocation(location);
    }
    
    @And("Departman olarak {string} filtreler")
    public void departmanOlarakFiltreler(String department) {
        qaJobsPage.filterByDepartment(department);
    }
    
    @Then("Tüm işlerin Position alanında {string} içerdiği doğrulanır")
    public void tumIslerinPositionAlanindaIcerdigiDogrulanir(String expectedText) {
        qaJobsPage.verifyAllJobsContainPositionText(expectedText);
    }
    
    @And("Tüm işlerin Department alanında {string} içerdiği doğrulanır")
    public void tumIslerinDepartmentAlanindaIcerdigiDogrulanir(String expectedText) {
        qaJobsPage.verifyAllJobsContainDepartmentText(expectedText);
    }
    
    @And("Tüm işlerin Location alanında {string} içerdiği doğrulanır")
    public void tumIslerinLocationAlanindaIcerdigiDogrulanir(String expectedText) {
        qaJobsPage.verifyAllJobsContainLocationText(expectedText);
    }
    
    @When("İlk işteki {string} butonuna tıklar")
    public void ilkIstekiButonunaTiklar(String buttonName) {
        qaJobsPage.clickFirstViewRoleButton();
    }
    
    @Then("Lever Application form sayfasına yönlendirildiği doğrulanır")
    public void leverApplicationFormSayfasinaYonlendirildigiDogrulanir() {
        qaJobsPage.verifyRedirectedToLeverApplicationForm();
    }
}
