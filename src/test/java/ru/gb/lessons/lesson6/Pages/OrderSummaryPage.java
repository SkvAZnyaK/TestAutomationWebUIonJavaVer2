package ru.gb.lessons.lesson6.Pages;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderSummaryPage extends BasicElementsPage {

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement pageHeading;


    @FindBy(xpath = "//span[text()='I confirm my order']")
    private WebElement IConfirmMyOrder;

    @FindBy(xpath = "//div/p/strong[@class='dark']")
    private WebElement confirmation;

    public OrderSummaryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public OrderSummaryPage checkProceedSuccess() {
        assertThat(pageHeading.getText()).as("Не удалось перейти к проверке параметров заказа").isEqualTo("ORDER SUMMARY");
        return this;
    }

    public OrderSummaryPage clickConfirm() {
        IConfirmMyOrder.click();
        return this;
    }

    public OrderSummaryPage checkConfirmation() {
        assertThat(confirmation.getText()).as("Не удалось подтвердить заказ").isEqualTo("Your order on My Store is complete.");
        return this;
    }
}

