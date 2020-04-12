package ru.ragnards.selenium.task11;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;

import java.util.Random;

public class RegisterNewUserTest extends BaseTest {

    @Test
    public void testRegisterNewUser() {
        driver.navigate().to("http://localhost/litecart");
        driver.findElement(By.cssSelector("[name='login_form'] a")).click();

        driver.findElement(By.name("firstname")).sendKeys("Иван");
        driver.findElement(By.name("lastname")).sendKeys("Иванов");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("address1")).sendKeys("First Avenue");
        driver.findElement(By.name("city")).sendKeys("Saint-Petersburg");

        Select selectCountry = new Select(driver.findElement(By.name("country_code")));
        selectCountry.selectByVisibleText("United States");

        String email = "test" + new Random().nextInt(99999) + "@mail.org";
        String password = "qwerty";
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+1234567890");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();

        logout();
        login(email, password);
        logout();
    }

    private void logout() {
        driver.findElement(By.cssSelector("#box-account [href$='logout']")).click();
    }

    private void login(String login, String password) {
        driver.findElement(By.name("email")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }
}
