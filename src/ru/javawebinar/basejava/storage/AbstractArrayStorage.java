package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
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
    protected void saveResume(int index, Resume r) {
        if (size == STORAGE_LIMIT) throw new StorageException("Storage overflow!", r.getUuid());
        addResume(index, r);
        size++;
    }

    @Override
    protected void updateResume(int index, Resume r) {
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        removeResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(int index, Resume r);

    protected abstract void removeResume(int index);
}
