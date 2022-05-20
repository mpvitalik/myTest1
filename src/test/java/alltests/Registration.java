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

public class Registration {
    private WebDriver driver;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver","../myTest1/src/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testRegistration() throws NoSuchElementException, InterruptedException {
        driver.get("https://rc.conquestador.com/en-int/registration?step=1");

        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement button = driver.findElement(By.xpath("//div[@id=\"page-container\"]/form/div[3]/button"));

        int indexMail = 9;
        String login = "ivanaleksandrov2012+";
        String mailDomain = "@gmail.com";

        while (indexMail < 100) {
            indexMail++;
            email.click();
            email.sendKeys(login + indexMail + mailDomain);
            driver.findElement(By.id("email-label")).click();
            Thread.sleep(1000);
            if (!driver.findElement(By.xpath("//div[@id=\"page-container\"]/form/div[2]/div[1]/span"))
                    .getAttribute("className")
                    .equals("ui-text-field__element ui-input ui-input_invalid ui-input_full-width ui-input_state_focus ub-box-szg_border-box")) {

                password.click();
                password.sendKeys("qqq111qqq");
                button.click();
                Thread.sleep(1000);
                break;

            } else email.clear();
        }
        //Проверка, что мы на втором Шаге
        Assert.assertEquals("First Name", driver.findElement(By.id("firstName-label")).getText());

        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement dayOfBirth = driver.findElement(By.id("dayOfBirth"));
        WebElement mobilePhone = driver.findElement(By.id("mobilePhone"));
        WebElement buttonSecondStep = driver.findElement(By.xpath("//div[@id=\"page-container\"]/form/div[3]/button[2]"));

        firstName.click();
        firstName.sendKeys("Sasha");
        lastName.click();
        lastName.sendKeys("Alex");
        dayOfBirth.click();
        dayOfBirth.sendKeys("01031984");
        mobilePhone.click();
        mobilePhone.clear();
        mobilePhone.sendKeys("934563478");
        buttonSecondStep.click();
        Thread.sleep(1000);

        //Проверка, что мы на третьем Шаге
        Assert.assertEquals("Country", driver.findElement(By.id("country-label")).getText());

        WebElement city = driver.findElement(By.id("city"));
        WebElement postcode = driver.findElement(By.id("postcode"));
        WebElement street = driver.findElement(By.id("street"));
        WebElement house = driver.findElement(By.id("house"));
        WebElement checkbox = driver.findElement(By.xpath("//div[@id=\"page-container\"]/form/div[2]/label[1]/input"));
        WebElement buttonFinish = driver.findElement(By.xpath("//div[@id=\"page-container\"]/form/div[3]/button[2]"));

        city.click();
        city.sendKeys("Kiev");
        postcode.click();
        postcode.sendKeys("01001");
        street.click();
        street.sendKeys("Pobedy");
        house.click();
        house.sendKeys("20");
        checkbox.click();
        buttonFinish.click();
        Thread.sleep(3000);

        //Проверка, что мы на странице подтверждения пароля
        Assert.assertEquals("Dear Player", driver.findElement(By.xpath("//div[@id=\"page-container\"]/div[1]/div/h1")).getText());
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
