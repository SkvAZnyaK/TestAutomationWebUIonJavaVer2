package ru.gb.lessons.lesson6.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;

public class UiAuthorizationPage extends BasicElementsPage{

    private By loginEmail= By.id("email");
    private By loginPasswd= By.id("passwd");
    private By loginBtn= By.id("SubmitLogin");
    //Попробовал вынести элементы с помощью переменных By.

    public UiAuthorizationPage(WebDriver webDriver){
        super(webDriver);
    }

    @Step("Ввести в поле Email address логин: {0}")
    public UiAuthorizationPage enterLogin (String email){
        webDriver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
        webDriver.findElement(loginEmail).sendKeys(email);
        return this;
    }

    @Step("Ввести в поле Password пароль: {0}")
    public UiAuthorizationPage enterPassword (String pswd){
        webDriver.findElement(loginPasswd).sendKeys(pswd);
        return this;
    }

    @Step("Нажать на кнопку Sign in")
    public PersonalAccountPage clickLoginButton (){
        webDriver.findElement(loginBtn).click();
        return new PersonalAccountPage(webDriver);
    }

    @Step("Убедиться что в поле Email address введен верный логин")
    public UiAuthorizationPage checkLoginIsCorrect (){
        assertThat(webDriver.findElement(loginEmail).getAttribute("value")).as("Не верный логин").isEqualTo("zaraz4@yandex.ru");
        return this;
    }

    @Step("Убедиться что в поле Password введен верный пароль")
    public UiAuthorizationPage checkPasswordIsCorrect (){
        assertThat(webDriver.findElement(loginPasswd).getAttribute("value")).as("Не верный пароль").isEqualTo("Cthutq62!");
        return this;
    }
}
