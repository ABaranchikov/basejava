package ru.javawebinar.basejava.storage.Serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

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
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> section : sections.entrySet()) {
                dos.writeUTF(section.getKey().name());
                for (String st : getAllFields(section.getValue())) {
                    dos.writeUTF(st);
                }
            }
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

            size = dis.readInt();
            while (size > 0) {
                String st1 = dis.readUTF();
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
                            String linkName = dis.readUTF();
                            String linkURL = dis.readUTF();
                            Link link = new Link(linkName, linkURL.equals("null") ? null : linkURL);
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
                size--;
            }
            return resume;
        }
    }

    private List<String> getAllFields(Section section) {

        List<String> list = new ArrayList<>();

        if (section instanceof StringField) {
            list.add(section.toString());
        } else if (section instanceof ListField) {
            List<String> items = ((ListField) section).getItems();
            list.add(String.valueOf(items.size()));
            list.addAll(items);
        } else {
            List<Experience> experiences = ((ExperienceField) section).getExperiences();
            list.add(String.valueOf(experiences.size()));
            for (Experience exp : experiences) {
                list.add(exp.getHomePage().getName());
                list.add(exp.getHomePage().getUrl() == null ? "null" : exp.getHomePage().getUrl());
                List<Experience.Periods> periods = exp.getPeriods();
                list.add(String.valueOf(periods.size()));
                for (Experience.Periods period : periods) {
                    list.add(period.getStartDate().toString());
                    list.add(period.getEndDate().toString());
                    list.add(period.getTitle());
                    list.add(period.getDescription() == null ? "null" : period.getDescription());
                }
            }
        }
        return list;
    }
}
