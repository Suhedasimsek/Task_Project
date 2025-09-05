package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Insider Ana Sayfa Page Object sınıfı
 * 
 * Bu sınıf Insider ana sayfasının elemanları ve işlemleri için 
 * kapsülleme sağlar. Playwright'in auto-wait özelliklerinden faydalanır.
 */
public class InsiderHomePage {
    
    private final Page page;
    
    // Locators
    private final Locator insiderLogo;
    private final Locator companyMenu;
    private final Locator careersLink;
    private final Locator acceptCookiesButton;
    private final Locator idcMarketScapeElement;
    // Careers page locators
    private final Locator careersPageHeader;
    private final Locator locationsBlock;
    private final Locator lifeAtInsiderBlock;
    
    public InsiderHomePage(Page page) {
        this.page = page;
        this.insiderLogo = page.locator("span:has-text('Insider named a Leader in the IDC MarketScape Report 2025')");
        this.companyMenu = page.locator("a.nav-link.dropdown-toggle:has-text('Company')");
        this.careersLink = page.locator("text='Careers'");
        this.acceptCookiesButton = page.locator("text='Accept All'").first();
        this.idcMarketScapeElement = page.locator("span:has-text('Insider named a Leader in the IDC MarketScape Report')");
        // Careers page locators
        this.careersPageHeader = page.locator("h1:has-text('Ready to disrupt')");
        this.locationsBlock = page.locator("h3:has-text('Our Locations')").first();
        this.lifeAtInsiderBlock = page.locator("h2:has-text('Life at Insider')").first();
    }
    
    /**
     * Verilen URL'ye navigate eder
     */
    public void navigateTo(String url) {
        page.navigate(url);
        //handleCookiePopup();
    }
    
    /**
     * Cookie popup'ı varsa kabul eder
     */
    private void handleCookiePopup() {
        try {
            if (acceptCookiesButton.isVisible()) {
                acceptCookiesButton.click();
            }
        } catch (Exception e) {
            // Cookie popup yoksa devam et
        }
    }
    
    /**
     * Ana sayfanın başarıyla yüklendiğini doğrular
     */
    public void verifyHomePageIsOpened() {
        assertThat(idcMarketScapeElement).isVisible();
    }
    
    /**
     * Company menüsüne hover yapar
     */
    public void hoverCompanyMenu() {
        companyMenu.hover();
    }
    
    /**
     * Careers linkine tıklar
     */
    public void clickCareers() {
        careersLink.click();
    }
    
    /**
     * Careers sayfasının başarıyla yüklendiğini doğrular
     */
    public void verifyCareersPageIsOpened() {
        assertThat(careersPageHeader).isVisible();
    }

    /**
     * Locations bloğunun görünür olduğunu doğrular
     */
    public void verifyLocationsBlockIsVisible() {
        // Sayfayı scroll yap
        page.evaluate("window.scrollBy(0, 500)");
        assertThat(locationsBlock).isVisible();
    }

    /**
     * Life at Insider bloğunun görünür olduğunu doğrular
     */
    public void verifyLifeAtInsiderBlockIsVisible() {
        // Life at Insider elementine doğrudan scroll yap
        lifeAtInsiderBlock.scrollIntoViewIfNeeded();
        assertThat(lifeAtInsiderBlock).isVisible();
    }
}
