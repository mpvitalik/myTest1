package alltests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class Auth {
    private WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","../myTest1/src/driver/chromedriver");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testAuth() throws NoSuchElementException, InterruptedException, IOException {
        driver.get("https://rc.conquestador.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Ожидание до 10 секунд
        WebElement mail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));

        //WebElement mail = driver.findElement(By.id("login"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"page-container\"]/div[1]/div/form/div[1]/div[2]/div/div/span/input"));
        WebElement button = driver.findElement(By.xpath("//*[@id=\"page-container\"]/div[1]/div/form/div[2]/button"));

        mail.click();
        mail.sendKeys("ivanaleksandrov2012+186@gmail.com");
        password.click();
        password.sendKeys("qqq111qqq");
        Thread.sleep(3000);
        button.click();
        Thread.sleep(3000);

        WebElement nickName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='sasha#1259']")));

        //WebElement nickName = driver.findElement(By.xpath("//p[text()='sasha#1259']"));
        String nickNameText = nickName.getText();
        Assert.assertEquals("sasha#1259", nickNameText);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}