package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

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

    public void setContacts(ContactType contactType, String contactValue) {
        contacts.put(contactType, contactValue);
    }

    public String getContacts(ContactType type){
        return contacts.get(type);
    }

    public Section getSection(SectionType type){
        return sections.get(type);
    }


    public void getContacts() {
        for (Map.Entry<ContactType, String> contact : contacts.entrySet()) {
            System.out.println(contact.getKey().getTitle() + contact.getValue());
        }
    }

    public void setSections(SectionType key, Section section) {
        sections.put(key, section);
    }

    public void getAllSection() {
        for (SectionType sectionType : SectionType.values()) {
            System.out.println(sectionType.getTitle());
            Section section = sections.get(sectionType);
            System.out.println(section);
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
