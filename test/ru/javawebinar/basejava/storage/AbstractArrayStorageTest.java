package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    // TODO remain only for Arrays implementations
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Ivanov Ivan"));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("Petrov Ivan"));
    }
}
