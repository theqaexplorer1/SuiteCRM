package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница просмотра контакта (после создания).
 * Chain of Invocations + Loadable Page
 */
public class ContactPage extends BasePage {
    // Уникальный элемент: заголовок с именем контакта
    private final By CONTACT_NAME = By.cssSelector("h2.module-title-text");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Loadable Page: проверка, что мы на странице просмотра контакта.
     * Уникальные признаки: заголовок с именем и module=Contacts в URL.
     */
    @Override
    public boolean isPageLoaded() {
        try {
            return driver.findElement(CONTACT_NAME).isDisplayed()
                    && driver.getCurrentUrl().contains("module=Contacts")
                    && driver.getCurrentUrl().contains("action=DetailView");
        } catch (Exception e) {
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
        openPage("/index.php?module=Contacts&action=DetailView&record=" + contactId);
        return this;
    }

    /**
     * Возвращает полное имя контакта с страницы просмотра.
     * На странице имя отображается в элементе <h2 class="module-title-text">.
     * @return имя контакта Имя и Фамилия
     */
    public String getContactName() {
        return driver.findElement(CONTACT_NAME).getText().trim();
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