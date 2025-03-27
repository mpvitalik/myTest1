package alltests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
