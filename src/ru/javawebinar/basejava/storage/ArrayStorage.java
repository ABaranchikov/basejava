/**
 * Array based storage for Resumes
 */
package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {
    /*
    add Resume to the end of the Storage
    @param Resume r - resume
     */
    @Override
    protected void addResume(Resume r, int index) {
        storage[size] = r;
    }

    /*
    Delete Resume from the Storage.
    @param int Index - index of the item to be deleted
    */
    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[size - 1];
    }

    /*
   Search Resume in ArrayStorage.
   Return the index of the element if it is found.
   Else - return -1.
   @param String uuid - ID resume
   @return index
    */
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }
}
