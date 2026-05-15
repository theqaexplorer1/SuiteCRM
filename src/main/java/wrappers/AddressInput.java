package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Wrapper для полей ввода внутри блока адреса (City, State, etc.).
 */
public class AddressInput {
    private final WebDriver driver;
    private final String addressLabel;  // "Billing Address" или "Shipping Address"
    private final String fieldLabel;    // "City", "State", etc.

    public AddressInput(WebDriver driver, String addressLabel, String fieldLabel) {
        this.driver = driver;
        this.addressLabel = addressLabel;
        this.fieldLabel = fieldLabel;
    }

    /**
     * Вводит текст в поле адреса.
     */
    public void write(String text) {
        // XPath: ищем блок адреса по лейблу, затем внутри него поле по лейблу
        String xpath = String.format(
                "//legend[contains(text(), '%s')]/ancestor::div[contains(@class, " +
                        "'edit-view-row-item')]//label[contains(text(), '%s')]/../following-sibling::td//input",
                addressLabel, fieldLabel
        );
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
}
