package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Resume getResume(int index) {
        return null;
    }

    @Override
    protected void saveResume(int index, Resume r) {

    }

    @Override
    protected void deleteResume(int index) {

    }

    @Override
    protected void updateResume(int index, Resume r) {

    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }


}
