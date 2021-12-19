package ru.gb.lessons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hw3_Scripts {

    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Actions actions = new Actions(webDriver);

        Authorization(webDriver);
        PersonalDataChange(webDriver);
        AddToCart(webDriver, actions);
        SendFeedback(webDriver);

        Thread.sleep(10000);
        webDriver.quit();
        // Сознательно ставлю в каждом скрипте Thread.sleep(3000) на ключквых моментах, чтобы
        // можно было понять что действия на странице выполнены. Понимаю, что делать так в реальной работе не надо ))
    }

    private static void Authorization(WebDriver webDriver) throws InterruptedException {
        webDriver.get("http://automationpractice.com/index.php");
        webDriver.manage().window().fullscreen();
        webDriver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        webDriver.findElement(By.id("email")).sendKeys("zaraz4@yandex.ru");
        webDriver.findElement(By.id("passwd")).sendKeys("Cthutq62!");
        webDriver.findElement(By.id("SubmitLogin")).click();
        Thread.sleep(3000);
    }

    private static void PersonalDataChange(WebDriver webDriver) throws InterruptedException {
        webDriver.findElement(By.xpath("//a[@title='Information']")).click();
        webDriver.findElement(By.id("id_gender1")).click();
        webDriver.findElement(By.id("firstname")).clear();
        webDriver.findElement(By.id("firstname")).sendKeys("Sergey");
        webDriver.findElement(By.id("lastname")).clear();
        webDriver.findElement(By.id("lastname")).sendKeys("Semizarov");
        webDriver.findElement(By.id("days")).click();
        webDriver.findElement(By.xpath("//select[@id='days']/option[@value='10']")).click();
        webDriver.findElement(By.id("months")).click();
        webDriver.findElement(By.xpath("//select[@id='months']/option[@value='4']")).click();
        webDriver.findElement(By.id("years")).click();
        webDriver.findElement(By.xpath("//select[@id='years']/option[@value='1983']")).click();
        webDriver.findElement(By.id("old_passwd")).sendKeys("Cthutq62!");
        Thread.sleep(3000);
        webDriver.findElement(By.name("submitIdentity")).click();
    }

    private static void AddToCart(WebDriver webDriver, Actions actions) throws InterruptedException {
        actions.moveToElement(webDriver.findElement(By.xpath("//a[text()='Women']")))
                .build()
                .perform();
        webDriver.findElement(By.xpath("//a[text()='Casual Dresses']")).click();
        List<WebElement> casualDresses = webDriver.findElements(By.xpath("//div[@class='product-container']"));
        actions.moveToElement(casualDresses.get(0))
                .build()
                .perform();
        casualDresses.get(0).findElement(By.xpath("//span[text()='Add to cart']")).click();
        webDriver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//a[@title='Delete']")).click();
    }

    private static void SendFeedback(WebDriver webDriver) throws InterruptedException {
        webDriver.findElement(By.xpath("//a[text()='Contact us']")).click();
        webDriver.findElement(By.id("id_contact")).click();
        webDriver.findElement(By.xpath("//select[@id='id_contact']/option[@value='1']")).click();
        webDriver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Thank you very much? fof such a beautiful source to practice");
        Thread.sleep(3000);
        webDriver.findElement(By.id("submitMessage")).click();
    }


}
