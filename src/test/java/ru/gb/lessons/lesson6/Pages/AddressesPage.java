package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressesPage extends BasicElementsPage{

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement pageHeading;

    @FindBy(xpath = "//button[@name='processAddress']")
    private WebElement processAddressBtn;

    public AddressesPage(WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public AddressesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public AddressesPage() {
    }

    public AddressesPage checkProceedToAddress(){
        assertThat(pageHeading.getText()).as("Не удалось перейти к заполнению адреса").isEqualTo("ADDRESSES");
        return this;
    }

    public AddressesPage clickMyAddress(){
        webDriver.findElement(By.xpath("//option[contains(text(),'My address')]")).click();
        return this;
    }

    public AddressesPage typeSomeComments (String someCommentsToProceed){
        webDriver.findElement(By.xpath("//textarea[@name='message']")).sendKeys(someCommentsToProceed);
        return this;
    }

    public ShippingPage clickProceed(){
        processAddressBtn.click();
        return new ShippingPage(webDriver);
    }
}
