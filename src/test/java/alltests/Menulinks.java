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

public class Menulinks {
    private WebDriver driver;

    @Before
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver","../myTest1/src/driver/chromedriver");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testMenulinks() throws NoSuchElementException, InterruptedException {

        driver.get("https://rc.conquestador.com/en-int/");

        WebElement agreeButton = driver.findElement(By.cssSelector("div.notification-list > div > div.ui-notification-button-box.ub-box-szg_border-box > button"));
        agreeButton.click();
        Thread.sleep(1000);

        WebElement slots = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/a[3]/span"));
        String slotsText = slots.getText();
        Assert.assertEquals("SLOTS", slotsText);

        WebElement menuButton = driver.findElement(By.xpath("//div[@id=\"headerControlPanel\"]/div[1]/div[2]/button"));
        menuButton.click();
        Thread.sleep(1000);

        WebElement liveDealers = driver.findElement(By.xpath("//div[@id=\"headerControlPanelPlaceholder\"]/header/div[1]/div[2]/div/ul/li[3]/a"));
        liveDealers.click();
        Thread.sleep(3000);

        WebElement roulette = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/a[3]/span"));
        String rouletteText = roulette.getText();
        Assert.assertEquals("ROULETTE", rouletteText);

        menuButton.click();
        Thread.sleep(1000);
        WebElement promotions = driver.findElement(By.xpath("//div[@id=\"headerControlPanelPlaceholder\"]/header/div[1]/div[2]/div/ul/li[4]/a"));
        promotions.click();
        Thread.sleep(1000);

        WebElement tournaments = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/ul/li[2]/a"));
        tournaments.click();
        Thread.sleep(1000);

        WebElement upcomingTournaments = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div/div[2]/h2"));
        String upcomingTournamentsText = upcomingTournaments.getText();
        Assert.assertEquals("Upcoming tournaments:", upcomingTournamentsText);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
