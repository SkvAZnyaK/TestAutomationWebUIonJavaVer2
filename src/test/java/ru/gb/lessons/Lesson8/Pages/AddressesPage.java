package ru.gb.lessons.Lesson8.Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressesPage extends BasicElementsPage {

    private SelenideElement pageHeading = $x("//h1[@class='page-heading']");
    private SelenideElement processAddressBtn = $x("//button[@name='processAddress']");

    @Step("Убедиться что загрузилась страница заполнения адреса")
    public AddressesPage checkProceedToAddress(){
        assertThat(pageHeading.getText()).as("Не удалось перейти к заполнению адреса").isEqualTo("ADDRESSES");
        return this;
    }

    @Step("В строке Choose a delivery address выбрать My address")
    public AddressesPage clickMyAddress(){
        $x("//option[contains(text(),'My address')]").click();
        return this;
    }

    @Step("В строке обратной связи ввести текст {0}")
    public AddressesPage typeSomeComments (String someCommentsToProceed){
        $x("//textarea[@name='message']").sendKeys(someCommentsToProceed);
        return this;
    }

    @Step("Нажать кнопку Proceed to checkout")
    public ShippingPage clickProceed(){
        processAddressBtn.click();
        return page(ShippingPage.class);
    }
}
