import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchTest {

WebDriver driver;

    @BeforeMethod
    public void setup() {
    WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }


    @Test
    public void googleSearch() {
        driver.findElement(By.name("q")).sendKeys("Samoyed" + Keys.ENTER);
        Assert.assertTrue(driver.getTitle().contains("Samoyed"));
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }








}
