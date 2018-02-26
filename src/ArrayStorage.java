/**
 * Array based storage for Resumes
 */

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {
    /*
    Save Resume in ArrayStorage
    @param Resume r - resume
     */
    @Override
    public void save(Resume r) {
        int i = getIndex(r.getUuid());
        if (i >= 0) {
            System.out.println("This resume is allready exist!");
            return;
        }

        if (size < storage.length) {
            storage[size++] = r;
        } else System.out.println("Storage is full! Can't add new element.");
    }

    /*
    Delete resume from ArrayStorage
    @param uuid - ID resume
    @return resume
    */
    @Override
    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i >= 0) {
            storage[i] = storage[size - 1];
            storage[--size] = null;
        } else System.out.println("Resume not found!");
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
