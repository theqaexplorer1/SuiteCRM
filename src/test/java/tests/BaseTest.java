package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert = new SoftAssert();

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    @Description("Настройка браузера")
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {
        // Инициализация драйвера в зависимости от браузера
        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            //firefoxOptions.addArguments("--headless");  // опционально
            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();
        } else {
            // Chrome по умолчанию
            ChromeOptions chromeOptions = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-infobars");
            driver = new ChromeDriver(chromeOptions);
            //DriverManager.setDriver(driver);//1.03 xunit video
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        iTestContext.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    @Description("Закрытие браузера")
    public void tearDown() {
        driver.quit();
        softAssert.assertAll();
    }
}
