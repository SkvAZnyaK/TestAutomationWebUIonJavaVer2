package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class ShippingPage extends BasicElementsPage{

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement pageHeading;

    @FindBy(id = "cgv")
    private WebElement cgvCheckBtn;

    @FindBy(xpath = "//button[@name='processCarrier']")
    private WebElement proceedBtn;

    public ShippingPage(WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public ShippingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ShippingPage() {
    }

    public ShippingPage checkProceedToShipping(){
        assertThat(pageHeading.getText()).as("Не удалось перейти к выбору метода доставки").isEqualTo("SHIPPING");
        return this;
    }

    public ShippingPage clickAgree(){
        cgvCheckBtn.click();
        return this;
    }

    public PaymentMethodPage clickProceed(){
        proceedBtn.click();
        return new PaymentMethodPage(webDriver);
    }
}
