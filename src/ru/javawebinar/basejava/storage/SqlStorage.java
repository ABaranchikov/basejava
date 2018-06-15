package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.sql.SqlHelper;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name =? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContacts(conn, r);
            insertContacts(conn, r);

            deleteSections(conn, r);
            insertSections(conn, r);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContacts(conn, r);
                    insertSections(conn, r);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("SELECT full_name FROM resume WHERE uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT type, value FROM contact WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    addContact(rs, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT type, value FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    addSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT uuid, full_name FROM resume ORDER BY full_name")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    String uuid = rs.getString("uuid");
                    Resume r = resumes.get(uuid);
                    if (r == null) {
                        r = new Resume(uuid, rs.getString("full_name"));
                        resumes.put(uuid, r);
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT resume_uuid, type, value FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    String uuid = rs.getString("resume_uuid");
                    Resume r = resumes.get(uuid);
                    addContact(rs, r);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT resume_uuid, type, value FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    String uuid = rs.getString("resume_uuid");
                    Resume r = resumes.get(uuid);
                    addSection(rs, r);
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String type = rs.getString("type");
        if (type != null) {
            String value = rs.getString("value");
            ContactType contactType = ContactType.valueOf(type);
            r.addContact(contactType, value);
        }
    }

    private void deleteContacts(Connection conn, Resume r) throws SQLException {
        deleteAttribute(conn, r, "DELETE FROM contact WHERE resume_uuid = ?");
    }

    private void insertContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            if (r.getContacts().size() > 0) {
                ps.executeBatch();
            }
        }
    }

    private void insertSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                Section section = e.getValue();
                ps.setString(3, JsonParser.write(section, Section.class));
                ps.addBatch();
            }
            if (r.getSections().size() > 0) {
                ps.executeBatch();
            }
        }
    }

    private void deleteSections(Connection conn, Resume r) throws SQLException {
        deleteAttribute(conn, r, "DELETE FROM section WHERE resume_uuid = ?");
    }

    private void deleteAttribute(Connection conn, Resume r, String query) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, r.getUuid());
            ps.executeUpdate();
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        String content = rs.getString("value");
        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            r.addSection(type, JsonParser.read(content, Section.class));
        }
    }
}