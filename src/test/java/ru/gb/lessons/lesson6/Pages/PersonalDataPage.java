package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDataPage extends BasicElementsPage{

    public PersonalDataPage (WebDriver webDriver) {
        super(webDriver);
    }

    public PersonalDataPage checkIfPersonalDataPageIsAvailable() {
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-subheading']")).getText()).as("Не у далось войти в раздел персональных данных").isEqualTo("YOUR PERSONAL INFORMATION");
        return this;
    }

    public PersonalDataPage setGender(){
        webDriver.findElement(By.id("id_gender1")).click();
        return this;
    }

    public PersonalDataPage setFirstname(String name){
        webDriver.findElement(By.id("firstname")).clear();
        webDriver.findElement(By.id("firstname")).sendKeys(name);
        return this;
    }

    public PersonalDataPage setLastname(String sureName){
        webDriver.findElement(By.id("lastname")).clear();
        webDriver.findElement(By.id("lastname")).sendKeys(sureName);
        return this;
    }

    public PersonalDataPage setBirthdayDate(){
        webDriver.findElement(By.id("days")).click();
        webDriver.findElement(By.xpath("//select[@id='days']/option[@value='10']")).click();
        webDriver.findElement(By.id("months")).click();
        webDriver.findElement(By.xpath("//select[@id='months']/option[@value='4']")).click();
        webDriver.findElement(By.id("years")).click();
        webDriver.findElement(By.xpath("//select[@id='years']/option[@value='1983']")).click();
        return this;
    }

    public PersonalDataPage enterOldPassword(String pswd){
        webDriver.findElement(By.id("old_passwd")).sendKeys(pswd);
        return this;
    }

    public PersonalAccountPage clickSaveButton(){
        webDriver.findElement(By.name("submitIdentity")).click();
        assertThat(webDriver.findElement(By.xpath("//p[@class='alert alert-success']")).getText()).as("Персональные данные не корректны").isEqualTo("Your personal information has been successfully updated.");
        return new PersonalAccountPage(webDriver);
    }


    public PersonalDataPage checkFirstname(String name){
        assertThat(webDriver.findElement(By.id("firstname")).getAttribute("value")).isEqualTo(name);
        return this;
    }

    public PersonalDataPage checkLastname (String sureName){
        assertThat(webDriver.findElement(By.id("lastname")).getAttribute("value")).isEqualTo(sureName);
        return this;
    }

    public PersonalDataPage checkBirthdayDate(){
        assertThat(webDriver.findElement(By.id("days")).getAttribute("value")).isEqualTo("10");
        assertThat(webDriver.findElement(By.id("months")).getAttribute("value")).isEqualTo("4");
        assertThat(webDriver.findElement(By.id("years")).getAttribute("value")).isEqualTo("1983");
        return this;
    }

    public PersonalDataPage checkOldPassword(String pswd) {
        assertThat(webDriver.findElement(By.id("old_passwd")).getAttribute("value")).isEqualTo(pswd);
        return this;
    }
}
