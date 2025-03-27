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

        // Добавляем cookies, сохранённые в первом классе
        for (Cookie cookie : Auth.cookies) {
            driver.manage().addCookie(cookie);
        }
        Thread.sleep(1000);
        driver.navigate().refresh();  // Обновляем страницу, чтобы авторизация применилась

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Ожидание до 10 секунд

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
