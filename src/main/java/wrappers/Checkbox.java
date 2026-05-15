package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Wrapper для чекбоксов.
 * 🔑 Ключевое: исключаем чекбоксы внутри скрытых шаблонов (.hidden)
 */
public class Checkbox {

    private final WebDriver driver;
    private final String title;  // title атрибута: "Opt Out" или "Invalid"

    public Checkbox(WebDriver driver, String title) {
        this.driver = driver;
        this.title = title;
    }

    /**
     * Устанавливает чекбокс по title атрибуту.
     * Исключает элементы внутри скрытых контейнеров (.hidden)
     * Использует скролл + клик через JS
     */
    public void check() {
        // XPath: ищем чекбокс по title, НО исключаем те, что внутри .hidden
        String xpath = String.format(
                "//input[@type='checkbox' and @title='%s' and not(ancestor::*[contains(@class, 'hidden')])]",
                title
        );
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        // Скроллим к элементу
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkbox);
        // Кликаем через JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

    /**
     * Снимает чекбокс по title атрибуту.
     */
    public void uncheck() {
        String xpath = String.format(
                "//input[@type='checkbox' and @title='%s' " +
                        "and not(ancestor::*[contains(@class, 'hidden')])]",
                title
        );
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        if (checkbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", checkbox);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    /**
     * Проверяет, установлен ли чекбокс.
     */
    public boolean isChecked() {
        String xpath = String.format(
                "//input[@type='checkbox' and @title='%s' and not(ancestor::*[contains(@class, 'hidden')])]",
                title
        );
        return driver.findElement(By.xpath(xpath)).isSelected();
    }
}