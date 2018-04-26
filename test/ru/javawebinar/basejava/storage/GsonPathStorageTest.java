package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.JsonStreamSerializer;

public class GsonPathStorageTest extends AbstractStorageTest {
    public GsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new JsonStreamSerializer()));
    }
}