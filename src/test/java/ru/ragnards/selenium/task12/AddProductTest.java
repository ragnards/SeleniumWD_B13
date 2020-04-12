package ru.ragnards.selenium.task12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;


import static java.lang.ClassLoader.getSystemResource;

public class AddProductTest extends BaseTest {

    @BeforeTest
    public void login() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void testAddProduct() {
        driver.findElement(By.xpath("//*[text()='Catalog']")).click();
        driver.findElement(By.cssSelector(".button[href$='edit_product']")).click();

        driver.findElement(By.cssSelector("[name='status'][value='1']")).click();
        driver.findElement(By.name("name[en]")).sendKeys("Rubber Duck Mask");
        driver.findElement(By.name("code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[data-name='Rubber Ducks']")).click();

        Select selectDefaultCategory = new Select(driver.findElement(By.name("default_category_id")));
        selectDefaultCategory.selectByVisibleText("Rubber Ducks");

        driver.findElement(By.xpath("//td[text()='Unisex']/..//input[@name='product_groups[]']")).click();

        WebElement quantityField = driver.findElement(By.name("quantity"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", quantityField, "50");

        Select selectQuantityUnit = new Select(driver.findElement(By.name("quantity_unit_id")));
        selectQuantityUnit.selectByVisibleText("pcs");
        Select selectDeliveryStatus = new Select(driver.findElement(By.name("delivery_status_id")));
        selectDeliveryStatus.selectByVisibleText("3-5 days");
        Select selectSoldOutStatus = new Select(driver.findElement(By.name("sold_out_status_id")));
        selectSoldOutStatus.selectByVisibleText("Temporary sold out");

        driver.findElement(By.name("new_images[]")).sendKeys(getSystemResource("task12_image.jpg").getPath().replaceFirst("^/(.:/)", "$1"));
        driver.findElement(By.name("date_valid_from")).sendKeys("01.01.2020");
        driver.findElement(By.name("date_valid_to")).sendKeys("01.01.2021");

        driver.findElement(By.cssSelector("[href='#tab-information']")).click();

        Select selectManufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        selectManufacturer.selectByVisibleText("ACME Corp.");

        driver.findElement(By.name("keywords")).sendKeys("mask");
        driver.findElement(By.name("short_description[en]")).sendKeys("Duck Mask");
        driver.findElement(By.name("description[en]")).sendKeys("Rubber Duck Mask");
        driver.findElement(By.name("head_title[en]")).sendKeys("Duck");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Yellow Rubber Duck Mask");

        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();

        driver.findElement(By.name("purchase_price")).sendKeys("5");
        Select selectPriceCurrency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        selectPriceCurrency.selectByValue("EUR");

        driver.findElement(By.name("prices[USD]")).sendKeys("10");
        WebElement priceUsdTaxField = driver.findElement(By.name("gross_prices[USD]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", priceUsdTaxField, "10");
        driver.findElement(By.name("prices[EUR]")).sendKeys("9");
        WebElement priceEurTaxField = driver.findElement(By.name("gross_prices[EUR]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", priceEurTaxField, "9");

        driver.findElement(By.name("save")).click();

        driver.findElement(By.xpath("//table//*[text()='Rubber Duck Mask']")).isDisplayed();
    }
}

