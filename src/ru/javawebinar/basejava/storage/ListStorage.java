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
    protected void saveResume(int index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void updateResume(int index, Resume r) {
        storage. (index, r);
    }

    protected void deleteResume(int index) {
        storage.remove(index);
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
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    protected int getIndex(String uuid) {
        int index = 0;
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) {
                return index;
            }
            index++;
        }
        return -1;
    }

}
