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
    protected void addResume(Object index, Resume r) {
        Integer newIndex = -(int) index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = r;
    }

    /*
    Delete resume from storage and save the sorted order
     */
    @Override
    protected void removeResume(Object index) {
        Integer i = (Integer) index;
        System.arraycopy(storage, i + 1, storage, i, size - i - 1);
    }

    /*
    Search index of element using binarySearch
    */
    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected boolean isExist(Object searchKey){
        return (Integer) searchKey >= 0;
    }
}
