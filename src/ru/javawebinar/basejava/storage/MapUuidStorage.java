package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO implement
// TODO create new MapStorage with search key not uuid
public class MapUuidStorage extends AbstractStorage {

    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        map.put((String)index, r);
    }

    @Override
    protected boolean isExist(Object index) {

        return map.get((String)index) != null;
    }

    @Override
    protected void saveResume(Object index, Resume r) {
        map.put((String)index, r);
    }

    @Override
    protected Resume getResume(Object index) {
        return map.get((String)index);
    }

    @Override
    protected void deleteResume(Object index) {
        map.remove((String)index);
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