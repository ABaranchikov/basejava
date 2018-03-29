package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private Map<String, String> contacts = new LinkedHashMap<>();
    private Map<String, List<?>> textFields = new HashMap<>();

    public static class Experience {
        private String company;
        private String dateFrom;
        private String dateTo;
        private String position;
        private String duties;

        public Experience(String company, String dateFrom, String dateTo, String position, String duties) {
            this.company = company;
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.position = position;
            this.duties = duties;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("Company: ");
            sb.append(company);
            sb.append("\n");

            sb.append("From: ");
            sb.append(dateFrom);
            sb.append("\n");

            sb.append("To: ");
            sb.append(dateTo);
            sb.append("\n");

            sb.append("Position: ");
            sb.append(position);
            sb.append("\n");

            sb.append("Duties: ");
            sb.append(duties);

            return sb.toString();
        }
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "UUID must not be null!");
        Objects.requireNonNull(fullName, "fullName must not be null!");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setContacts(String contactType, String contactValue) {
        contacts.put(contactType, contactValue);
    }

    public void getContacts() {
        for (Map.Entry<String, String> contact : contacts.entrySet()) {
            System.out.println(contact.getKey() + " : " + contact.getValue());
        }
    }

    public void setTextFields(String key, List<?> list) {
        textFields.put(key, list);
    }

    public void getTextFields(String key) {
        List<?> list = textFields.get(key);
        if (list != null) {
            for (Object ob : list) System.out.println(ob);
        }

    }

    @Override
    public String toString() {
        return uuid + " " + '(' + fullName + ')';
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, fullName);
    }
}
