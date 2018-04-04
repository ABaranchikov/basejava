package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

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

}
