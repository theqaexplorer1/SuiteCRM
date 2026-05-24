package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class AccountPage extends BasePage {

    private final By ACCOUNT_NAME = By.cssSelector("h2.module-title-text");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return driver.findElement(ACCOUNT_NAME).isDisplayed()
                    && driver.getCurrentUrl().contains("action=DetailView")
                    && driver.getCurrentUrl().contains("module=Accounts");
        } catch (Exception e) {
            log.warn("AccountPage not loaded: {}", e.getMessage());
            return false;
        }
    }

    public AccountPage open(String accountId) {
        log.info("Open Account page with ID: {}", accountId);
        openPage("/index.php?module=Accounts&action=DetailView&record=" + accountId);
        return this;
    }

    /**
     * Возвращает имя аккаунта со страницы просмотра.
     */
    public String getAccountName() {
        String name = driver.findElement(ACCOUNT_NAME).getText().trim();
        log.debug("Get account name: '{}'", name);
        return name;
    }
}