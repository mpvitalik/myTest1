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

public class Logout {
    private WebDriver driver;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver","../myTest1/src/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testLogout() throws NoSuchElementException, InterruptedException {
        driver.get("https://rc.conquestador.com/en-int/login");

        WebElement mail = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div/form/div[1]/div[1]/div/span/input"));
        WebElement password = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div/form/div[1]/div[2]/div/span/input"));
        WebElement button = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div/form/div[2]/div/div/button"));

        mail.click();
        mail.sendKeys("ivanaleksandrov2012@gmail.com");
        password.click();
        password.sendKeys("qqq111qqq");
        button.click();
        Thread.sleep(3000);

        WebElement menuButton = driver.findElement(By.xpath("//div[@id=\"headerControlPanel\"]/div[1]/div[2]/button"));
        menuButton.click();
        Thread.sleep(1000);
        WebElement logoutLink = driver.findElement(By.xpath("//*[@id=\"headerControlPanelPlaceholder\"]/header/div[1]/div[2]/div/div/span[2]"));
        logoutLink.click();
        Thread.sleep(1000);

        WebElement signInLink = driver.findElement(By.xpath("//*[@id=\"headerControlPanel\"]/div[1]/div[1]/ul/li[1]/a"));
        String signInLinkText = signInLink.getText();
        Assert.assertEquals("Sign In", signInLinkText);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
