/**
 * Array based storage for Resumes
 *@count - счетчик объектов в массиве
 */
import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int count = 0;

    public void clear() {

        Arrays.fill(this.storage, null);
        count = 0;

    }

    public void update(Resume r){
//проверить на наличие резюме

    }

    public void save(Resume r) {
        //проверить на отсутствие резюме
        if (count< storage.length) {
            storage[count++] = r;
        }
        else System.out.println("Storage is full! Can't add new element.");
    }

    public Resume get(String uuid) {

        for (int i=0; i<count; i++){
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }

        return null;
    }

    public void delete(String uuid) {
        //проверить на наличие резюме

        for (int i=0; i<count; i++){
            if (storage[i].getUuid().equals(uuid) ) {
              //  System.arraycopy(storage,i+1, storage, i, count-i-1);
                storage[i] = storage[count-1];
                storage[--count] =null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    public int size() {

        return count;
    }
}
//убрать дублированеи кода

   // Сделать проверки: в update/delete/get - резюме есть в storage, в save- нет в storage: System.out.println("Resume ...").
     //   Сделать в save проверку на переполнениеe: System.out.println("...").