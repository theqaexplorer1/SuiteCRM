package tests;

import dto.Account;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.NewAccountPage;

import static java.lang.Thread.sleep;

public class NewAccountTest extends BaseTest{
    Account account = new Account("Ivan", "+79991231212", "+74991231212",
            "example.com", "Quality Street", "Investor", "Banking");
    Account account1 = new Account("John", "+79991231212", "+74991231212",
            "example.com", "Quality Street", "Investor", "Banking");

    @Test
    public void checkAddNewAccount() throws InterruptedException {
        driver.get("https://demo.suiteondemand.com/index.php?action=Login&module=Users");
        driver.findElement(By.id("user_name")).sendKeys("will");
        driver.findElement(By.id("username_password")).sendKeys("will");
        driver.findElement(By.id("bigbutton")).click();
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=" +
                "Accounts&return_action=DetailView");
        sleep(2000);
        NewAccountPage newAccountPage = new NewAccountPage(driver);
        newAccountPage.addNewAccount(account);
        sleep(2000);
        newAccountPage.clicksave();
        sleep(2000);
        driver.quit();

    }


}
