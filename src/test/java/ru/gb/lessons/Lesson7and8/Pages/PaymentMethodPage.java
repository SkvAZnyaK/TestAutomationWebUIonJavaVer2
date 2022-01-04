package ru.gb.lessons.Lesson7and8.Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodPage {

    private SelenideElement pageHeading = $x("//h1[@class='page-heading']");
    private SelenideElement PayByBankWire = $x("//a[@title='Pay by bank wire']");

    @Step("Убедиться что открылась страница с вариантами оплаты")
    public PaymentMethodPage checkProceedToPaymentMethod(){
        assertThat(pageHeading.getText()).as("Не удалось перейти к выбору метода оплаты").isEqualTo("PLEASE CHOOSE YOUR PAYMENT METHOD");
        return this;
    }

    @Step("Нажать Pay By Bank Wire")
    public OrderSummaryPage clickPayByBankWire(){
        PayByBankWire.click();
        return page(OrderSummaryPage.class);
    }
}
