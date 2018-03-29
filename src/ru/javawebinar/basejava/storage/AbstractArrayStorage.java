package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    protected List<Resume> getStorage() {

        Resume[] allResume = Arrays.copyOfRange(storage, 0, size);
        return Arrays.asList(allResume);
    }

    @Override
    protected void saveResume(Integer index, Resume r) {
        if (size == STORAGE_LIMIT) throw new StorageException("Storage overflow!", r.getUuid());
        addResume(index, r);
        size++;
    }

    @Override
    protected void updateResume(Integer index, Resume r) {
        storage[index] = r;
    }

    @Override
    protected void deleteResume(Integer index) {
        removeResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void addResume(int index, Resume r);

    protected abstract void removeResume(int index);
}
