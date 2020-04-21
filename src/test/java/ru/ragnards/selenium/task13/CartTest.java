package ru.ragnards.selenium.task13;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartTest extends BaseTest {

    @Test
    public void testCart() {
        for (int i = 1; i <= 3; i++) {
            driver.navigate().to("http://localhost/litecart");
            driver.findElement(By.className("product")).click();
            String cartQuantity = driver.findElement(By.cssSelector("#cart .quantity")).getText();
            if (isElementPresent(By.name("options[Size]"))) {
                Select selectSize = new Select(driver.findElement(By.name("options[Size]")));
                selectSize.selectByValue("Medium");
            }
            driver.findElement(By.name("add_cart_product")).click();
            wait.until(textChanged(By.cssSelector("#cart .quantity"), cartQuantity));
        }

        driver.findElement(By.cssSelector(".link[href$='checkout']")).click();
        driver.findElement(By.className("shortcut")).click();

        do {
            WebElement orderSummary = driver.findElement(By.id("box-checkout-summary"));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(orderSummary));
        }
        while (isElementPresent(By.id("box-checkout-summary")));
    }

    private static ExpectedCondition<Boolean> textChanged(final By locator, final String value) {
        return driver -> {
            try {
                String actualValue = driver.findElement(locator).getText();
                return !actualValue.equals(value);
            } catch (StaleElementReferenceException var3) {
                return false;
            }
        };
    }
}
