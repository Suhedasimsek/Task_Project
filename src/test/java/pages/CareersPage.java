package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Insider Careers Sayfa Page Object sınıfı
 * 
 * Bu sınıf Insider careers sayfasının elemanları ve işlemleri için 
 * kapsülleme sağlar. Playwright'in auto-wait özelliklerinden faydalanır.
 */
public class CareersPage {
    
    private final Page page;
    
    // Locators
    private final Locator careersPageHeader;
    private final Locator locationsBlock;
    private final Locator teamsBlock;
    private final Locator lifeAtInsiderBlock;
    
    public CareersPage(Page page) {
        this.page = page;
        this.careersPageHeader = page.locator("h1:has-text('Ready to disrupt')");
        this.locationsBlock = page.locator("h3:has-text('Our Locations')").first();
        this.teamsBlock = page.locator("h3:has-text('Find your calling')").first();
        this.lifeAtInsiderBlock = page.locator("h2:has-text('Life at Insider')").first();
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
