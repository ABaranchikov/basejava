package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    private static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    /*
    @return size of the array
     */
    public int size() {
        return size;
    }

    /*
    Check the new storage size.
    Return false if new size > STORAGE_LIMIT
     */
    private boolean checkSize() {

        if (this.size + 1 > STORAGE_LIMIT) {
            System.out.println("Storage is full! Can't add new element.");
            return false;
        }
        return true;
    }

    /*
   Get resume from ArrayStorage
   @param uuid - ID resume
   @return resume
    */
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
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
        } else System.out.println("Resume " + r.getUuid() + " not exist");
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

        if (checkSize()) {

            int index = getIndex(r.getUuid());
            if (index < 0)
                addResume(r, index);
            else
                System.out.println("Resume " + r.getUuid() + " is allready exist!");
        }
    }

    /*
    Delete resume from storage by the found index
     */
    public void delete(String uuid) {

        int index = getIndex(uuid);
        if (index < 0)
            System.out.println("Resume " + uuid + " not exist");
        else {
            deleteResume(index);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
