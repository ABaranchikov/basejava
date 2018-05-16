package ru.javawebinar.basejava.Sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecution<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
