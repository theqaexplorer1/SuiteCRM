package pages;

import dto.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrappers.AddressTextarea;
import wrappers.Input;
import wrappers.Select;

import java.time.Duration;

public class NewAccountPage {

    WebDriver driver;

    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addNewAccount(Account account) {
        new Input(driver, "Name").write(account.getName());
        new Input(driver, "Office Phone").write(account.getPhone());
        new Input(driver, "Fax").write(account.getFax());
        new Input(driver, "Website").write(account.getFax());
        new AddressTextarea(driver, "Billing Address", "Street").write(account.getStreet());
        new AddressTextarea(driver, "Shipping Address", "Street").write(account.getStreet());
        new Select(driver, "Type").select(account.getType());
        new Select(driver, "Industry").select(account.getIndustry());
    }

//    public void clicksave() {
//        driver.findElement(By.id("SAVE")).click();
//    }
public void clicksave() {
    WebElement saveButton = driver.findElement(By.id("SAVE"));

    // 1. Скроллим к элементу
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveButton);

    // 2. Ждём, пока элемент станет кликабельным
    new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(saveButton));

    // 3. Кликаем через JavaScript (обходит перекрытия)
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
}
}