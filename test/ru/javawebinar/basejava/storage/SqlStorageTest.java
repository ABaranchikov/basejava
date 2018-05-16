package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

import java.sql.SQLException;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() throws SQLException {
        super(new SqlStorage(Config.getInstance().getDbUrl(),Config.getInstance().getDbUser(), Config.getInstance().getDbPassword()));
    }
}
