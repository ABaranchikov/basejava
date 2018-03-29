package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Integer index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void updateResume(Integer index, Resume r) {
        storage.set(index, r);
    }

    protected void deleteResume(Integer index) {
        storage.remove((index).intValue());
    }

    protected List<Resume> getStorage(){
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage.get(index);
    }
    @Override
    protected Integer getSearchKey(String uuid) {
        Integer index = 0;
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) {
                return index;
            }
            index++;
        }
        return null;
    }

    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }
}
