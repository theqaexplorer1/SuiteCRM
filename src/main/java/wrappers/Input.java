package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Wrapper для текстовых полей input.
 * Ищет поле по тексту лейбла рядом.
 */
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
        String xpath = String.format("//div[contains(text(), '%s')]/parent::div//input", label);
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    /**
     * Очищает поле перед вводом.
     */
    public void clearAndWrite(String text) {
        String xpath = String.format("//div[contains(text(), '%s')]/parent::div//input", label);
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
}