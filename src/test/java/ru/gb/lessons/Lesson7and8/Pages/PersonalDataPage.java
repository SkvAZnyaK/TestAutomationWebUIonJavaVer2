package ru.gb.lessons.Lesson7and8.Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDataPage {

    private SelenideElement pageHeading = $x("//h1[@class='page-subheading']");
    private SelenideElement genderCheckbox = $(By.id("id_gender1"));
    private SelenideElement firstNameInput = $(By.id("firstname"));
    private SelenideElement lastNameInput = $(By.id("lastname"));
    private SelenideElement daysTeardown = $(By.id("days"));
    private SelenideElement monthsTeardown = $(By.id("months"));
    private SelenideElement yearsTeardown = $(By.id("years"));
    private SelenideElement daysValue = $x("//select[@id='days']/option[@value='10']");
    private SelenideElement monthsValue = $x("//select[@id='months']/option[@value='4']");
    private SelenideElement yearsValue = $x("//select[@id='years']/option[@value='1983']");
    private SelenideElement oldPswdInput = $(By.id("old_passwd"));
    private SelenideElement saveBtn = $(By.name("submitIdentity"));
    private SelenideElement successAllert = $x("//p[@class='alert alert-success']");

    @Step("Убедиться что открылась страница персональных данных")
    public PersonalDataPage checkIfPersonalDataPageIsAvailable() {
        assertThat(pageHeading.getText()).as("Не у далось войти в раздел персональных данных").isEqualTo("YOUR PERSONAL INFORMATION");
        return this;
    }

    @Step("Установить чекбокс на значение Mr.")
    public PersonalDataPage setGender(){
        genderCheckbox.click();
        return this;
    }

    @Step("Очистить поле First name и ввести значение Sergey")
    public PersonalDataPage setFirstname(String name){
        firstNameInput.clear();
        firstNameInput.sendKeys(name);
        return this;
    }

    @Step("Очистить поле Last name и ввести значение Semizarov")
    public PersonalDataPage setLastname(String sureName){
        lastNameInput.clear();
        lastNameInput.sendKeys(sureName);
        return this;
    }

    @Step("Очистить поля Date of Birth и ввести значения 10 April 1983")
    public PersonalDataPage setBirthdayDate(){
        daysTeardown.click();
        daysValue.click();
        monthsTeardown.click();
        monthsValue.click();
        yearsTeardown.click();
        yearsValue.click();
        return this;
    }

    @Step("Ввести в поле Current Password пароль {0}")
    public PersonalDataPage enterOldPassword(String pswd){
        oldPswdInput.sendKeys(pswd);
        return this;
    }

    @Step("Нажать кнопку Save")
    public PersonalAccountPage clickSaveButton(){
        saveBtn.click();
        assertThat(successAllert.getText()).as("Персональные данные не корректны").isEqualTo("Your personal information has been successfully updated.");
        return page(PersonalAccountPage.class);
    }


    @Step("Убедиться что в поле First name введено значение Sergey")
    public PersonalDataPage checkFirstname(String name){
        assertThat(firstNameInput.getAttribute("value")).isEqualTo(name);
        return this;
    }

    @Step("Убедиться что в поле Last name введено значение Semizarov")
    public PersonalDataPage checkLastname (String sureName){
        assertThat(lastNameInput.getAttribute("value")).isEqualTo(sureName);
        return this;
    }

    @Step("Убедиться что в полях Date of Birth введены значения 10 April 1983")
    public PersonalDataPage checkBirthdayDate(){
        assertThat(daysTeardown.getAttribute("value")).isEqualTo("10");
        assertThat(monthsTeardown.getAttribute("value")).isEqualTo("4");
        assertThat(yearsTeardown.getAttribute("value")).isEqualTo("1983");
        return this;
    }

    @Step("Убедиться что в поле Current Password введен пароль {0}")
    public PersonalDataPage checkOldPassword(String pswd) {
        assertThat(oldPswdInput.getAttribute("value")).isEqualTo(pswd);
        return this;
    }
}