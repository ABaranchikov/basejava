package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Телефон: "),
    SKYPE("Контакт skype: "),
    LINKEDIN("Контакт LinkedIn: "),
    GITHUB("Контакт GitHub: "),
    STACKOVERFLOW("Контакт StackOverFlow: "),
    HOMEPAGE("Домашняя страница: ");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
