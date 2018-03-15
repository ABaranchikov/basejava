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
    protected Resume getResume(Object index) {
        return null;
    }

    @Override
    protected void saveResume(Object index, Resume r) {

    }

    @Override
    protected void deleteResume(Object index) {

    }

    @Override
    protected void updateResume(Object index, Resume r) {

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return 0;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }
}
