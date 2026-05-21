package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Главная страница после авторизации
 */
@Log4j2
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
            log.warn("MainPage not loaded: {}", e.getMessage());
            return false;
        }
    }

    public MainPage open() {
        log.info("Opening main page");
        openPage("/index.php?module=Home&action=index");
        return this;
    }

    /**
     * Chain: переход на страницу создания аккаунта.
     */
    public AddAccountPage goToCreateAccount() {
        log.info("Go to Create Account page");
        driver.get(BASE_URL + "/index.php?module=Accounts&action=EditView&return_module=Accounts&" +
                "return_action=DetailView");
        // Ждём появления формы #EditView и нужного URL (прямая проверка)
        wait.until(driver -> {
            try {
                return driver.findElement(By.id("EditView")).isDisplayed()
                        && driver.getCurrentUrl().contains("action=EditView")
                        && driver.getCurrentUrl().contains("module=Accounts");
            } catch (Exception e) {
                return false;  // Если элемент не найден — продолжаем ждать
            }
        });
        log.debug("Create Account page loaded");
        return new AddAccountPage(driver);
    }

    /**
     * Chain: переход на страницу создания контакта.
     */
    public AddContactPage goToCreateContact() {
        log.info("Go to Create Contact page");
        driver.get(BASE_URL + "/index.php?module=Contacts&action=EditView&return_module=Contacts&" +
                "return_action=DetailView");
        // Ждём появления формы #EditView и нужного URL (прямая проверка)
        wait.until(driver -> {
            try {
                return driver.findElement(By.id("EditView")).isDisplayed()
                        && driver.getCurrentUrl().contains("action=EditView")
                        && driver.getCurrentUrl().contains("module=Contacts");
            } catch (Exception e) {
                return false;  // Если элемент не найден — продолжаем ждать
            }
        });
        log.debug("Create Contact page loaded");
        return new AddContactPage(driver);
    }
}