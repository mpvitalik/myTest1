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

public class Gifts {
    private WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","../myTest1/src/driver/chromedriver");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testProfile() throws NoSuchElementException, InterruptedException {
        driver.get("https://bons.com/login");

        // Добавляем cookies, сохранённые в первом классе
        for (Cookie cookie : Auth.cookies) {
            driver.manage().addCookie(cookie);
        }
        Thread.sleep(1000);
        driver.navigate().refresh();  // Обновляем страницу, чтобы авторизация применилась

        driver.navigate().to("https://bons.com/account/promo/promotions");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Ожидание до 10 секунд
        Actions actions = new Actions(driver); // Вместо клика. Для более точного клика

        WebElement activeBonuses = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='Active Bonuses']")));
        actions.moveToElement(activeBonuses).click().perform();
        WebElement activeBonusesTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Bonuses History']")));
        String activeBonusesTitleText = activeBonusesTitle.getText();
        Assert.assertEquals("Bonuses History", activeBonusesTitleText);

        WebElement myPromotions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='My Promotions']")));
        actions.moveToElement(myPromotions).click().perform();
        WebElement myPromotionsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[@class='ui-table__cell'][text()='Status Bonuses']")));
        String myPromotionsTitleText = myPromotionsTitle.getText();
        Assert.assertEquals("Status Bonuses", myPromotionsTitleText);

        WebElement referAFriend = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='Refer a friend']")));
        actions.moveToElement(referAFriend).click().perform();
        WebElement referAFriendTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[contains(text(), 'Friend Invite History')]")));
        String referAFriendTitleText = referAFriendTitle.getText();
        Assert.assertEquals("Friend Invite History:", referAFriendTitleText);

        WebElement achievements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='Achievements']")));
        actions.moveToElement(achievements).click().perform();
        Thread.sleep(3000);
        WebElement achievementsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Statuses']")));
        String achievementsTitleText = achievementsTitle.getText();
        Assert.assertEquals("Statuses", achievementsTitleText);

        WebElement bonusCodes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='Bonus Codes']")));
        actions.moveToElement(bonusCodes).click().perform();
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Submit']")));
        String buttonText = button.getText();
        Assert.assertEquals("Submit", buttonText);





        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);"); // Скроллим вниз на 300 пикселей

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
