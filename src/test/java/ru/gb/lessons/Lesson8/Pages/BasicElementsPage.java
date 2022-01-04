package ru.gb.lessons.Lesson8.Pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.gb.lessons.Lesson8.Pages.Blocks.HeaderBlock;

public class BasicElementsPage {

    @Getter
    private HeaderBlock headerBlock = new HeaderBlock();
}