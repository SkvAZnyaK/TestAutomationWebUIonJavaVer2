package ru.gb.lessons.lesson6.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAccountPage extends BasicElementsPage {

    @FindBy(xpath = "//a[@title='Information']")
    private WebElement personalDataBtn;

    @FindBy(xpath = "//a[text()='Contact us']")
    private WebElement contactUsRef;

    //вынес элементы как на уроке, с помощью ленивой иницииализации

    public PersonalAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Убедиться что авторизация прошла успешно, и открылся личный кабинет пользователя")
    public PersonalAccountPage checkLoginIsSucceed (){
        assertThat(webDriver.findElement(By.xpath("//*[@class = 'info-account']")).getText()).as("Не удалось войти в личный кабинет").isEqualTo("Welcome to your account. Here you can manage all of your personal information and orders.");
        return this;
    }

    @Step("Нажать на кнопку Personal Data")
    public PersonalDataPage clickPersonalData() {
        personalDataBtn.click();
        return new PersonalDataPage(webDriver);
    }

    @Step("Нажать на кнопку Contact Us")
    public FeedbackPage clickContactUs(){
        contactUsRef.click();
        return new FeedbackPage(webDriver);
    }

// Эту часть пришлось дописать, т.к. метод goToProductsPage в HeaderBlock падал с ошибкой:
// org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document

    ProductListPage productListPage = new ProductListPage(webDriver, actions);
    CompareListPage compareListPage = new CompareListPage(webDriver,actions);

    //@Step("")
    public ProductListPage goToProductListPage() {
        return productListPage;
    }

    //@Step("")
    public CompareListPage goToCompareListPage() {
        return compareListPage;
    }

}
