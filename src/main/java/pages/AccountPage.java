package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
            return false;
        }
    }

    public AccountPage open(String accountId) {
        openPage("/index.php?module=Accounts&action=DetailView&record=" + accountId);
        return this;
    }

    /**
     * Возвращает имя аккаунта со страницы просмотра.
     */
    public String getAccountName() {
        return driver.findElement(ACCOUNT_NAME).getText().trim();
    }
}