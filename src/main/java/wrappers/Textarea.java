package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Wrapper для многострочных полей textarea (Description и т.п.).
 */
public class Textarea {
    private final WebDriver driver;
    private final String label;

    public Textarea(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    /**
     * Вводит текст в textarea по лейблу.
     */
    public void write(String text) {
        String xpath = String.format("//div[contains(text(), '%s')]/parent::div//textarea", label);
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
}