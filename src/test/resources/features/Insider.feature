@insider
Feature: Insider Website Test Senaryoları

  @company-careers
  Scenario: Insider ana sayfasının açılıp Company > Careers sayfasına geçiş testi
    Given Kullanıcı "https://useinsider.com/" adresine gider
    Then Insider ana sayfasının açıldığı doğrulanır
    When Kullanıcı navigasyon barındaki "Company" menüsünü seçer
    And "Careers" seçeneğine tıklar
    Then Careers sayfasının açıldığı doğrulanır
    And "Locations" bloğunun görünür olduğu doğrulanır
    And "Life at Insider" bloğunun görünür olduğu doğrulanır

  @qa-jobs
  Scenario: QA işleri için filtreleme ve iş listesi kontrolü
    Given QA sayfasına gidilir "https://useinsider.com/careers/quality-assurance/"
    When "See all QA jobs" butonuna tıklar
    And Lokasyon olarak "Istanbul, Turkiye" filtreler
    And Departman olarak "Quality Assurance" filtreler
    Then Tüm işlerin Position alanında "Quality Assurance" içerdiği doğrulanır
    And Tüm işlerin Department alanında "Quality Assurance" içerdiği doğrulanır
    And Tüm işlerin Location alanında "Istanbul, Turkiye" içerdiği doğrulanır
    When İlk işteki "View Role" butonuna tıklar
    Then Lever Application form sayfasına yönlendirildiği doğrulanır

