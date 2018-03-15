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
        storage.set((int) index, r);
    }

    protected void deleteResume(Object index) {
        storage.remove((int) index);
    }

    @Override
    public Resume[] getAll() {
        Resume[] list = new Resume[storage.size()];
        return storage.toArray(list);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((int)index);
    }

    protected Object getSearchKey(String uuid) {
        int index = 0;
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) {
                return index;
            }
            index++;
        }
        return -1;
    }
    protected boolean isExist(Object searchKey){
        if ((int)searchKey>0) return true;
        return false;
    }
}
