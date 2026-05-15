package tests;

import dto.Contact;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Тест создания контакта в SuiteCRM.
 * Chain of Invocations + Loadable Page + Value Object
 */
@Epic("SuiteCRM")
@Feature("Contact Management")
@Owner("ivan.ivanov")
@Link(name = "SuiteCRM Demo", url = "https://demo.suiteondemand.com")
public class CreateContactTest extends BaseTest {

    @Test(description = "Создание нового контакта с полной цепочкой вызовов")
    @Description("Позитивный тест: авторизация, переход к созданию, заполнение формы через DTO, сохранение, проверка полного имени")
    @TmsLink("QASE-CON-001")
    @Issue("BUG-CON-001")
    @Story("Create Contact Flow")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("ivan.ivanov")
    @Link(name = "SuiteCRM", url = "https://demo.suiteondemand.com")
    public void testCreateContact() {
        // Генерация уникального суффикса (короткого)
        String uniqueId = String.valueOf(System.currentTimeMillis()).substring(8);

        // Value Object: данные контакта (используется короткий конструктор)
        Contact contact = new Contact(
                "Ivan_" + uniqueId,
                "Ivanov",
                "+7999" + uniqueId + "000",
                String.format("ivanov.%s@test.com", uniqueId)  // emaiil
        );

        // Chain of Invocations + Loadable Page
        // Полная цепочка: LoginPage -> MainPage -> AddContactPage -> ContactPage
        String createdContactName = new LoginPage(driver)
                .open()
                .login("will", "will")
                .goToCreateContact()
                .fillContactForm(contact)      // Заполняет форму данными из DTO
                .save()                        // Кликает Save, ждёт загрузки ContactPage
                .getContactName();             // Читает имя со страницы

        // Проверка: имя контакта сформировано как "FirstName LastName"
        String expectedFullName = contact.getFirstName() + " " + contact.getLastName();

        Assert.assertEquals(createdContactName.trim().toUpperCase(), expectedFullName.toUpperCase(),
                "Полное имя созданного контакта не совпадает с ожидаемым");
    }
}