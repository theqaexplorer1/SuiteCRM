package pages;

import dto.Contact;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.*;

/**
 * Страница создания нового контакта.
 * Chain of Invocations + Loadable Page
 */
@Log4j2
public class AddContactPage extends BasePage {

    private final By SAVE_BUTTON = By.id("SAVE");

    public AddContactPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Loadable Page: проверка, что мы на странице создания контакта.
     */
    @Override
    public boolean isPageLoaded() {
        try {
            return driver.findElement(By.id("EditView")).isDisplayed()
                    && driver.getCurrentUrl().contains("action=EditView")
                    && driver.getCurrentUrl().contains("module=Contacts");
        } catch (Exception e) {
            log.warn("AddContactPage not loaded: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Loadable Page: открывает страницу и ждёт полной загрузки.
     * Chain of Invocations: возвращает this (остаёмся на этой странице).
     */
    public AddContactPage open() {
        log.info("Opening Add Contact page");
        openPage("/index.php?module=Contacts&action=EditView&return_module=Contacts&return_action=" +
                "DetailView");
        return this;
    }

    /**
     * Заполняет форму создания контакта данными из Contact DTO.
     * Chain of Invocations: возвращает this для продолжения цепочки.
     * @param contact объект с данными контакта
     * @return эта же страница (для продолжения цепочки)
     */
    public AddContactPage fillContactForm(Contact contact) {
        // Основные поля
        // Обращение (MR или MRS - выпадающий список перед First Name)
        String salutation = contact.getSalutation();
        if (salutation != null && !salutation.isEmpty()) {
            new Select(driver, "Salutation").select(salutation);
        }
        // Имя и фамилия
        log.info("Filling contact form: firstName='{}', lastName='{}'",
                contact.getFirstName(), contact.getLastName());
        new Input(driver, "First Name").write(contact.getFirstName());
        new Input(driver, "Last Name").write(contact.getLastName());
        // Телефоны
        log.info("Filling contacts");
        new Input(driver, "Office Phone").write(contact.getPhoneWork());
        new Input(driver, "Mobile").write(contact.getPhoneMobile());
        new Input(driver, "Fax").write(contact.getPhoneFax());
        // Должность и отдел
        log.info("Filling Job and Department");
        new Input(driver, "Job Title").write(contact.getTitle());
        new Input(driver, "Department").write(contact.getDepartment());
        // Account Name
        if (!contact.getAccountName().isEmpty()) {
            new Input(driver, "Account Name").write(contact.getAccountName());
        }
        // Email (простой вариант - первый email)
        if (!contact.getEmail().isEmpty()) {
            // Для простоты заполняем первое поле
            log.info("Filling Email: {}", contact.getEmail());
            String xpath = "//input[@id='Contacts0emailAddress0']";
            driver.findElement(By.xpath(xpath)).sendKeys(contact.getEmail());
        }
        // Primary Address (Основной адрес)
        log.info("Filling Primary Address");
        new AddressTextarea(driver, "Primary Address",
                "Address").write(contact.getPrimaryStreet());
        new AddressInput(driver, "Primary Address",
                "City").write(contact.getPrimaryCity());
        new AddressInput(driver, "Primary Address",
                "State/Region").write(contact.getPrimaryState());
        new AddressInput(driver, "Primary Address",
                "Postal Code").write(contact.getPrimaryPostalCode());
        new AddressInput(driver, "Primary Address",
                "Country").write(contact.getPrimaryCountry());
        // Other Address (Альтернативный адрес)
        if (!contact.getAltStreet().isEmpty()) {
            log.info("Filling Alternative Other Address");
            new AddressTextarea(driver, "Other Address",
                    "Other Address").write(contact.getAltStreet());
            new AddressInput(driver, "Other Address",
                    "City").write(contact.getAltCity());
            new AddressInput(driver, "Other Address",
                    "State/Region").write(contact.getAltState());
            new AddressInput(driver, "Other Address",
                    "Postal Code").write(contact.getAltPostalCode());
            new AddressInput(driver, "Other Address",
                    "Country").write(contact.getAltCountry());
        }
        // Textarea Description
        log.info("Filling Description");
        new Textarea(driver, "Description").write(contact.getDescription());

        // More Information
        if (!contact.getLeadSource().isEmpty()) {
            log.info("Filling More Information");
            new Select(driver, "Lead Source").select(contact.getLeadSource());
        }

        log.info("Contact form filled successfully");
        return this;  // Chain: остаёмся на этой странице
    }

    /**
     * Chain of Invocations: нажимает Save и возвращает страницу просмотра контакта.
     * @return ContactPage — страница просмотра созданного контакта
     */
    public ContactPage save() {
        log.info("Saving contact");
        WebElement saveButton = driver.findElement(SAVE_BUTTON);
        // Скроллим к кнопке (на случай, если она не в видимой области)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveButton);
        // Ждём кликабельности
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        // Кликаем через JS (на случай если есть перекрытия)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
        // Возвращаем следующую страницу
        return new ContactPage(driver);
    }
}