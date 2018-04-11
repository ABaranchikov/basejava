package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage extends AbstractFileStorage {
    public FileStorage(File directory) {
        super(directory);
    }

    protected void doWrite(Resume r, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.append(r.getFullName()).append("\n");
        for (ContactType contactType : ContactType.values()) {
            fw.append(contactType.getTitle()).append(r.getContacts(contactType)).append("\n");
        }

        for (SectionType sectionType : SectionType.values()) {
            fw.append(sectionType.getTitle()).append(String.valueOf(r.getSection(sectionType))).append("\n");
        }
        fw.close();
    }

    @Override
    protected Resume doRead(File file) throws IOException {
        return null;
    }

}
