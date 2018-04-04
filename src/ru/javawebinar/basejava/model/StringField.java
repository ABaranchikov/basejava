package ru.javawebinar.basejava.model;

import java.util.Objects;

public class StringField extends Section {
    private String name;

   public void setName(String name) {
        Objects.requireNonNull(name,"This field must not be null!");
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
