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
        CompareList(webDriver, actions);
        OrderCheckOut(webDriver);
        SendFeedback(webDriver);

        Thread.sleep(10000);
        webDriver.quit();
        // Сознательно ставлю в каждом скрипте Thread.sleep(3000) на ключквых моментах, чтобы
        // можно было понять что действия на странице выполнены. Понимаю, что делать так в реальной работе не надо ))
    }

    private static void Authorization(WebDriver webDriver) throws InterruptedException {
        webDriver.get("http://automationpractice.com/index.php");
        //webDriver.manage().window().fullscreen();
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
        webDriver.findElement(By.xpath("//span[@class='cross']")).click();
        Thread.sleep(3000);
    }

    private static void CompareList(WebDriver webDriver, Actions actions) {
        webDriver.findElement(By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']")).click();
        webDriver.findElement(By.xpath("//div[@class='subcategory-image']/a[@title='Summer Dresses']")).click();
        List<WebElement> summerDresses = webDriver.findElements(By.xpath("//div[@class='product-container']"));
        actions.moveToElement(summerDresses.get(0))
                .build()
                .perform();
        summerDresses.get(0).findElement(By.xpath("//a[@class='add_to_compare']")).click();
        summerDresses.get(1).findElement(By.xpath("//a[@class='add_to_compare']")).click();
        //summerDresses.get(2).findElement(By.xpath("//a[@class='add_to_compare']")).click();
        webDriver.findElement(By.xpath("//div[@class='bottom-pagination-content clearfix']//button[@type='submit']")).click();
        webDriver.findElement(By.xpath("//ul[@class='footer_link']//a[@class='button lnk_view btn btn-default']")).click();
    }

    private static void OrderCheckOut(WebDriver webDriver) {
        webDriver.findElement(By.xpath("//a[@title='View my shopping cart']")).click();
        webDriver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']")).click();
        webDriver.findElement(By.xpath("//option[contains(text(),'My address')]")).click();
        webDriver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Very important blah-blah...");
        webDriver.findElement(By.xpath("//button[@name='processAddress']")).click();
        webDriver.findElement(By.id("cgv")).click();
        webDriver.findElement(By.xpath("//button[@name='processCarrier']")).click();
        webDriver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();
        webDriver.findElement(By.xpath("//span[text()='I confirm my order']")).click();
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
