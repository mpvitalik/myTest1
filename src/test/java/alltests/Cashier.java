package alltests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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
        Thread.sleep(2000);

        // Добавляем cookies, сохранённые в первом классе
        for (Cookie cookie : Auth.cookies) {
            driver.manage().addCookie(cookie);
        }

        driver.navigate().refresh();  // Обновляем страницу, чтобы авторизация применилась
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Ожидание до 10 секунд
        Actions actions = new Actions(driver); // Вместо клика. Для более точного клика

        // Переходим в кассу
        WebElement depositButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[text()='Deposit']")));
        depositButton.click();
        //driver.findElement(By.xpath("//div[@id=\"headerControlPanel\"]/div[1]/div[1]/ul/li[5]/a/span")).click();
        Thread.sleep(3000);

        //Проверка, что касса загрузилась
        WebElement cashierName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='All payment methods']")));
        String cashierNameText = cashierName.getText();
        Assert.assertEquals("All payment methods", cashierNameText);

        WebElement pendingPayoutRequests = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Pending payout requests']")));
        actions.moveToElement(pendingPayoutRequests).click().perform();
        WebElement pendingPayoutRequestsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='date of request']")));
        String pendingPayoutRequestsTitleText = pendingPayoutRequestsTitle.getText();
        Assert.assertEquals("date of request", pendingPayoutRequestsTitleText);

        WebElement transactionsHistory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Transactions history']")));
        actions.moveToElement(transactionsHistory).click().perform();
        WebElement transactionsHistoryTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div/p[text()='From']")));
        String transactionsHistoryTitleText = transactionsHistoryTitle.getText();
        Assert.assertEquals("From", transactionsHistoryTitleText);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
