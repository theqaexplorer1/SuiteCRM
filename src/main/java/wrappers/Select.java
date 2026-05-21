package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Wrapper для Select
 */
@Log4j2
public class Select {
    WebDriver driver;
    String label;

    public Select(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        log.debug("Selecting option '{}' for label '{}'", option, label);
        driver.findElement
                (By.xpath(String.format("//*[contains(text(), '%s')]/following-sibling::div//select",
                        label))).click();
        driver.findElement
                (By.xpath(String.format("//*[contains(text(), '%s')]/following-sibling::div//option[contains(text(), '%s')]",
                        label, option))).click();
    }
}