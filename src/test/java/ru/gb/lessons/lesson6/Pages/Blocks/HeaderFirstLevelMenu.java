package ru.gb.lessons.lesson6.Pages.Blocks;

public enum HeaderFirstLevelMenu {

    WOMEN ("Women");

    private String title;

    HeaderFirstLevelMenu(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
