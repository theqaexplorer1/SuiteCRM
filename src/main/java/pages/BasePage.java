package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Базовый класс для всех страниц. Loadable Page
 */
@Log4j2
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    public static final String BASE_URL = "https://demo.suiteondemand.com";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Проверка, что страница действительно открыта.
     * Каждая страница должна реализовать этот метод.
     * @return true, если страница загружена и готова к работе
     */
    public abstract boolean isPageLoaded();

    /**
     * Открывает страницу по относительному URL и ждёт её полной загрузки.
     */
    protected void openPage(String relativeUrl) {
        log.debug("Open page: {}{}", BASE_URL, relativeUrl);
        driver.get(BASE_URL + relativeUrl);
        wait.until(driver -> isPageLoaded());
        log.info("Page loaded: {}", relativeUrl);
    }

    /**
     * Возвращает заголовок страницы (для проверок в тестах).
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}