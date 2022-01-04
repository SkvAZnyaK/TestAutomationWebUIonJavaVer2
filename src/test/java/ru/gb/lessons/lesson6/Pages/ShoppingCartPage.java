package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartPage extends BasicElementsPage{

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement pageHeading;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    public ShoppingCartPage(WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public ShoppingCartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ShoppingCartPage() {
    }

    public ShoppingCartPage checkProceedToCart (){
        assertThat(pageHeading.getText()).as("Не удалось открыть корзину").contains("SHOPPING-CART SUMMARY");
        return this;
    }

    public AddressesPage clickProceedToCheckout (){
        proceedToCheckoutBtn.click();
        return new AddressesPage(webDriver);
    }
}
