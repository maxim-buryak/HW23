package HW23;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.IntStream;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FindMonitors {
    WebDriver driver;
    WebDriverWait wait;
    String initialUrl = "http://rozetka.com.ua";

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBworser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateAction() {
        driver.get(initialUrl);
    }

    @Test
    public void TestFoundMonitors() {
        assertEquals(driver.findElements(By.xpath("//rz-icon-counter/span")).size(), 0);
        driver.findElement(By.xpath("//button[@id='fat-menu']")).click();

        wait.until(presenceOfElementLocated(By.xpath("//rz-header/header/div/div/rz-header-fat-menu/button")));
        driver.findElement(By.cssSelector("a[href='https://hard.rozetka.com.ua/monitors/c80089/'][class=menu__link]")).click();

        wait.until(presenceOfElementLocated(By.className("header-component")));
        int comparedPrice = 4000;
            List<WebElement> findElements = driver.findElements(By.className("goods-tile__price-value"));
        IntStream.range(0, findElements.size())
                .filter(i -> Integer.parseInt(findElements.get(i).getText().replace(" ","")) < comparedPrice)
                .findFirst()
                .ifPresent(i -> driver.findElement(By.xpath("(//span[@class='goods-tile__price-value']//preceding::a[1])"+ "[" + i + 1 + "]")).click());
        wait.until(presenceOfElementLocated(By.cssSelector("a[class='tabs__link tabs__link--active']")));
        driver.findElement(By.xpath("//button[@class='compare-button ng-star-inserted']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//rz-icon-counter//child::span")));
        assertTrue(driver.findElement(By.xpath("//rz-icon-counter//child::span")).isDisplayed());
        String monitorTitle1 = driver.findElement(By.cssSelector("h1[class='product__title']")).getText();
        int comparedPrice1 = Integer.parseInt(driver.findElement(By.cssSelector("p.product-prices__big")).getText()
                .replace("&nbsp", "")
                .replaceAll(" ", "")
                .replace("₴", ""));
        driver.navigate().back();
        wait.until(presenceOfElementLocated(By.cssSelector("h1[class='catalog-heading ng-star-inserted']")));
        List<WebElement> findElements1 = driver.findElements(By.className("goods-tile__price-value"));
        IntStream.range(0, findElements1.size())
                .filter(i -> Integer.parseInt(findElements1.get(i).getText().replace(" ","")) < comparedPrice1)
                .findFirst()
                .ifPresent(i -> driver.findElement(By.xpath("(//span[@class='goods-tile__price-value']//preceding::a[2])"+ "[" + i + 3 + "]")).click());
        wait.until(presenceOfElementLocated(By.cssSelector("a[class='tabs__link tabs__link--active']")));
        driver.findElement(By.xpath("//button[@class='compare-button ng-star-inserted']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//rz-icon-counter//child::span")));
        assertTrue(driver.findElement(By.xpath("//rz-icon-counter//child::span")).isDisplayed());
        String monitorTitle2 = driver.findElement(By.cssSelector("h1[class='product__title']")).getText();
        int comparedPrice2 = Integer.parseInt(driver.findElement(By.cssSelector("p.product-prices__big")).getText()
                .replace("&nbsp", "")
                .replaceAll(" ", "")
                .replace("₴", ""));
        driver.findElement(By.xpath("//rz-comparison[@class='header-actions__component']")).click();
        wait.until(presenceOfElementLocated(By.className("modal__header")));
        driver.findElement(By.className("comparison-modal__link")).click();
        List<WebElement> elementNameList = driver.findElements(By.cssSelector("div.product__heading a"));
        assertEquals(monitorTitle1, elementNameList.get(1).getText());
        assertEquals(monitorTitle2, elementNameList.get(0).getText());
        WebElement elementPriceOne = driver.findElement(By.xpath("(//div[@class='product__price--old']//parent::div)[1]"));
        assertEquals(comparedPrice2, Integer.parseInt(elementPriceOne.getText().replaceAll("&nbsp", "")
                .replaceAll(" ", "")
                .replace("₴", "")));
        WebElement elementPriceTwo = driver.findElement(By.xpath("(//div[@class='product__price--old']//parent::div)[3]"));
        assertEquals(comparedPrice1, Integer.parseInt(elementPriceTwo.getText().replaceAll("&nbsp", "")
                .replaceAll(" ", "")
                .replace("₴", "")));
    }
}