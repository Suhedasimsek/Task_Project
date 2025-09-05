# Insider Website Test Automation

Bu proje, Insider web sitesinin test otomasyonu için geliştirilmiş, **Playwright** ve **Cucumber BDD** framework'lerini kullanan sağlam ve bakımı kolay bir test projesidir.

## 🛠️ Teknoloji Stack

- **Java 11+**
- **Playwright** - Modern web automation
- **Cucumber** - BDD (Behavior Driven Development)
- **JUnit** - Test runner
- **Maven** - Dependency management

## 📁 Proje Yapısı

```
src/test/java/
├── pages/                    # Page Object Model sınıfları
│   ├── InsiderHomePage.java  # Ana sayfa ve Careers sayfası
│   └── QAJobsPage.java       # QA işleri sayfası
├── stepdefinitions/          # Cucumber step definitions
│   ├── HomePageStepDefs.java # Ana sayfa adımları
│   └── QAJobsStepDefs.java   # QA işleri adımları
├── hooks/
│   └── Hooks.java            # Test lifecycle yönetimi
├── testrunner/
│   └── TestRunner.java       # Cucumber test runner
└── utils/
    ├── factory/
    │   └── DriverFactory.java # Playwright driver yönetimi
    ├── ConfigReader.java      # Konfigürasyon okuyucu
    └── WebActions.java        # Ortak web aksiyonları

src/test/resources/
├── features/
│   └── Insider.feature      # BDD test senaryoları
└── config/
    └── config.properties    # Test konfigürasyonu
```

## 🎯 Test Senaryoları

### 1. Company > Careers Sayfası Testi (`@company-careers`)
- Insider ana sayfasına gitme
- Ana sayfa yüklenme doğrulaması
- Company menüsüne hover yapma
- Careers linkine tıklama
- Careers sayfası yüklenme doğrulaması
- "Locations" ve "Life at Insider" bloklarının görünürlük kontrolü

### 2. QA Jobs Filtreleme Testi (`@qa-jobs`)
- QA sayfasına gitme
- "See all QA jobs" butonuna tıklama
- Lokasyon filtreleme (Istanbul, Turkiye)
- Departman filtreleme (Quality Assurance)
- İş listesi doğrulamaları (Position, Department, Location)
- "View Role" butonuna tıklama
- Lever Application form sayfasına yönlendirme kontrolü

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler
- Java 11 veya üzeri
- Maven 3.6+
- Git

### Kurulum
```bash
# Projeyi klonlayın
git clone https://github.com/Suhedasimsek/Task_Project.git
cd Task_Project

# Dependencies'leri yükleyin
mvn clean install
```

### Testleri Çalıştırma

#### Tüm testleri çalıştırma:
```bash
mvn clean test
```

#### Belirli tag ile çalıştırma:
```bash
# Company-Careers testi
mvn test -Dcucumber.filter.tags="@company-careers"

# QA Jobs testi  
mvn test -Dcucumber.filter.tags="@qa-jobs"

# Her iki testi de çalıştırma
mvn test -Dcucumber.filter.tags="@insider"
```

## 📊 Raporlama

Test çalıştıktan sonra aşağıdaki raporlar oluşturulur:

- **HTML Report**: `target/cucumber-html-report.html`
- **JSON Report**: `target/cucumber.json`
- **JUnit XML**: `target/cucumber.xml`
- **Extent Report**: `target/extent-reports/`

## ⚙️ Konfigürasyon

`src/test/resources/config/config.properties` dosyasında test ayarları:

```properties
browser=chromium
timeout=30000
headless=false
slowMo=100
```

## 🏗️ Mimari Prensipleri

### Page Object Model (POM)
- Her sayfa için ayrı sınıf
- Locator'lar ve metodlar kapsüllenmiş
- Kod tekrarı minimized

### BDD (Behavior Driven Development)
- Gherkin dilinde yazılmış senaryolar
- İş odaklı test senaryoları
- Teknik detaylardan arındırılmış

### Playwright Auto-Wait
- Otomatik element bekleme
- `Thread.sleep()` kullanımı yok
- Sağlam ve hızlı testler

### Smart Locators
- Öncelik sırası: `getByTestId()` > `getByRole()` > `getById()` > CSS/XPath
- Benzersiz ve stabil locator'lar
- Dynamic wait stratejileri

## 🔧 Geliştirme Notları

### Yeni Test Ekleme
1. `src/test/resources/features/` altına Gherkin senaryosu ekleyin
2. İhtiyaç halinde yeni Page Object oluşturun
3. Step definitions ekleyin veya güncelleyin
4. Test runner'da tag'i ekleyin

### Locator Stratejisi
```java
// En iyi
page.getByTestId("submit-button")

// İyi  
page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"))

// Kabul edilebilir
page.locator("#submit-btn")

// Son çare
page.locator("//button[contains(@class,'submit')]")
```

### Debugging
- `page.pause()` - Debugging için duraklat
- `page.screenshot()` - Screenshot al
- Browser'ı headless=false modda çalıştır

## 🐛 Bilinen Sorunlar ve Çözümler

### Select2 Dropdown İssue
Select2 kütüphanesi kullanılan dropdown'lar için `force: true` click gerekli:
```java
dropdown.click(new Locator.ClickOptions().setForce(true));
```

### Network Timeout
Yavaş internet bağlantısı için timeout'ları artırın:
```java
page.setDefaultTimeout(60000);
page.setDefaultNavigationTimeout(120000);
```

## 🤝 Katkıda Bulunma

1. Fork yapın
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Commit yapın (`git commit -m 'Add amazing feature'`)
4. Push yapın (`git push origin feature/amazing-feature`)
5. Pull Request açın

## 📝 License

Bu proje MIT lisansı altında lisanslanmıştır.

## 📞 İletişim

- **Geliştirici**: Suheda Şimşek
- **GitHub**: [@Suhedasimsek](https://github.com/Suhedasimsek)
- **Proje**: [Task_Project](https://github.com/Suhedasimsek/Task_Project)

---
*Bu proje, modern web test otomasyonu best practice'lerini kullanarak geliştirilmiştir.*
