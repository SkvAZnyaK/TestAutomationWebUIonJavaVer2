package ru.gb.lessons.Lesson8.Pages.Blocks;

import ru.gb.lessons.Lesson8.Pages.ProductListPage;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class HeaderBlock{

    public ProductListPage goToProductsPage (HeaderFirstLevelMenu headerFirstLevelMenu, HeaderSecondLevelMenu headerSecondLevelMenu) throws InterruptedException {
        $x("//a[text()='" + headerFirstLevelMenu.getTitle() + "']").hover();
        Thread.sleep(2000);
        $x("//a[text()='" + headerSecondLevelMenu.getTitle() + "']").click();
        Thread.sleep(2000);
        return page(ProductListPage.class);
    }
}