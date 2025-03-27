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

import java.time.Duration;

public class Cashier {
    private WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","/Users/vitalii/IdeaProjects/wd/chromedriver");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testCashier() throws NoSuchElementException, InterruptedException {
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

        // Переходим в кассу
        WebElement depositButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Deposit']")));
        depositButton.click();
        //driver.findElement(By.xpath("//div[@id=\"headerControlPanel\"]/div[1]/div[1]/ul/li[5]/a/span")).click();
        Thread.sleep(3000);

        //Проверка, что касса загрузилась
        WebElement cashierName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='All payment methods']")));
        String cashierNameText = cashierName.getText();
        Assert.assertEquals("All payment methods", cashierNameText);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
