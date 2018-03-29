package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(String index, Resume r) {
        map.put(index, r);
    }

    @Override
    protected boolean isExist(String index) {

        return map.get(index) != null;
    }

    @Override
    protected void saveResume(String index, Resume r) {
        map.put(index, r);
    }

    @Override
    protected Resume getResume(String index) {
        return map.get(index);
    }

    @Override
    protected void deleteResume(String index) {
        map.remove(index);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> getStorage() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}