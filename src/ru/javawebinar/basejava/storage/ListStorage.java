package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Object index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage.set((Integer) index, r);
    }

    protected void deleteResume(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    protected List<Resume> getStorage(){
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((Integer) index);
    }

    protected Object getSearchKey(String uuid) {
        Integer index = 0;
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) {
                return index;
            }
            index++;
        }
        return null;
    }

    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
