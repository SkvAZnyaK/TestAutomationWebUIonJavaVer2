package ru.gb.lessons.Lesson8.Pages.Blocks;

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
