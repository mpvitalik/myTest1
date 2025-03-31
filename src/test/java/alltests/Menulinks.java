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

public class Menulinks {
    private WebDriver driver;

    @Before
    public void start() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","/Users/vitalii/IdeaProjects/wd/chromedriver");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testMenulinks() throws NoSuchElementException, InterruptedException {

        driver.get("https://bons.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Ожидание до 10 секунд

        WebElement casinoLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/p[text()='Casino']")));
        casinoLink.click();
        WebElement slots = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/span[text()='Slots']")));
        String slotsText = slots.getText();
        Assert.assertEquals("SLOTS", slotsText);

        WebElement ldLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/p[text()='Live Сasino']")));
        ldLink.click();
        Thread.sleep(3000);
        WebElement roulette = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/span[text()='Roulette']")));
        String rouletteText = roulette.getText();
        Assert.assertEquals("ROULETTE", rouletteText);

        WebElement sportsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/p[text()='Sports']")));
        sportsLink.click();
        Thread.sleep(5000);

        //driver.switchTo().frame(driver.findElement(By.id("cashIframe")));

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
