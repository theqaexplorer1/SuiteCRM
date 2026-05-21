package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Wrapper для многострочных полей textarea (Description и т.п.).
 */
@Log4j2
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
        log.debug("Writing '{}' to textarea with label '{}'", text, label);
        String xpath = String.format("//div[contains(text(), '%s')]/parent::div//textarea", label);
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
}