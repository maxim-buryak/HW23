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
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

    public class Task2 {
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
        public void FindSamsung2() {
            assertEquals(driver.findElements(By.xpath("//rz-icon-counter/span")).size(), 0);
            driver.findElement(By.xpath("//input[@name='search']")).sendKeys("samsung" + Keys.ENTER);
            wait.until(presenceOfElementLocated(By.xpath("//aside[@class='sidebar ng-star-inserted']")));
            By byMinPrice = By.cssSelector("input[formcontrolname=min]");
            By byMaxPrice = By.cssSelector("input[formcontrolname=max]");
            By bySubmit = By.xpath("(//button[@type='submit'])[1]");

            driver. findElement(byMinPrice).clear();
            driver.findElement(byMinPrice).sendKeys("5000");
            driver.findElement(byMaxPrice).clear();
            driver.findElement(byMaxPrice).sendKeys("15000");

            driver.findElement(bySubmit).click();
            wait.until(elementToBeClickable(bySubmit));

            List<WebElement> elementList = driver.findElements(By.cssSelector("span.goods-tile__price-value"));
            for(WebElement element : elementList){
                int goodPrice = Integer.parseInt(element.getText().replace("&nbsp", "").replaceAll(" ", ""));
                assertTrue(goodPrice > 5000 || goodPrice < 15000);



        }


    }

}
