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
        System.setProperty("webdriver.chrome.driver","/Users/vitalii/IdeaProjects/wd/chromedriver");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testAuth() throws NoSuchElementException, InterruptedException, IOException {
        driver.get("https://bons.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Ожидание до 10 секунд

        WebElement mail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/span/input[@name='password']")));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/button[text()='Sign in']")));

        mail.click();
        mail.sendKeys("ivanaleksandrov2012@gmail.com");
        password.click();
        password.sendKeys("qqq111qqq");
        Thread.sleep(3000);
        button.click();
        Thread.sleep(3000);

        WebElement nickName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='ivano']")));

        String nickNameText = nickName.getText();
        Assert.assertEquals("ivano", nickNameText);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}