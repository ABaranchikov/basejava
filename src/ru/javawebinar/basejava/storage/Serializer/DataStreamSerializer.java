package ru.javawebinar.basejava.storage.Serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataStreamSerializer implements Strategy {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            for (SectionType sectionType : SectionType.values()) {
                Section section = r.getSection(sectionType);
                if (section != null) {
                    dos.writeUTF(sectionType.name());
                    for (String st : section.getAllFields()) {
                        dos.writeUTF(st);
                    }
                }
            }
            dos.writeUTF("END"); //end of file
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            for (SectionType sectionType : SectionType.values()) {
                String st1 = dis.readUTF();
                if (st1.equals("END")) break;
                SectionType sType = SectionType.valueOf(st1);
                switch (sType) {
                    case OBJECTIVE:
                        resume.addSection(sType, new StringField(dis.readUTF()));
                        break;
                    case PERSONAL:
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int count = Integer.parseInt(dis.readUTF());
                        while (count > 0) {
                            list.add(dis.readUTF());
                            count--;
                        }
                        resume.addSection(sType, new ListField(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Experience> expList = new ArrayList<>();
                        int count2 = Integer.parseInt(dis.readUTF());
                        while (count2 > 0) {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            List<Experience.Periods> periodList = new ArrayList<>();

                            int periodsSize = Integer.parseInt(dis.readUTF());
                            while (periodsSize > 0) {
                                LocalDate d1 = LocalDate.parse(dis.readUTF());
                                LocalDate d2 = LocalDate.parse(dis.readUTF());
                                String title = dis.readUTF();
                                String desc = dis.readUTF();
                                Experience.Periods p = new Experience.Periods(d1, d2, title, desc.equals("null") ? null : desc);
                                periodList.add(p);
                                periodsSize--;
                            }
                            count2--;
                            expList.add(new Experience(link, periodList));
                        }

                        resume.addSection(sType, new ExperienceField(expList));
                        break;
                }
            }
            return resume;
        }
    }
}
