package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Wrapper для текстовых полей input.
 * Ищет поле по тексту лейбла рядом.
 */
@Log4j2
public class Input {

    private final WebDriver driver;
    private final String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    /**
     * Вводит текст в поле, найденное по лейблу.
     */
    public void write(String text) {
        // XPath: ищем div с текстом лейбла, затем внутри него input
        log.debug("Writing '{}' to field with label '{}'", text, label);
        String xpath = String.format("//div[contains(text(), '%s')]/parent::div//input", label);
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    /**
     * Очищает поле перед вводом.
     */
    public void clearAndWrite(String text) {
        log.debug("Clearing and writing '{}' to field with label '{}'", text, label);
        String xpath = String.format("//div[contains(text(), '%s')]/parent::div//input", label);
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
}