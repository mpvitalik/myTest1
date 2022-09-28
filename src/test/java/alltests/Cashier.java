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

public class Cashier {
    private WebDriver driver;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver","../myTest1/src/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testCashier() throws NoSuchElementException, InterruptedException {
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

        driver.findElement(By.xpath("//div[@id=\"headerControlPanel\"]/div[1]/div[1]/ul/li[5]/a/span")).click();
        Thread.sleep(10000);

        //Проверка, что касса загрузилась
        driver.switchTo().frame(driver.findElement(By.id("cashIframe")));
        Assert.assertEquals("All payment methods", driver.findElement(By.xpath("//div[@id=\"cabbagino\"]/div[2]/div/h4"))
                .getText());
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
