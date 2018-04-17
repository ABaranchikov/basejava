package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListField<T> extends Section {
    private static final long serialVersionUID = 1L;

    private final List<T> items;

    public ListField(T...items){
        this(Arrays.asList(items));
    }

    public ListField (List<T> items){

        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public String toString() {
            return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListField that = (ListField) o;

        return items.equals(that.items);

    }

    @Override
    public int hashCode() {

        return items.hashCode();
    }
}
