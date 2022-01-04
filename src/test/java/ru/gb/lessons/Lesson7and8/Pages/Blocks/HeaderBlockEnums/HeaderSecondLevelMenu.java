package ru.gb.lessons.Lesson7and8.Pages.Blocks.HeaderBlockEnums;

public enum HeaderSecondLevelMenu {

    SUMMERDRESSES ("Summer Dresses"),
    CASUALDRESSES ("Casual Dresses");

    private String title;

    HeaderSecondLevelMenu(String title) {
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
