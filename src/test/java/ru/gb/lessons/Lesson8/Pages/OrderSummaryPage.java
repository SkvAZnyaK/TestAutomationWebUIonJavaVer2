package ru.gb.lessons.Lesson8.Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderSummaryPage extends BasicElementsPage {

    private SelenideElement pageHeading = $x("//h1[@class='page-heading']");
    private SelenideElement IConfirmMyOrder = $x("//span[text()='I confirm my order']");
    private SelenideElement confirmation = $x("//div/p/strong[@class='dark']");


    @Step("Убедиться что открылась страница проверки данных заказа")
    public OrderSummaryPage checkProceedToOrderSummary() {
        assertThat(pageHeading.getText()).as("Не удалось перейти к проверке параметров заказа").isEqualTo("ORDER SUMMARY");
        return this;
    }

    @Step("Нажать кнопку I confirm my order")
    public OrderSummaryPage clickConfirm() {
        IConfirmMyOrder.click();
        return this;
    }

    @Step("Убедиться что страница с вариантами оплаты закрылась")
    public OrderSummaryPage checkConfirmation() {
        assertThat(confirmation.getText()).as("Не удалось подтвердить заказ").isEqualTo("Your order on My Store is complete.");
        return this;
    }
}

