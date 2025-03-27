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

public class Logout {
    private WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","/Users/vitalii/IdeaProjects/wd/chromedriver");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testLogout() throws NoSuchElementException, InterruptedException {
        driver.get("https://bons.com/login");

        // Добавляем cookies, сохранённые в первом классе
        for (Cookie cookie : BetaTest.cookies) {
            driver.manage().addCookie(cookie);
        }
        Thread.sleep(1000);
        driver.navigate().refresh();  // Обновляем страницу, чтобы авторизация применилась

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Ожидание до 10 секунд

        WebElement menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/button[@aria-label='Menu']")));
        menuButton.click();
        Thread.sleep(1000);
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/span[text()='Log out']")));
        logoutLink.click();
        Thread.sleep(1000);

        // Проверяем, что вышли
        WebElement signUpLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Sign Up']")));
        String signUpLinkText = signUpLink.getText();
        Assert.assertEquals("Sign Up", signUpLinkText);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
