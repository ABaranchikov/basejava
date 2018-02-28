package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    /*
    Add a resume in sorted order.
    @param Resume r - resume
    @param int index - new item index
     */
    @Override
    protected void addResume(Resume r, int index) {
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = r;
        size++;
    }

    /*
    Delete resume from storage and save the sorted order
     */
    @Override
    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size--] = null;
    }

    /*
    Search index of element using binarySearch
    */
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
