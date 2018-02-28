import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    /*
    Add a resume in sorted order.
     */
    @Override
    public void save(Resume r) {
        int i = getIndex(r.getUuid());
        if (i >= 0)
            System.out.println("Resume is allready exist!");
        else if (size + 1 > STORAGE_LIMIT)
            System.out.println("Storage is full! Can't add new element.");
        else {
            int newIndex = -i - 1;
            System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
            storage[newIndex] = r;
            size++;
        }
    }

    /*
    Delete resume from base and save the sorted order
     */
    @Override
    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (i < 0) {
            System.out.println("Resume not found!");
        } else {
            System.arraycopy(storage, i + 1, storage, i, size - i - 1);
            storage[size--] = null;
        }
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
