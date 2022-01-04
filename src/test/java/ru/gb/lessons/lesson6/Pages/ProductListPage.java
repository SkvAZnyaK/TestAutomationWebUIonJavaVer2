package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.gb.lessons.lesson6.Pages.Blocks.HeaderBlock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductListPage extends BasicElementsPage{

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement myShoppingCartBtn;

    public ProductListPage(WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public ProductListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProductListPage() {
    }

    public ProductListPage addToCart (int index){
        actions.moveToElement(webDriver.findElement(By.xpath("//a[text()='Women']")))
                .build()
                .perform();
        assertThat(webDriver.findElement(By.xpath("//ul/li/ul/li/a[@title='Dresses']")).getText()).as("Не раскрылось меню женской одежды").isEqualTo("DRESSES");
        webDriver.findElement(By.xpath("//a[text()='Casual Dresses']")).click();
        assertThat(webDriver.findElement(By.xpath("//span[@class='cat-name']")).getText()).as("Не удалось войти в категорию CASUAL DRESSES").isEqualTo("CASUAL DRESSES ");
        List<WebElement> casualDresses = webDriver.findElements(By.xpath("//div[@class='product-container']"));
        actions.moveToElement(casualDresses.get(index))
                .build()
                .perform();
        casualDresses.get(index).findElement(By.xpath(".//span[text()='Add to cart']")).click();
        return this;
    }

    public ProductListPage closeProductWindow (){
        webDriver.findElement(By.xpath("//span[@class='cross']")).click();
        return this;
    }

    public ShoppingCartPage goToShoppingCart (){
        myShoppingCartBtn.click();
        return new ShoppingCartPage(webDriver);
    }

//    public ProductListPage addToCompareList (int index){
//
//        return this;
//    }

    public ProductListPage checkIfProductWindowIsClosed () {
        assertThat(webDriver.findElement(By.xpath("//span[@class='cat-name']")).getText()).as("Не удалось закрыть окно товара").isEqualTo("CASUAL DRESSES ");
        return this;
    }

    public ProductListPage checkIfProductIsInCart () throws InterruptedException {
        Thread.sleep(1000);
        assertThat(webDriver.findElement(By.xpath("//a/span[contains(@class, 'ajax_cart_quantity')]")).getText()).as("Не удалось положить товар в корзину").isEqualTo("1");
        return this;
    }
}
