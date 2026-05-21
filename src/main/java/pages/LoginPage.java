package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.id("user_name");
    private final By PASSWORD_FIELD = By.id("username_password");
    private final By LOGIN_BUTTON = By.id("bigbutton");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Loadable Page: проверка, что мы на странице логина.
     */
    @Override
    public boolean isPageLoaded() {
        try {
            return driver.findElement(USERNAME_FIELD).isDisplayed()
                    && driver.getCurrentUrl().contains("action=Login");
        } catch (Exception e) {
            log.warn("Page not loaded: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Loadable Page: открывает страницу и ждёт загрузки.
     * Chain of Invocations: возвращает this (остаёмся на LoginPage).
     */
    public LoginPage open() {
        log.info("Open login page");
        openPage("/index.php?action=Login&module=Users");
        return this;
    }

    /**
     * Chain of Invocations: после успешного логина возвращаем MainPage.
     */
    public MainPage login(String username, String password) {
        log.info("Logging in as '{}'", username);
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        // Возвращаем следующую страницу
        return new MainPage(driver);
    }
}