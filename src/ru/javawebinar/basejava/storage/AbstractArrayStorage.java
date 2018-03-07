package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

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
    Get resume from ArrayStorage
    @param uuid - ID resume
    @return resume
     */
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    /*
    clear of the array
     */
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    /*
    find and update resume in base
     */
    public void update(Resume r) {

        int i = getIndex(r.getUuid());

        if (i >= 0) {
            storage[i] = r;
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    /*
    @return all resume from base
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    /*
    add new resume in storage.
    */
    public void save(Resume r) {

        int index = getIndex(r.getUuid());
        if (index > 0)
            throw new ExistStorageException(r.getUuid());
        else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow!", r.getUuid());
        } else {
            addResume(r, index);
            size++;
        }

    }

    /*
    Delete resume from storage by the found index
     */
    public void delete(String uuid) {

        int index = getIndex(uuid);
        if (index < 0)
            throw new NotExistStorageException(uuid);
        else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
