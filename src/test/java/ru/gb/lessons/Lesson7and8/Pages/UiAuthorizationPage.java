package ru.gb.lessons.Lesson7and8.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UiAuthorizationPage {

    private By loginEmail= By.id("email");
    private By loginPasswd= By.id("passwd");
    private By loginBtn= By.id("SubmitLogin");
    //Попробовал вынести элементы с помощью переменных By. После подключения Селенида не так уж и красиво выходит. Но пусть останется на память)))

    @Step("Ввести в поле Email address логин: {0}")
    public UiAuthorizationPage enterLogin (String email){
        $x("//a[@title='Log in to your customer account']").click();
        $(loginEmail).sendKeys(email);
        return this;
    }

    @Step("Ввести в поле Password пароль: {0}")
    public UiAuthorizationPage enterPassword (String pswd){
        $(loginPasswd).sendKeys(pswd);
        return this;
    }

    @Step("Нажать на кнопку Sign in")
    public PersonalAccountPage clickLoginButton (){
        $(loginBtn).click();
        return page(PersonalAccountPage.class);
    }

    @Step("Убедиться что в поле Email address введен верный логин")
    public UiAuthorizationPage checkLoginIsCorrect (){
        assertThat($(loginEmail).getAttribute("value")).as("Не верный логин").isEqualTo("zaraz4@yandex.ru");
        return this;
    }

    @Step("Убедиться что в поле Password введен верный пароль")
    public UiAuthorizationPage checkPasswordIsCorrect (){
        assertThat($(loginPasswd).getAttribute("value")).as("Не верный пароль").isEqualTo("Cthutq62!");
        return this;
    }
}