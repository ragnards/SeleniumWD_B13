package ru.ragnards.selenium.task9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.ragnards.selenium.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Ordering.natural;
import static org.testng.Assert.assertTrue;

public class CountriesSortingTest extends BaseTest {

    @BeforeClass
    public void login() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void testCountriesSorting() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> rows = driver.findElements(By.className("row"));
        List<String> countryNames = rows.stream().map(row -> row.findElement(By.cssSelector("a:not([title])")).getText())
                .collect(Collectors.toList());
        assertTrue(natural().isOrdered(countryNames));

        List<String> countriesWithZonesUrl = rows.stream()
                .filter(row -> !row.findElement(By.cssSelector("td:nth-child(6)")).getText().equals("0"))
                .map(country -> country.findElement(By.cssSelector("a:not([title])")).getAttribute("href"))
                .collect(Collectors.toList());

        for (String url : countriesWithZonesUrl) {
            driver.navigate().to(url);
            List<String> zoneNames = driver.findElements(By.cssSelector("[type='hidden'][name$='[name]']")).stream()
                    .map(zone -> zone.getAttribute("value")).collect(Collectors.toList());
            assertTrue(natural().isOrdered(zoneNames));
        }
    }

    @Test
    public void testZonesSorting() {
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<String> countriesUrl = driver.findElements(By.cssSelector(".row a:not([title])")).stream()
                .map(country -> country.getAttribute("href")).collect(Collectors.toList());

        for (String url : countriesUrl) {
            driver.navigate().to(url);
            List<String> zoneNames = driver.findElements(By.cssSelector("[name$='[zone_code]'] option[selected]")).stream()
                    .map(WebElement::getText).collect(Collectors.toList());
            assertTrue(natural().isOrdered(zoneNames));
        }
    }
}
