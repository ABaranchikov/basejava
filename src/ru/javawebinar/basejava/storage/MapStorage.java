package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected boolean isExist(Object index) {

        return index != null;
    }

    @Override
    protected void saveResume(Object index, Resume r) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object index) {
        return (Resume) index;
    }

    @Override
    protected void deleteResume(Object index) {
        map.remove(((Resume) index).getUuid());
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