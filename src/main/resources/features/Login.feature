@login
Feature: Kullanıcı Giriş Testi

  Scenario: Doğru email ve şifre ile başarılı giriş
    Given Tarayıcı başlatılır
    When "http://automationexercise.com" adresine gidilir
    Then Ana sayfa başarılı şekilde görünür olduğu doğrulanır
    When "Signup / Login" butonuna tıklanır
    Then "Login to your account" görünür olduğu doğrulanır
    When Doğru email adresi ve şifre girilir
    And "login" butonuna tıklanır
    Then "Logged in as username" görünür olduğu doğrulanır
