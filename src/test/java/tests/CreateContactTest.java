package tests;

import dto.Contact;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Тест создания контакта в SuiteCRM.
 */
@Log4j2
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
        log.info("Starting Create Contact");
        // Генерация уникального суффикса (короткого)
        String uniqueId = String.valueOf(System.currentTimeMillis()).substring(8);
        // данные контакта (используется короткий конструктор)
        Contact contact = Contact.builder()
                .firstName("Ivan_" + uniqueId)
                .lastName("Ivanov")
                .phoneWork("+7999" + uniqueId + "000")
                .email(String.format("ivan.%s@test.com", uniqueId))
                .build();

        log.debug("Created Contact object: {}", contact);
        // Полная цепочка: LoginPage -> MainPage -> AddContactPage -> ContactPage
        log.info("Starting login for contact creation");
        String createdContactName = new LoginPage(driver)
                .open()
                .login("will", "will")
                .goToCreateContact()
                .fillContactForm(contact)      // Заполняет форму данными из DTO
                .save()                        // Кликает Save, ждёт загрузки ContactPage
                .getContactName();             // Читает имя со страницы
        log.info("Created contact name: '{}'", createdContactName);
        // Проверка: имя контакта сформировано как "FirstName LastName"
        String expectedFullName = contact.getFirstName() + " " + contact.getLastName();
        Assert.assertEquals(createdContactName.trim().toUpperCase(), expectedFullName.toUpperCase(),
                "Полное имя созданного контакта не совпадает с ожидаемым");
        log.info("Test passed: Contact created successfully");
    }
}