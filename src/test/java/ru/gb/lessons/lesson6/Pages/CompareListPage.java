package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompareListPage extends BasicElementsPage {

    @FindBy(xpath = "//span[@class='cat-name']")
    private WebElement menuHeader;

    @FindBy(xpath = "//div[@class='bottom-pagination-content clearfix']//strong[@class='total-compare-val']")
    private WebElement compareListValue;

    @FindBy(xpath = "//div[@class='bottom-pagination-content clearfix']//button[@type='submit']")
    private WebElement compareBtn;

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement pageHeading;

    @FindBy(xpath = "//i[@class='icon-trash']")
    private WebElement deleteIcon;

    @FindBy(xpath = "//ul[@class='footer_link']//a[@class='button lnk_view btn btn-default']")
    private WebElement closeBtn;

    public CompareListPage(WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public CompareListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CompareListPage() {
    }

    public CompareListPage goToProductListPage2() {
        webDriver.findElement(By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']")).click();
        assertThat(menuHeader.getText()).as("Не удалось войти в категорию DRESSES").isEqualTo("DRESSES ");
        webDriver.findElement(By.xpath("//div[@class='subcategory-image']/a[@title='Summer Dresses']")).click();
        assertThat(menuHeader.getText()).as("Не удалось войти в категорию SUMMER DRESSES").isEqualTo("SUMMER DRESSES ");
        return this;
    }

    public CompareListPage addToCompareList(int index1, int index2) throws InterruptedException {
        List<WebElement> summerDresses = webDriver.findElements(By.xpath("//div[@class='product-container']"));
        actions.moveToElement(summerDresses.get(index1))
                .build()
                .perform();
        summerDresses.get(index1).findElement(By.xpath(".//a[@class='add_to_compare']")).click();
        Thread.sleep(2000);
        assertThat(compareListValue.getText()).as("Товар не попал в список для сравнения").isEqualTo("1");
        actions.moveToElement(summerDresses.get(index2))
                .build()
                .perform();
        summerDresses.get(index2).findElement(By.xpath(".//a[@class='add_to_compare']")).click();
        Thread.sleep(2000);
        assertThat(compareListValue.getText()).as("Товар не попал в список для сравнения").isEqualTo("2");
        return this;
    }

    public CompareListPage clickCompareButton() {
        compareBtn.click();
        return this;
    }

    public CompareListPage checkProceedToCompareListSuccess() {
        assertThat(pageHeading.getText()).as("Не удалось войти в список сравнения").isEqualTo("PRODUCT COMPARISON");
        return this;
    }

    public CompareListPage cleanAndCloseCompareList() throws InterruptedException {
        deleteIcon.click();
        Thread.sleep(2000);
        deleteIcon.click();
        closeBtn.click();
        return this;
    }

    public CompareListPage checkIfCompareListClosed() {
        assertThat(webDriver.getCurrentUrl()).as("Не получается выйти из списка сравнения").isNotEqualTo("http://automationpractice.com/index.php?controller=products-comparison&compare_product_list=1");
        return this;
    }
}
