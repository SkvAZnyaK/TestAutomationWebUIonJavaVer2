package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.gb.lessons.lesson6.Pages.Blocks.HeaderBlock;

public class BasicElementsPage extends BaseView{

    private HeaderBlock headerBlock = new HeaderBlock(webDriver, actions);

    public BasicElementsPage (WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public BasicElementsPage (WebDriver webDriver) {
        super(webDriver);
    }

    public BasicElementsPage () {
    }

    public HeaderBlock getHeaderBlock() {
        return headerBlock;
    }
    // Помню про Ломбок, но мне быстрее было просто через Альт+Инсерт сделать геттер, чем подключать его ))) Хотя пожалуй в следующей домашке надо подключить, а то конструкторов много в классах.
}
