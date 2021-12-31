package ru.gb.lessons.lesson6.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CompareListPage extends BasicElementsPage{

    public CompareListPage(WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public CompareListPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CompareListPage() {
    }
}
