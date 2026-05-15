package tests;

import dto.Account;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

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

        //Value Object: используем ПОЛНЫЙ конструктор с чекбоксами
        Account account = new Account(
                "TestAccount_" + System.currentTimeMillis(),
                "+79991112233",
                "+74991112233",
                "example.com",
                "Lenina st, 10",
                "Moscow",
                "Moscow",
                "123456",
                "Russia",
                "Lenina st, 10",
                "Moscow",
                "Moscow",
                "123456",
                "Russia",
                "Customer",
                "Technology",
                "Auto-created",
                true,
                true
        );

        //Chain of Invocations + Loadable Page
        String createdAccountName = new LoginPage(driver)
                .open()
                .login("will", "will")
                .goToCreateAccount()
                .fillAccountForm(account)
                .save()
                .getAccountName();

        // Проверка: сравниваем в верхнем регистре (SuiteCRM делает toUpperCase для name)
        Assert.assertEquals(createdAccountName.toUpperCase(), account.getName().toUpperCase(),
                "Имя созданного аккаунта не совпадает с переданным в DTO");
    }
}