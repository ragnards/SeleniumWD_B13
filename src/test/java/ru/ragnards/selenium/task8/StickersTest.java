package ru.ragnards.selenium.task8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;

import java.util.List;

public class StickersTest extends BaseTest {

    @Test
    public void testOnceStickerExists() {
        driver.navigate().to("http://localhost/litecart");
        List<WebElement> goods = driver.findElements(By.className("product"));
        for (WebElement good : goods) {
            int stickersCount = good.findElements(By.className("sticker")).size();
            Assert.assertEquals(stickersCount, 1);
        }
    }
}
