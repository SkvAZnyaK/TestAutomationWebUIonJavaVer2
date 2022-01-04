package ru.gb.lessons.lesson6.Pages.Blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.gb.lessons.lesson6.Pages.BaseView;
import org.openqa.selenium.interactions.Actions;
import ru.gb.lessons.lesson6.Pages.ProductListPage;

public class HeaderBlock extends BaseView {

    public HeaderBlock (WebDriver webDriver, Actions actions){
        super(webDriver, actions);
    }

//    public ProductListPage goToProductsPage (String headerFirstLevelMenu, String headerSecondLevelMenu) throws InterruptedException {
//        actions.moveToElement(webDriver.findElement(By.xpath("//a[text()='" + headerFirstLevelMenu + "']")))
//                .build()
//                .perform();
//        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("//a[text()='" + headerSecondLevelMenu + "']")).click();
//        return new ProductListPage(webDriver,actions);
//    }

//  Этот метод падает с ошибкой: org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document
//  Я погуглил что это, понял, но решить просто ожиданием не получилось, поэтому в условиях ограничения по времени, реализую метод без рефакторинга в классе ProductListPage.

}