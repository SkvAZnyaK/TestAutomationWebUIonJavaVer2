package ru.gb.lessons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class BasicTest {

    WebDriver webDriver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeEach
    void setUp(){
        webDriver = WebDriverManager.chromedriver().create();
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 7);
        actions = new Actions(webDriver);

        webDriver.get("http://automationpractice.com/index.php");
        webDriver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        WebElement login = webDriver.findElement(By.id("email"));
        login.sendKeys("zaraz4@yandex.ru");
        WebElement password = webDriver.findElement(By.id("passwd"));
        password.sendKeys("Cthutq62!");
        webDriver.findElement(By.id("SubmitLogin")).click();
    }

    @AfterEach
    void tearDown(){
        webDriver.quit();
    }
}