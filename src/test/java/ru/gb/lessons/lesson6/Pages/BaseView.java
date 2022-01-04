package ru.gb.lessons.lesson6.Pages;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseView {

    protected WebDriver webDriver = WebDriverManager.chromedriver().create();
    //protected WebDriverWait webDriverWait = new WebDriverWait(webDriver, 7);
    protected Actions actions = new Actions(webDriver);

    public BaseView (WebDriver webDriver, Actions actions){
        this.webDriver = webDriver;
        this.actions = actions;
        PageFactory.initElements(webDriver,this);
    }

    public BaseView (WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public BaseView (){
        PageFactory.initElements(webDriver,this);
    }


        //webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
}
