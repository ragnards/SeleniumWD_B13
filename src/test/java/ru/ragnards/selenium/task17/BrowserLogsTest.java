package ru.ragnards.selenium.task17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BrowserLogsTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        loginAsAdmin();
    }

    @Test
    public void testNoLogMessagesAtCatalogPages() {
        navigateToCatalog();
        int productsSize = driver.findElements(By.cssSelector("[href*='product_id']:not([title])")).size();
        for (int i = 0; i < productsSize; i++) {
            WebElement product = driver.findElements(By.cssSelector("[href*='product_id']:not([title])")).get(i);
            wait.until(elementToBeClickable(product)).click();
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if (logs.size() != 0) {
                System.out.println("JS errors on the page " + driver.getCurrentUrl() + ":\n" + logs);
            }
            navigateToCatalog();
        }
    }

    private void navigateToCatalog() {
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    }
}
