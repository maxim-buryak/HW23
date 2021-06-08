package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class RozetkaHomePage {
    WebDriver driver;
    WebDriverWait wait;
    By ElementAssert = By.xpath("//rz-icon-counter/span");
    By CatalogInput = By.xpath("//button[@id='fat-menu']");
    By WaitElement = By.xpath("//rz-header/header/div/div/rz-header-fat-menu/button");
    By MonitorsInput = By.xpath("a[href='https://hard.rozetka.com.ua/monitors/c80089/'][class=menu__link]");
    public RozetkaHomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void SelectMonitors(){
        assertEquals(driver.findElements(ElementAssert).size(), 0);
        driver.findElement(CatalogInput).click();
        wait.until(presenceOfElementLocated(WaitElement));
        driver.findElement(MonitorsInput).click();
    }

}
