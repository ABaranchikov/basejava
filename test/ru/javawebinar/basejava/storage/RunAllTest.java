package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapStorageTest.class,
        SortedArrayStorageTest.class,
        FileStorageTest.class,
        PathStorageTest.class,
        XmlPathStorageTest.class,
        GsonPathStorageTest.class,
        DataPathStorageTest.class
})

public class RunAllTest {

}
