package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodPage extends BasicElementsPage{

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement pageHeading;


    @FindBy(xpath = "//a[@title='Pay by bank wire']")
    private WebElement PayByBankWire;

    public PaymentMethodPage(WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public PaymentMethodPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PaymentMethodPage() {
    }

    public PaymentMethodPage checkProceedSuccess (){
        assertThat(pageHeading.getText()).as("Не удалось перейти к выбору метода оплаты").isEqualTo("PLEASE CHOOSE YOUR PAYMENT METHOD");
        return this;
    }

    public OrderSummaryPage clickPayByBankWire(){
        PayByBankWire.click();
        return new OrderSummaryPage(webDriver);
    }
}
