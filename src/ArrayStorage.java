/**
 * Array based storage for Resumes
 *@count - счетчик объектов в массиве
 */

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int count = 0;

    /*
    возвращает индекс элемента массива, если он найден.
    В противном случае возвращает -1.
    @param String uuid - ID элемента массива
     */
    public int getIndex(String uuid) {

        for (int i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    public int getIndex(Resume r) {

        for (int i = 0; i < count; i++) {
            if (storage[i].equals(r)) return i;
        }
        return -1;
    }



    /*
    Очищает массив
     */
    public void clear() {

        Arrays.fill(this.storage, null);
        count = 0;

    }

    /*
    проверяет массив на наличие резюме.
    При наличии - его обновляет.
    Пока реализован поиск. Что здесь можно обновить - не понятно.
     */

    public void update(Resume r){

        String id = r.getUuid();

        if (getIndex(r)>=0) {
            System.out.println("Нашли резюме");
        }
        else System.out.println("Данного резюме нет в базе!");
    }

    /*
    Сохраняем резюме в базе
    @param Resume r - резюме
     */
    public void save(Resume r) {
        //проверить на отсутствие резюме
        int i = getIndex(r);
        if (i>=0) {
            System.out.println("Данное резюме уже есть в базе!");
            return;
        }

        if (count< storage.length) {
            storage[count++] = r;
        }
        else System.out.println("Storage is full! Can't add new element.");
    }
    /*
    получить резюме по ID
     */
    public Resume get(String uuid) {

        int i = getIndex(uuid);

        if (i>=0) {return storage[i];}
        else System.out.println("Данного резюме нет в базе!");

        return null;
    }
    /*
    удалить резюме по ID
     */
    public void delete(String uuid) {
        //проверить на наличие резюме

        int i =getIndex(uuid);
        if (i>=0) {
            storage[i] = storage[count-1];
            storage[--count] =null;
        }
        else System.out.println("Данного резюме нет в базе!");

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
