package tests;

import dto.Account;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Log4j2
@Epic("SuiteCRM")
@Feature("Account Management")
@Owner("ivan.ivanov")
@Link(name = "SuiteCRM Demo", url = "https://demo.suiteondemand.com")
public class CreateAccountTest extends BaseTest {

    @Test(description = "Создание нового аккаунта с полной цепочкой вызовов")
    @Description("Позитивный тест: авторизация, переход к созданию, заполнение формы через DTO, сохранение, проверка имени")
    @TmsLink("QASE-ACC-001")
    @Issue("BUG-ACC-001")
    @Story("Create Account Flow")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("ivan.ivanov")
    @Link(name = "SuiteCRM", url = "https://demo.suiteondemand.com")
    public void testCreateAccount() {
        log.info("Starting Create Account");
        Account account = Account.builder()
                .name("TestAccount_" + System.currentTimeMillis())
                .phone("+79991112233")
                .fax("+74991112233")
                .website("example.com")
                .billingStreet("Lenina st, 50")
                .billingCity("Moscow")
                .billingState("Moscow")
                .billingPostalCode("123456")
                .billingCountry("Russia")
                .shippingStreet("Lenina st, 50")
                .shippingCity("Moscow")
                .shippingState("Moscow")
                .shippingPostalCode("123456")
                .shippingCountry("Russia")
                .type("Customer")
                .industry("Technology")
                .description("Auto created test account")
                .emailOptedOut(true)
                .emailInvalid(true)
                .build();

        log.debug("Created Account object: {}", account);
        log.info("Starting login for account creation");
        String createdAccountName = new LoginPage(driver)
                .open()
                .login("will", "will")
                .goToCreateAccount()
                .fillAccountForm(account)
                .save()
                .getAccountName();
        log.info("Created account name: '{}'", createdAccountName);
        // Проверка: сравниваем в верхнем регистре (SuiteCRM делает toUpperCase для name)
        Assert.assertEquals(createdAccountName.toUpperCase(), account.getName().toUpperCase(),
                "Имя созданного аккаунта не совпадает с переданным в DTO");
        log.info("Account created successfully");
    }
}