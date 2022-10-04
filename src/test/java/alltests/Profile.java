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

public class Profile {
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

        WebElement nickName = driver.findElement(By.xpath("//div[@id=\"headerControlPanel\"]/div[1]/div[1]/ul/li[1]/a"));
        nickName.click();
        Thread.sleep(1000);

        WebElement profileTitle = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[2]/h2"));
        String profileTitleText = profileTitle.getText();
        Assert.assertEquals("Profile", profileTitleText);

        WebElement balanceTab = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[2]/a/div/p"));
        balanceTab.click();
        Thread.sleep(1000);
        WebElement balanceTitle = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[2]/div/div[1]/h2"));
        String balanceTitleText = balanceTitle.getText();
        Assert.assertEquals("Balance", balanceTitleText);

        WebElement verificationTab = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/a/div/p"));
        verificationTab.click();
        Thread.sleep(1000);
        WebElement mobileVerificationTitle = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[2]/div/div/h2"));
        String mobileVerificationTitleText = mobileVerificationTitle.getText();
        Assert.assertEquals("Mobile Verification", mobileVerificationTitleText);

        WebElement documentsTab = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/a[2]/div/span"));
        documentsTab.click();
        Thread.sleep(1000);
        WebElement documentsTitle = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[2]/div/div/h2"));
        String documentsTitleText = documentsTitle.getText();
        Assert.assertEquals("Verify your account", documentsTitleText);

        WebElement passportTab = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/a[1]/div/span"));
        passportTab.click();
        Thread.sleep(1000);
        WebElement passportTitle = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[2]/div/div/h2"));
        String passportTitleText = passportTitle.getText();
        Assert.assertEquals("Passport", passportTitleText);

        WebElement drivingLicenceTab = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/a[2]/div/span"));
        drivingLicenceTab.click();
        Thread.sleep(1000);
        WebElement drivingLicenceTitle = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[2]/div/div/h2"));
        String drivingLicenceTitleText = drivingLicenceTitle.getText();
        Assert.assertEquals("Driving licence", drivingLicenceTitleText);

        WebElement nationalPassportTab = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/a[3]/div/span"));
        nationalPassportTab.click();
        Thread.sleep(1000);
        WebElement nationalPassportTitle = driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[2]/div/div/h2"));
        String nationalPassportTitleText = nationalPassportTitle.getText();
        Assert.assertEquals("National passport", nationalPassportTitleText);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
