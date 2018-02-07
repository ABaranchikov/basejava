/**
 * Array based storage for Resumes
 *@count - счетчик объектов в массиве
 */
import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int count = 0;

    void clear() {

        Arrays.fill(this.storage, null);
        count = 0;

    }

    void save(Resume r) {
        if (count< storage.length) {
            storage[count++] = r;
        }
        else System.out.println("Storage is full! Can't add new element.");
    }

    Resume get(String uuid) {

        for (int i=0; i<count; i++){
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }

        return null;
    }

    void delete(String uuid) {

        for (int i=0; i<count; i++){
            if (storage[i].uuid == uuid) {
                System.arraycopy(storage,i+1, storage, i, count-i-1);
                storage[--count] =null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    int size() {
        return count;
    }
}
