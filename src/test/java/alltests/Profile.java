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
        driver.get("https://bons.com/login");

        // Добавляем cookies, сохранённые в первом классе
        for (Cookie cookie : Auth.cookies) {
            driver.manage().addCookie(cookie);
        }
        Thread.sleep(1000);
        driver.navigate().refresh();  // Обновляем страницу, чтобы авторизация применилась

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Ожидание до 10 секунд
        Actions actions = new Actions(driver); // Вместо клика. Для более точного клика

        WebElement nickName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='ivano']")));
        actions.moveToElement(nickName).click().perform();
        WebElement profileTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='Personal information']")));
        String profileTitleText = profileTitle.getText();
        Assert.assertEquals("Personal information", profileTitleText);

        WebElement balance = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-account-menu__main_item']/p[text()='Balance']")));
        actions.moveToElement(balance).click().perform();
        WebElement balanceTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-balance__group']/p[text()='Balance']")));
        String balanceTitleText = balanceTitle.getText();
        Assert.assertEquals("Balance", balanceTitleText);

        WebElement verification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='Verification']")));
        actions.moveToElement(verification).click().perform();
        Thread.sleep(1000);
        WebElement verificationPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/a[1]/div")));
        actions.moveToElement(verificationPhone).click().perform();
        WebElement verificationPhoneTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-verification-phone']/p[text()='Mobile Verification']")));
        String verificationPhoneTitleText = verificationPhoneTitle.getText();
        Assert.assertEquals("Mobile Verification", verificationPhoneTitleText);

        WebElement documents = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/a[2]/div")));
        actions.moveToElement(documents).click().perform();
        Thread.sleep(1000);

        WebElement documentsNationalId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/a[1]/div")));
        actions.moveToElement(documentsNationalId).click().perform();
        WebElement documentsNationalIdTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='National passport']")));
        String documentsNationalIdTitleText = documentsNationalIdTitle.getText();
        Assert.assertEquals("National passport", documentsNationalIdTitleText);

        WebElement documentsDrivingLicence = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/a[2]/div")));
        actions.moveToElement(documentsDrivingLicence).click().perform();
        WebElement documentsDrivingLicenceTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-verification-upload']/p[text()='Driving licence']")));
        String documentsDrivingLicenceTitleText = documentsDrivingLicenceTitle.getText();
        Assert.assertEquals("Driving licence", documentsDrivingLicenceTitleText);

        WebElement documentsPassport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/div/a[3]/div")));
        actions.moveToElement(documentsPassport).click().perform();
        WebElement documentsPassportTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-verification-upload']/p[text()='Passport']")));
        String documentsPassportTitleText = documentsPassportTitle.getText();
        Assert.assertEquals("Passport", documentsPassportTitleText);

        WebElement verificationOtherDocuments = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[3]/div/a[3]/div")));
        actions.moveToElement(verificationOtherDocuments).click().perform();
        WebElement verificationOtherDocumentsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-verification-upload']/p[text()='Other Documents']")));
        String verificationOtherDocumentsTitleText = verificationOtherDocumentsTitle.getText();
        Assert.assertEquals("Other Documents", verificationOtherDocumentsTitleText);

        WebElement security = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-account-menu__main_item']/p[text()='Security']")));
        actions.moveToElement(security).click().perform();
        WebElement securityTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-security']/p[text()='Security']")));
        String securityTitleText = securityTitle.getText();
        Assert.assertEquals("Security", securityTitleText);

        WebElement privacy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-account-menu__main_item']/p[text()='Privacy']")));
        actions.moveToElement(privacy).click().perform();
        WebElement privacyTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'profile-subscriptions')]/p[text()='Privacy']")));
        String privacyTitleText = privacyTitle.getText();
        Assert.assertEquals("Privacy", privacyTitleText);

        WebElement history = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-account-menu__main_item profile-account-menu__main_sub']/p[text()='History']")));
        actions.moveToElement(history).click().perform();
        Thread.sleep(1000);
        WebElement historyTransactionsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-history-transactions__container']/p[text()='Transactions']")));
        String historyTransactionsTitleText = historyTransactionsTitle.getText();
        Assert.assertEquals("Transactions", historyTransactionsTitleText);

        WebElement historyGames = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[6]/div/a[2]/div")));
        actions.moveToElement(historyGames).click().perform();
        WebElement historyGamesTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-history-games__container']/p[text()='Games']")));
        String historyGamesTitleText = historyGamesTitle.getText();
        Assert.assertEquals("Games", historyGamesTitleText);

        WebElement historyNotifications = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"page-container\"]/div[1]/div[2]/div[1]/div/div[6]/div/a[3]/div")));
        actions.moveToElement(historyNotifications).click().perform();
        Thread.sleep(1000);
        WebElement historyNotificationsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='profile-history-notifications__container']/p[text()='Notifications']")));
        String historyNotificationsTitleText = historyNotificationsTitle.getText();
        Assert.assertEquals("Notifications", historyNotificationsTitleText);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
