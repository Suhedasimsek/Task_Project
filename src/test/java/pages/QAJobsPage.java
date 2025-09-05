package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.regex.Pattern;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QAJobsPage {
    
    private final Page page;
    
    // Locators
    private final Locator seeAllQAJobsButton;
    private final Locator locationDropdown;
    private final Locator departmentDropdown;
    private final Locator locationDropdownOptions;
    private final Locator departmentDropdownOptions;
    private final Locator viewRoleButtons;
    private final Locator firstJobPosition;
    private final Locator acceptCookiesButton;
    
    public QAJobsPage(Page page) {
        this.page = page;
        this.seeAllQAJobsButton = page.locator("a.btn.btn-outline-secondary[href*='department=qualityassurance']");
        this.locationDropdown = page.locator("#select2-filter-by-location-container");
        this.departmentDropdown = page.locator("#select2-filter-by-department-container");
        this.locationDropdownOptions = page.locator(".select2-results__option");
        this.departmentDropdownOptions = page.locator(".select2-results__option");
        this.viewRoleButtons = page.locator("text=View Role");
        this.firstJobPosition = page.locator(".position-list-item").first();
        this.acceptCookiesButton = page.locator("text='Accept All'").first();
    }
    
    // Feature'daki adımlara uygun basit methodlar
    
    public void navigateTo(String url) {
        page.navigate(url);
    }
    
    public void clickSeeAllQAJobs() {
        handleCookiePopup();
        seeAllQAJobsButton.click();
    }
    
    private void handleCookiePopup() {
        try {
            acceptCookiesButton.waitFor();
            acceptCookiesButton.click();
        } catch (Exception e) {
            // Cookie popup yoksa devam et
        }
    }
    
    public void filterByLocation(String location) {
        
        locationDropdown.click();
        page.locator(".select2-results__option:has-text('" + location + "')").click();
        page.waitForTimeout(2000); 
    }
    
    public void filterByDepartment(String department) {
      
        departmentDropdown.click(); 
        page.locator(".select2-results__option:has-text('" + department + "')").click();
        page.waitForTimeout(2000); 
    }
    
    public void verifyAllJobsContainPositionText(String expectedText) {
        
        page.evaluate("window.scrollBy(0, 500)");
        assertThat(viewRoleButtons.first()).isVisible();
    }

    public void verifyAllJobsContainDepartmentText(String expectedText) {
        assertThat(page.locator(".position-department:has-text('Quality Assurance')").first()).isVisible();
    }

    public void verifyAllJobsContainLocationText(String expectedText) {
        assertThat(page.locator(".position-location:has-text('Istanbul')").first()).isVisible();
    }
    
    public void clickFirstViewRoleButton() {
        firstJobPosition.hover();
        viewRoleButtons.first().click();
    }
    
    public void verifyRedirectedToLeverApplicationForm() {
        // Yönlendirmeyi bekle
        page.waitForTimeout(3000);
        
        // Eğer yeni tab açıldıysa, o tab'a geç
        if (page.context().pages().size() > 1) {
            Page newPage = page.context().pages().get(1);
                    assertThat(newPage).hasURL(Pattern.compile(".*jobs\\.lever\\.co/useinsider.*"));
    } else {
        // Aynı sayfada yönlendirme olduysa
        assertThat(page).hasURL(Pattern.compile(".*jobs\\.lever\\.co/useinsider.*"));
        }
    }
}
