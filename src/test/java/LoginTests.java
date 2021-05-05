import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
public class LoginTests {

    WebDriver driver;

    @BeforeClass (alwaysRun = true)   // this method will run only once before everything else in this class
    /// any logic that you must execute only once before everything in the class
    public void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Browser drivers\\chromedriver.exe");

    }

    @BeforeMethod (alwaysRun = true)   //   // this method will run  before each @Test method this class
    public void setUpMethod(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
    }


    @Test (groups = {"smoke"})
    public void positiveLogin(){

        // Enter the correct credentials
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

        // Verify that the login was successful
        String expectedURL = "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/";
        assertEquals(driver.getCurrentUrl(), expectedURL);

    }

    @Test (groups = { "login"})
    public void negativeTest(){
        // Enter the incorrect credentials
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Hello");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("world");
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();
        // Verify that the login is unsuccessful by verifying the error message
        String expectedMessage = "Invalid Login or Password.";
        WebElement errorMessage = driver.findElement(By.id("ctl00_MainContent_status"));
        assertTrue(errorMessage.isDisplayed());
        assertEquals(errorMessage.getText(), expectedMessage);
    }


    @Test (groups = { "login"})
    public void negativeTest2_UI(){
        // Enter the correct username and incorrect password
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("world");
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

        // Verify that the login is unsuccessful by verifying the error message
        String expectedMessage = "Invalid Login or Password.";
        WebElement errorMessage = driver.findElement(By.id("ctl00_MainContent_status"));
        assertTrue(errorMessage.isDisplayed());
        assertEquals(errorMessage.getText(), expectedMessage);

    }

    @Test (groups = {"smoke"})
    public void negativeTest3(){
        // Omit the credentials
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();
        // Verify that the login is unsuccessful by verifying the error message
        String expectedMessage = "Invalid Login or Password.";
        WebElement errorMessage = driver.findElement(By.id("ctl00_MainContent_status"));
        assertTrue(errorMessage.isDisplayed());
        assertEquals(errorMessage.getText(), expectedMessage);
    }



    @AfterClass (alwaysRun = true) //this method will run only once after all @Test methods have finished executing
    public void tearDownClass(){

    }

    @AfterMethod (alwaysRun = true) // // this method will run  after each @Test method this class
    public void tearDownMethod(){
        driver.quit();
    }


        // TestNG test components hierarchy
        // Suite -> Test -> Class > Method

}
