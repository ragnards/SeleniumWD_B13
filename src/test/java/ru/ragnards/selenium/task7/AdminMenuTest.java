package ru.ragnards.selenium.task7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;

public class AdminMenuTest extends BaseTest {

    @Test
    public void testAdminMenu() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        int menuItemsCount = driver.findElements(By.id("app-")).size();
        for (int i = 1; i <= menuItemsCount; i++) {
            WebElement menuItem = driver.findElement(By.cssSelector("#app-:nth-child(" + i + ")"));
            menuItem.click();
            driver.findElement(By.tagName("h1")).isDisplayed();
            int submenuItemsCount = driver.findElements(By.cssSelector(".docs li")).size();
            for (int j = 1; j <= submenuItemsCount; j++) {
                WebElement submenuItem = driver.findElement(By.cssSelector(".docs li:nth-child(" + j + ")"));
                submenuItem.click();
                driver.findElement(By.tagName("h1")).isDisplayed();
            }
        }
    }
}
