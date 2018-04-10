package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListField<T> extends Section {

    private List<T> list = new ArrayList<>();

    public void add(T value) {
        list.add(value);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object aList : list) {
            sb.append(aList);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListField<?> listField = (ListField<?>) o;
        return Objects.equals(list, listField.list);
    }

    @Override
    public int hashCode() {

        return Objects.hash(list);
    }
}
