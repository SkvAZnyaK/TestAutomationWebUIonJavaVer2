package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAccountPage extends BasicElementsPage {

    @FindBy(xpath = "//a[@title='Information']")
    private WebElement personalDataBtn;
    //вынес элемент как на уроке, с помощью ленивой иницииализации

    public PersonalAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PersonalAccountPage checkLoginIsSucceed (){
        assertThat(webDriver.findElement(By.xpath("//*[@class = 'info-account']")).getText()).as("Не удалось войти в личный кабинет").isEqualTo("Welcome to your account. Here you can manage all of your personal information and orders.");
        return this;
    }

    public PersonalDataPage clickPersonalData() {
        personalDataBtn.click();
        return new PersonalDataPage(webDriver);
    }

// Эту часть пришлось дописать, т.к. метод goToProductsPage в HeaderBlock падал с ошибкой:
// org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document

    ProductListPage productListPage = new ProductListPage(webDriver, actions);
    CompareListPage compareListPage = new CompareListPage(webDriver,actions);

    public ProductListPage goToProductListPage() {
        return productListPage;
    }

    public CompareListPage goToCompareListPage() {
        return compareListPage;
    }

}
