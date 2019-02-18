import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class simpleTest {

    private static WebDriver driver;

    @BeforeClass

    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/yaroslav/Work/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get("https://www.delivery-club.ru/");
    }

    @Test

    public void SearchSpace() {
        WebElement loginButton = driver.findElement(By.xpath("//*[@id='authorization-app']/div/button"));
        loginButton.click();
        WebElement phoneSpace = driver.findElement(By.xpath("//*[@id='authorization-app']/div/div/div[2]/div[2]/div/form/label/input"));
        phoneSpace.sendKeys("88888888888");//enter a incorrect phone-number
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement enterButton = driver.findElement(By.xpath("//*[@id='authorization-app']/div/div/div[2]/div[2]/div/form/button"));
        enterButton.click();
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id='authorization-app']/div/div/div[2]/div[2]/div/form/div[1]"));
        String error = errorMessage.getText();
        Assert.assertEquals("Нажимая на кнопку «Войти», вы принимаете условия пользовательского соглашения", error);

    }

    @AfterClass

    public static void tearDown(){

        driver.quit();
    }
}
