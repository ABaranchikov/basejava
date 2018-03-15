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
        int newIndex = -(int) index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = r;
    }

    /*
    Delete resume from storage and save the sorted order
     */
    @Override
    protected void removeResume(Object index) {
        int i = (int) index;
        System.arraycopy(storage, i + 1, storage, i, size - i - 1);
    }

    /*
    Search index of element using binarySearch
    */
    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return (Object) Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected boolean isExist(Object searchKey){
        if ((int)searchKey<0) return false;
        return true;
    }
}
