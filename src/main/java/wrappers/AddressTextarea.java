package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Wrapper для поля Street в блоке адреса.
 */
@Log4j2
public class AddressTextarea {
    private final WebDriver driver;
    private final String addressLabel;  // "Billing Address" или "Shipping Address"
    private final String fieldLabel;    // "Street"

    public AddressTextarea(WebDriver driver, String addressLabel, String fieldLabel) {
        this.driver = driver;
        this.addressLabel = addressLabel;
        this.fieldLabel = fieldLabel;
    }

    /**
     * Вводит текст в textarea Street внутри блока адреса.
     */
    public void write(String text) {
        log.debug("Writing '{}' to address textarea '{}' under '{}'", text, fieldLabel, addressLabel);
        String xpath = String.format(
                "//*[contains(text(), '%s')]/ancestor::div[contains(@class, " +
                        "'edit-view-row-item')]//*[contains(text(), '%s')]//ancestor::tr//textarea",
                addressLabel, fieldLabel
        );
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
}