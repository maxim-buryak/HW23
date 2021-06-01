package HWTask2;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task3 {
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
    public void FindSamsung3() {
        assertEquals(driver.findElements(By.xpath("//rz-icon-counter/span")).size(), 0);
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys("samsung" + Keys.ENTER);
        wait.until(presenceOfElementLocated(By.xpath("//aside[@class='sidebar ng-star-inserted']")));
        driver.findElement(By.xpath("//rz-filter-categories/ul/li[2]/ul/li[1]/a[@href='https://rozetka.com.ua/mobile-phones/c80003/producer=samsung/']")).click();
        wait.until(presenceOfElementLocated(By.xpath("//span[@class='link-dotted']")));
        driver.findElement(By.xpath("//rz-filter-checkbox/ul[1]/li[4]/a/label[@for='Galaxy S']")).click();
        wait.until(presenceOfElementLocated(By.className("goods-tile__title")));
        List<WebElement> findtitle = driver.findElements(By.className("goods-tile__title"));
        for (int i = 0; i < findtitle.size(); i++){
            String samsung ="Galaxy S";
            for (WebElement element : findtitle){
                assertTrue(element.getText().contains(samsung));
            }
        }


    }
}