package ru.gb.lessons.lesson5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Hw5Test extends BasicTest{


    @Test
    @DisplayName("Тестирование авторизации через логин и пароль" )
    void UIAuthorizationTest() throws InterruptedException {
        WebElement text = webDriver.findElement(By.xpath("//*[@class = 'info-account']"));
        assertThat(text.getText()).as("Не удалось войти в личный кабинет").isEqualTo("Welcome to your account. Here you can manage all of your personal information and orders.");
    }

    @Test
    @DisplayName("Тестирование изменения личных данных")
    void PersonalDataChangeTest() {
        webDriver.findElement(By.xpath("//a[@title='Information']")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-subheading']")).getText()).as("Не у далось войти в раздел персональных данных").isEqualTo("YOUR PERSONAL INFORMATION");
        webDriver.findElement(By.id("id_gender1")).click();
        webDriver.findElement(By.id("firstname")).clear();
        webDriver.findElement(By.id("firstname")).sendKeys("Sergey");
        assertThat(webDriver.findElement(By.id("firstname")).getAttribute("value")).isEqualTo("Sergey");
        webDriver.findElement(By.id("lastname")).clear();
        webDriver.findElement(By.id("lastname")).sendKeys("Semizarov");
        assertThat(webDriver.findElement(By.id("lastname")).getAttribute("value")).isEqualTo("Semizarov");
        webDriver.findElement(By.id("days")).click();
        webDriver.findElement(By.xpath("//select[@id='days']/option[@value='10']")).click();
        assertThat(webDriver.findElement(By.id("days")).getAttribute("value")).isEqualTo("10");
        webDriver.findElement(By.id("months")).click();
        webDriver.findElement(By.xpath("//select[@id='months']/option[@value='4']")).click();
        assertThat(webDriver.findElement(By.id("months")).getAttribute("value")).isEqualTo("4");
        webDriver.findElement(By.id("years")).click();
        webDriver.findElement(By.xpath("//select[@id='years']/option[@value='1983']")).click();
        assertThat(webDriver.findElement(By.id("years")).getAttribute("value")).isEqualTo("1983");
        webDriver.findElement(By.id("old_passwd")).sendKeys("Cthutq62!");
        webDriver.findElement(By.name("submitIdentity")).click();
        assertThat(webDriver.findElement(By.xpath("//p[@class='alert alert-success']")).getText()).as("Персональные данные не корректны").isEqualTo("Your personal information has been successfully updated.");
    }

    @Test
    @DisplayName("Тестирование сравнения товаров")
    void CompareListTest() throws InterruptedException {
        webDriver.findElement(By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']")).click();
        assertThat(webDriver.findElement(By.xpath("//span[@class='cat-name']")).getText()).as("Не удалось войти в категорию DRESSES").isEqualTo("DRESSES ");
        webDriver.findElement(By.xpath("//div[@class='subcategory-image']/a[@title='Summer Dresses']")).click();
        assertThat(webDriver.findElement(By.xpath("//span[@class='cat-name']")).getText()).as("Не удалось войти в категорию SUMMER DRESSES").isEqualTo("SUMMER DRESSES ");
        List<WebElement> summerDresses = webDriver.findElements(By.xpath("//div[@class='product-container']"));
        actions.moveToElement(summerDresses.get(0))
                .build()
                .perform();
        summerDresses.get(0).findElement(By.xpath(".//a[@class='add_to_compare']")).click();
        Thread.sleep(2000);
        //webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='bottom-pagination-content clearfix']//strong[@class='total-compare-val']")));
        assertThat(webDriver.findElement(By.xpath("//div[@class='bottom-pagination-content clearfix']//strong[@class='total-compare-val']")).getText()).as("Товар не попал в список для сравнения").isEqualTo("1");
        actions.moveToElement(summerDresses.get(1))
                .build()
                .perform();
        summerDresses.get(1).findElement(By.xpath(".//a[@class='add_to_compare']")).click();
        Thread.sleep(2000);
        assertThat(webDriver.findElement(By.xpath("//div[@class='bottom-pagination-content clearfix']//strong[@class='total-compare-val']")).getText()).as("Товар не попал в список для сравнения").isEqualTo("2");
        webDriver.findElement(By.xpath("//div[@class='bottom-pagination-content clearfix']//button[@type='submit']")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-heading']")).getText()).as("Не удалось войти в список сравнения").isEqualTo("PRODUCT COMPARISON");
        webDriver.findElement(By.xpath("//i[@class='icon-trash']")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//i[@class='icon-trash']")).click();
        webDriver.findElement(By.xpath("//ul[@class='footer_link']//a[@class='button lnk_view btn btn-default']")).click();
        assertThat(webDriver.getCurrentUrl()).as("Не получается выйти из списка сравнения").isNotEqualTo("http://automationpractice.com/index.php?controller=products-comparison&compare_product_list=1");
    }

    @Test
    @DisplayName("Тестирование добавления товара в корзину, оформления и оплаты заказа")
    void AddToCartTest() throws InterruptedException {
        actions.moveToElement(webDriver.findElement(By.xpath("//a[text()='Women']")))
                .build()
                .perform();
        assertThat(webDriver.findElement(By.xpath("//ul/li/ul/li/a[@title='Dresses']")).getText()).as("Не раскрылось меню женской одежды").isEqualTo("DRESSES");
        webDriver.findElement(By.xpath("//a[text()='Casual Dresses']")).click();
        assertThat(webDriver.findElement(By.xpath("//span[@class='cat-name']")).getText()).as("Не удалось войти в категорию CASUAL DRESSES").isEqualTo("CASUAL DRESSES ");
        List<WebElement> casualDresses = webDriver.findElements(By.xpath("//div[@class='product-container']"));
        actions.moveToElement(casualDresses.get(0))
                .build()
                .perform();
        casualDresses.get(0).findElement(By.xpath(".//span[text()='Add to cart']")).click();
        Thread.sleep(1000);
        assertThat(webDriver.findElement(By.xpath("//a/span[contains(@class, 'ajax_cart_quantity')]")).getText()).as("Не удалось положить товар в корзину").isEqualTo("1");
        webDriver.findElement(By.xpath("//span[@class='cross']")).click();
        assertThat(webDriver.findElement(By.xpath("//span[@class='cat-name']")).getText()).as("Не удалось закрыть окно товара").isEqualTo("CASUAL DRESSES ");
        webDriver.findElement(By.xpath("//a[@title='View my shopping cart']")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-heading']")).getText()).as("Не удалось открыть корзину").contains("SHOPPING-CART SUMMARY");
        webDriver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-heading']")).getText()).as("Не удалось перейти к заполнению адреса").isEqualTo("ADDRESSES");
        webDriver.findElement(By.xpath("//option[contains(text(),'My address')]")).click();
        webDriver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Very important blah-blah...");
        webDriver.findElement(By.xpath("//button[@name='processAddress']")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-heading']")).getText()).as("Не удалось перейти к выбору метода доставки").isEqualTo("SHIPPING");
        webDriver.findElement(By.id("cgv")).click();
        webDriver.findElement(By.xpath("//button[@name='processCarrier']")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-heading']")).getText()).as("Не удалось перейти к выбору метода оплаты").isEqualTo("PLEASE CHOOSE YOUR PAYMENT METHOD");
        webDriver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-heading']")).getText()).as("Не удалось перейти к проверке параметров заказа").isEqualTo("ORDER SUMMARY");
        webDriver.findElement(By.xpath("//span[text()='I confirm my order']")).click();
        assertThat(webDriver.findElement(By.xpath("//div/p/strong[@class='dark']")).getText()).as("Не удалось подтвердить заказ").isEqualTo("Your order on My Store is complete.");
    }

    @Test
    void SendFeedbackTest() {
        webDriver.findElement(By.xpath("//a[text()='Contact us']")).click();
        assertThat(webDriver.findElement(By.xpath("//h1[@class='page-heading bottom-indent']")).getText()).as("Не удалось перейти к форме обратной связи").contains("CUSTOMER SERVICE - CONTACT US");
        webDriver.findElement(By.id("id_contact")).click();
        webDriver.findElement(By.xpath("//select[@id='id_contact']/option[@value='1']")).click();
        assertThat(webDriver.findElement(By.id("uniform-id_contact")).getText()).as("Не удалось выбрать адрессата").contains("Webmaster");
        webDriver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Thank you very much! for such a beautiful source to practice");
        assertThat(webDriver.findElement(By.xpath("//textarea[@id='message']")).getAttribute("value")).as("Текст введен не верно").isEqualTo("Thank you very much! for such a beautiful source to practice");
        webDriver.findElement(By.id("submitMessage")).click();
        assertThat(webDriver.findElement(By.xpath("//p[@class='alert alert-success']")).getText()).as("Не удалось отправить сообщение").isEqualTo("Your message has been successfully sent to our team.");
    }
}