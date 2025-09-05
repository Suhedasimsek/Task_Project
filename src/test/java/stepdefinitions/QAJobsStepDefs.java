package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.QAJobsPage;
import utils.factory.DriverFactory;

/**
 * QA Jobs Step Definitions sınıfı
 * 
 * Bu sınıf QA jobs sayfası ile ilgili 
 * Gherkin adımlarını Java metodlarına bağlar.
 */
public class QAJobsStepDefs {
    
    private final Page page;
    private final QAJobsPage qaJobsPage;
    
    public QAJobsStepDefs() {
        this.page = DriverFactory.getPage();
        this.qaJobsPage = new QAJobsPage(page);
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
