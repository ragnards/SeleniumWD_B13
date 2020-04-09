package ru.ragnards.selenium.task10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductPageTest extends BaseTest {

    private WebElement product;

    @BeforeMethod
    public void findProduct() {
        driver.navigate().to("http://localhost/litecart");
        product = driver.findElement(By.cssSelector("#box-campaigns .product"));
    }

    @Test
    public void testProductName() {
        String nameAtMainPage = product.findElement(By.className("name")).getText();
        product.click();
        String nameAtProductPage = driver.findElement(By.cssSelector("[itemprop='name']")).getText();
        assertEquals(nameAtMainPage, nameAtProductPage);
    }

    @Test
    public void testProductPrices() {
        String regularPriceAtMainPage = product.findElement(By.className("regular-price")).getText();
        String campaignPriceAtMainPage = product.findElement(By.className("campaign-price")).getText();
        product.click();
        String regularPriceAtProductPage = driver.findElement(By.className("regular-price")).getText();
        String campaignPriceAtProductPage = driver.findElement(By.className("campaign-price")).getText();
        assertEquals(regularPriceAtMainPage, regularPriceAtProductPage);
        assertEquals(campaignPriceAtMainPage, campaignPriceAtProductPage);
    }

    @Test
    public void testRegularPriceMainPage() {
        WebElement price = product.findElement(By.className("regular-price"));
        String regularPriceColor = price.getCssValue("color");
        java.awt.Color color = Color.fromString(regularPriceColor).getColor();
        String regularPriceStyle = price.getCssValue("text-decoration-line");

        assertTrue(color.getRed() == color.getGreen() && color.getGreen() == color.getBlue());
        assertEquals(regularPriceStyle, "line-through");
    }

    @Test
    public void testRegularPriceProductPage() {
        product.click();
        WebElement price = driver.findElement(By.className("regular-price"));
        String regularPriceColor = price.getCssValue("color");
        java.awt.Color color = Color.fromString(regularPriceColor).getColor();
        String regularPriceStyle = price.getCssValue("text-decoration-line");

        assertTrue(color.getRed() == color.getGreen() && color.getGreen() == color.getBlue());
        assertEquals(regularPriceStyle, "line-through");
    }

    @Test
    public void testCampaignPriceMainPage() {
        WebElement price = product.findElement(By.className("campaign-price"));
        String campaignPriceColor = price.getCssValue("color");
        java.awt.Color color = Color.fromString(campaignPriceColor).getColor();
        String campaignPriceStyle = price.getCssValue("font-weight");

        assertTrue(color.getRed() != 0 && color.getGreen() == 0 && color.getBlue() == 0);
        assertTrue(Integer.parseInt(campaignPriceStyle) >= 600);
    }

    @Test
    public void testCampaignPriceProductPage() {
        product.click();
        WebElement price = driver.findElement(By.className("campaign-price"));
        String campaignPriceColor = price.getCssValue("color");
        java.awt.Color color = Color.fromString(campaignPriceColor).getColor();
        String campaignPriceStyle = price.getCssValue("font-weight");

        assertTrue(color.getRed() != 0 && color.getGreen() == 0 && color.getBlue() == 0);
        assertTrue(Integer.parseInt(campaignPriceStyle) >= 600);
    }

    @Test
    public void testPricesSizeMainPage() {
        WebElement regularPrice = product.findElement(By.className("regular-price"));
        WebElement campaignPrice = product.findElement(By.className("campaign-price"));

        double regularPriceSize = Double.parseDouble(regularPrice.getCssValue("font-size").replace("px", ""));
        double campaignPriceSize = Double.parseDouble(campaignPrice.getCssValue("font-size").replace("px", ""));

        assertTrue(campaignPriceSize > regularPriceSize);
    }

    @Test
    public void testPricesSizeProductPage() {
        product.click();
        WebElement regularPrice = driver.findElement(By.className("regular-price"));
        WebElement campaignPrice = driver.findElement(By.className("campaign-price"));

        double regularPriceSize = Double.parseDouble(regularPrice.getCssValue("font-size").replace("px", ""));
        double campaignPriceSize = Double.parseDouble(campaignPrice.getCssValue("font-size").replace("px", ""));

        assertTrue(campaignPriceSize > regularPriceSize);
    }
}
