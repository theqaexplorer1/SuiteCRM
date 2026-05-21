package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница просмотра контакта (после создания).
 * Chain of Invocations + Loadable Page
 */
@Log4j2
public class ContactPage extends BasePage {
    // Уникальный элемент: заголовок с именем контакта
    private final By CONTACT_NAME = By.cssSelector("h2.module-title-text");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Loadable Page: проверка, что мы на странице просмотра контакта.
     */
    @Override
    public boolean isPageLoaded() {
        try {
            return driver.findElement(CONTACT_NAME).isDisplayed()
                    && driver.getCurrentUrl().contains("module=Contacts")
                    && driver.getCurrentUrl().contains("action=DetailView");
        } catch (Exception e) {
            log.warn("ContactPage not loaded: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Loadable Page: открывает страницу контакта по ID.
     * Chain of Invocations: возвращает this.
     * @param contactId ID контакта в системе
     * @return эта же страница
     */
    public ContactPage open(String contactId) {
        log.info("Open Contact page with ID: {}", contactId);
        openPage("/index.php?module=Contacts&action=DetailView&record=" + contactId);
        return this;
    }

    /**
     * Возвращает полное имя контакта с страницы просмотра.
     * На странице имя отображается в элементе <h2 class="module-title-text">.
     * @return имя контакта Имя и Фамилия
     */
    public String getContactName() {
        String name = driver.findElement(CONTACT_NAME).getText().trim();
        log.debug("Get contact name: '{}'", name);
        return name;
    }

    /**
     * Возвращает имя контакта в формате FirstName LastName.
     * @param firstName имя
     * @param lastName фамилия
     * @return полное имя
     */
    public String formatFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}