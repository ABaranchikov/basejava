package ru.javawebinar.basejava.model;

import java.util.Objects;

public class StringField extends Section {
    private static final long serialVersionUID = 1L;

    private String name;

    public StringField() {
    }

    public StringField(String name) {
        Objects.requireNonNull(name, "This field must not be null!");
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringField that = (StringField) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
