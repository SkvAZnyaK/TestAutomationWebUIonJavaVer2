package ru.gb.lessons.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.gb.lessons.lesson6.Listener.ActionEventListener;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(AllureJunit5.class)
public class BasicTest {

    EventFiringWebDriver webDriver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeEach
    void setUp(){
        webDriver = new EventFiringWebDriver(WebDriverManager.chromedriver().create());
        webDriver.register(new ActionEventListener());
        webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 7);
        actions = new Actions(webDriver);
    }

    @AfterEach
    void tearDown(){
        for (LogEntry logEntry : webDriver.manage().logs().get(LogType.BROWSER)){
            Allure.addAttachment("Console log", logEntry.getMessage());
        }
        webDriver.quit();
    }
}