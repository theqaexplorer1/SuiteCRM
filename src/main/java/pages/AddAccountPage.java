package pages;

import dto.Account;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.*;

@Log4j2
public class AddAccountPage extends BasePage {

    private final By SAVE_BUTTON = By.id("SAVE");

    public AddAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        try {
            return driver.findElement(By.id("EditView")).isDisplayed()
                    && driver.getCurrentUrl().contains("action=EditView")
                    && driver.getCurrentUrl().contains("module=Accounts");
        } catch (Exception e) {
            log.warn("AddAccountPage not loaded: {}", e.getMessage());
            return false;
        }
    }

    public AddAccountPage open() {
        log.info("Open Add Account page");
        openPage("/index.php?module=Accounts&action=EditView&return_module=Accounts&return_action=" +
                "DetailView");
        return this;
    }

    /**
     * Заполняет форму данными из Account DTO.
     * Chain: возвращает this (можно продолжать цепочку).
     */
    public AddAccountPage fillAccountForm(Account account) {
        //Основные поля
        log.info("Filling main fields for account form with name='{}'", account.getName());
        new Input(driver, "Name").write(account.getName());
        new Input(driver, "Office Phone").write(account.getPhone());
        new Input(driver, "Fax").write(account.getFax());
        new Input(driver, "Website").write(account.getWebsite());
        // Все поля Billing Address
        log.info("Filling Billing Address fields: city='{}' and street='{}'",
                account.getBillingCity(), account.getBillingStreet());
        new AddressTextarea(driver,
                "Billing Address", "Street").write(account.getBillingStreet());
        new AddressInput(driver,
                "Billing Address", "City").write(account.getBillingCity());
        new AddressInput(driver,
                "Billing Address", "State/Region").write(account.getBillingState());
        new AddressInput(driver,
                "Billing Address", "Postal Code").write(account.getBillingPostalCode());
        new AddressInput(driver,
                "Billing Address", "Country").write(account.getBillingCountry());
        // Все поля Shipping Address
        log.info("Filling Shipping Address fields: city='{}' and street='{}'",
                account.getShippingCity(), account.getShippingStreet());
        new AddressTextarea(driver,
                "Shipping Address", "Street").write(account.getShippingStreet());
        new AddressInput(driver,
                "Shipping Address", "City").write(account.getShippingCity());
        new AddressInput(driver,
                "Shipping Address", "State/Region").write(account.getShippingState());
        new AddressInput(driver,
                "Shipping Address", "Postal Code").write(account.getShippingPostalCode());
        new AddressInput(driver,
                "Shipping Address", "Country").write(account.getShippingCountry());
        // Select поля
        log.info("Selecting Type: {}, Industry: {}", account.getType(), account.getIndustry());
        new Select(driver, "Type").select(account.getType());
        new Select(driver, "Industry").select(account.getIndustry());
        // Textarea Description
        log.info("Filling Description");
        new Textarea(driver, "Description").write(account.getDescription());
        // Чекбоксы email (Opted Out / Invalid)
        if (account.isEmailOptedOut()) {
            log.info("Checking 'Opted Out' checkbox");
            new Checkbox(driver, "Opt Out").check();
        }
        if (account.isEmailInvalid()) {
            log.info("Checking 'Invalid' checkbox");
            new Checkbox(driver, "Invalid").check();
        }
        log.info("Account form filled successfully");
        return this;
    }

    /**
     * Chain: нажимает Save и возвращает AccountPage (просмотр данных аккаунта).
     */
    public AccountPage save() {
        log.info("Saving account");
        WebElement saveButton = driver.findElement(SAVE_BUTTON);
        // Скроллим к кнопке
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveButton);
        // Ждём кликабельности
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        // Кликаем через JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
        return new AccountPage(driver);
    }
}