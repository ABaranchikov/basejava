/**
 * Array based storage for Resumes
 */
package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addResume(Object index, Resume r) {
        storage[size] = r;
    }

    @Override
    protected void removeResume(Object index) {
        storage[(Integer) index] = storage[size - 1];
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }
}
