package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    /*
    @return size of the array
     */
    public int size() {
        return size;
    }

    /*
    clear of the array
     */
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    /*
    @return all resume from base
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void saveResume(Object index, Resume r) {
        if (size == STORAGE_LIMIT) throw new StorageException("Storage overflow!", r.getUuid());
        addResume((Integer)index, r);
        size++;
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage[(Integer) index] = r;
    }

    @Override
    protected void deleteResume(Object index) {
        removeResume((Integer)index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }
    protected abstract boolean isExist(Object searchKey);

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void addResume(Object index, Resume r);

    protected abstract void removeResume(Object index);
}
