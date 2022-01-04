package ru.gb.lessons.Lesson7and8.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class CompareListPage{

    private SelenideElement menuHeader = $x("//span[@class='cat-name']");
    private SelenideElement pageHeading = $x("//h1[@class='page-heading']");
    private SelenideElement deleteIcon = $x(".//a[@title='Remove']");
    private SelenideElement closeBtn = $x("//ul[@class='footer_link']//a[@class='button lnk_view btn btn-default']");

//    public CompareListPage goToProductListPage2(){
//        $x("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']").click();
//        assertThat(menuHeader.getText()).as("Не удалось войти в категорию DRESSES").isEqualTo("DRESSES ");
//        $x("//div[@class='subcategory-image']/a[@title='Summer Dresses']").click();
//        assertThat(menuHeader.getText()).as("Не удалось войти в категорию SUMMER DRESSES").isEqualTo("SUMMER DRESSES ");
//        return this;
//    }

    @Step("Убедиться что загрузился список сравнения")
    public CompareListPage checkProceedToCompareListSuccess(){
        assertThat(pageHeading.getText()).as("Не удалось войти в список сравнения").isEqualTo("PRODUCT COMPARISON");
        return this;
    }

    @Step("Удалить все товары из списка сравнения и нажать кнопку Continue Shopping")
    public CompareListPage cleanAndCloseCompareList(){
        ElementsCollection dressesList = $$x("//td[contains(@class,'ajax_block_product comparison_infos product-block product')]");
        dressesList.get(1).$x(".//a[@title='Remove']").click();
        dressesList.get(1).shouldNotBe(visible);
        dressesList.get(0).$x(".//a[@title='Remove']").click();
        dressesList.get(0).shouldNotBe(visible);
        closeBtn.click();
        return this;
    }

    @Step("Убедиться что список сравнения закрылся")
    public CompareListPage checkIfCompareListClosed() {
        assertThat(WebDriverRunner.url()).as("Не получается выйти из списка сравнения").isNotEqualTo("http://automationpractice.com/index.php?controller=products-comparison&compare_product_list=1");
        return this;
    }
}