package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Главная страница после авторизации
 */
public class MainPage extends BasePage {

    private final By MODULE_TITLE = By.cssSelector("h2.module-title-text");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return driver.findElement(MODULE_TITLE).isDisplayed()
                    && (driver.getCurrentUrl().contains("module=Home")
                    || driver.getCurrentUrl().contains("action=index"));
        } catch (Exception e) {
            return false;
        }
    }

    public MainPage open() {
        openPage("/index.php?module=Home&action=index");
        return this;
    }

    /**
     * Chain: переход на страницу создания аккаунта.
     * Используем прямой URL вместо клика по ссылке
     */
    public AddAccountPage goToCreateAccount() {
        // Переходим по прямому URL на форму создания аккаунта
        driver.get(BASE_URL + "/index.php?module=Accounts&action=EditView&return_module=Accounts&" +
                "return_action=DetailView");
        // Ждём, пока страница создания аккаунта загрузится
        wait.until(driver -> new AddAccountPage(driver).isPageLoaded());
        return new AddAccountPage(driver);
    }

    /**
     * Chain: переход на страницу создания контакта.
     */
    public AddContactPage goToCreateContact() {
        // Переходим по прямому URL на форму создания контакта
        driver.get(BASE_URL + "/index.php?module=Contacts&action=EditView&return_module=Contacts&" +
                "return_action=DetailView");
        // Ждём, пока страница создания контакта загрузится
        wait.until(driver -> new AddContactPage(driver).isPageLoaded());
        return new AddContactPage(driver);
    }
}