package hooks;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.WebActions;
import utils.factory.DriverFactory;

import java.nio.file.Paths;

/**
 * Cucumber Hooks sınıfı - Framework altyapısı
 * 
 * Bu sınıf @Before ve @After hook'ları ile her senaryo için tarayıcıyı 
 * temiz bir şekilde başlatır ve kapatır.
 * 
 * Ana sorumluluklar:
 * - Browser yaşam döngüsü yönetimi
 * - Test başlamadan önce browser başlatma
 * - Test bitiminde cleanup işlemleri
 * - Başarısız testlerde screenshot alma ve trace oluşturma
 */
public class Hooks {
    private DriverFactory driverFactory;
    private Page page;

    /**
     * Her test senaryosu öncesinde çalışır
     * Config dosyasından browser tipini okur ve browser'ı başlatır
     */
    @Before
    public void launchBrowser() {
        System.out.println("Tarayıcı başlatıldı");
        String browserName = WebActions.getProperty("browser");
        driverFactory = new DriverFactory();
        page = driverFactory.initDriver(browserName);
        
        // Implicitly wait - her locator için 30 saniye bekle
        page.setDefaultTimeout(30000);
        page.setDefaultNavigationTimeout(60000);
    }

    /**
     * Test bitiminde browser'ı kapatır
     * Order=0 ile en son çalışmasını sağlar
     */
    @After(order = 0)
    public void quitBrowser() {
        if (page != null) {
            page.close();
        }
    }

    /**
     * Başarısız testlerde screenshot ve trace alır
     * Order=1 ile browser kapanmadan önce çalışmasını sağlar
     */
    @After(order = 1)
    public void takeScreenshotAndTrace(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = page.screenshot();
            scenario.attach(sourcePath, "image/png", screenshotName);
            
            // Trace dosyasını kaydet
            DriverFactory.context.tracing().stop(
                new Tracing.StopOptions().setPath(Paths.get("target/" + screenshotName + ".zip"))
            );
        }
    }
}
