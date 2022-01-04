package ru.gb.lessons.Lesson8.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductListPage extends BasicElementsPage {

    private SelenideElement myShoppingCartBtn = $x("//a[@title='View my shopping cart']");
    private SelenideElement compareListValue = $x("//div[@class='bottom-pagination-content clearfix']//strong[@class='total-compare-val']");
    private SelenideElement compareBtn = $x("//div[@class='bottom-pagination-content clearfix']//button[@type='submit']");

    @Step("Добавить два первых товара к сравнению")
    public ProductListPage addToCompareList(int index1, int index2) {
        ElementsCollection summerDresses = $$x("//div[@class='product-container']");
        summerDresses.get(index1).hover().$x(".//a[@class='add_to_compare']").click();
//        assertThat(compareListValue.getText()).as("Товар1 не попал в список для сравнения").isEqualTo("1");
        compareListValue.shouldHave(exactText("1"));
        summerDresses.get(index2).hover().$x(".//a[@class='add_to_compare']").click();
//        assertThat(compareListValue.getText()).as("Товар2 не попал в список для сравнения").isEqualTo("2");
        compareListValue.shouldHave(exactText("2"));
        return this;
    }

    @Step("Нажать кнопку Compare")
    public CompareListPage clickCompareButton(){
        compareBtn.click();
        return page(CompareListPage.class);
    }

    @Step("Добавить первый товар в корзину")
    public ProductListPage addToCart (int index){
        $x("//a[text()='Women']").hover();
        assertThat($x("//ul/li/ul/li/a[@title='Dresses']").getText()).as("Не раскрылось меню женской одежды").isEqualTo("DRESSES");
        $x("//a[text()='Casual Dresses']").click();
        assertThat($x("//span[@class='cat-name']").getText()).as("Не удалось войти в категорию CASUAL DRESSES").isEqualTo("CASUAL DRESSES ");
        ElementsCollection casualDresses = $$x("//div[@class='product-container']");
        casualDresses.get(index)
                .hover()
                .$x(".//span[text()='Add to cart']")
                .click();
        return this;
    }

    @Step("Закрыть всплывшее окно с выбранным товаром")
    public ProductListPage closeProductWindow (){
        $x("//span[@class='cross']").click();
        return this;
    }

    @Step("Нажать на кнопку корзины в хедере")
    public ShoppingCartPage goToShoppingCart (){
        myShoppingCartBtn.click();
        return page(ShoppingCartPage.class);
    }

    @Step("Убедиться что всплывшее окно с выбранным товаром закрылось")
    public ProductListPage checkIfProductWindowIsClosed(){
        assertThat($x("//span[@class='cat-name']").getText()).as("Не удалось закрыть окно товара").isEqualTo("CASUAL DRESSES ");
        return this;
    }

    @Step("Убедиться что товар попал в корзину")
    public ProductListPage checkIfProductIsInCart(){
//        assertThat($x("//a/span[contains(@class, 'ajax_cart_quantity')]").getText()).as("Не удалось положить товар в корзину").isEqualTo("1");
        $x("//a/span[contains(@class, 'ajax_cart_quantity')]").shouldHave(exactText("1"));
        return this;
    }
}
