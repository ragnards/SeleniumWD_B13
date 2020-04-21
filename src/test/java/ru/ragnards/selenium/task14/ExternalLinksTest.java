package ru.ragnards.selenium.task14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;

import java.util.List;

public class ExternalLinksTest extends BaseTest {

    @BeforeTest
    public void login() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void testExternalLinks() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("#content .button")).click();

        List<WebElement> externalLinks = driver.findElements(By.cssSelector("form [target='_blank']"));

        for (WebElement link : externalLinks) {
            openLinkAndReturn(link);
        }
    }

    private void openLinkAndReturn(WebElement link) {
        String mainWindow = driver.getWindowHandle();
        link.click();
        wait.until(driver -> driver.getWindowHandles().size() == 2);
        String newWindow = driver.getWindowHandles().stream().filter(handler -> !handler.equals(mainWindow)).findFirst().get();
        driver.switchTo().window(newWindow);
        driver.findElement(By.tagName("title")).isDisplayed();
        driver.close();
        driver.switchTo().window(mainWindow);
    }
}
