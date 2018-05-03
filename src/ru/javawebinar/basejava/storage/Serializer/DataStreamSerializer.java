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
                SectionType sectionType = section.getKey();
                dos.writeUTF(sectionType.name());
                Section values = section.getValue();
                switch (sectionType){
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(values.toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        for (String st: getListFields(values)){
                            dos.writeUTF(st);
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        for (String st: getExperienceFields(values)){
                            dos.writeUTF(st);
                        }
                        System.out.println("");
                        break;
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
                    case PERSONAL:
                        resume.addSection(sType, new StringField(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int count = Integer.parseInt(dis.readUTF());
                        for (int k = 0; k<count; k++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sType, new ListField(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Experience> expList = new ArrayList<>();
                        int count2 = Integer.parseInt(dis.readUTF());
                        for (int k=0; k<count2; k++){
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

    private List<String> getListFields(Section section){
        List<String> list = new ArrayList<>();
        List<String> items = ((ListField) section).getItems();
        list.add(String.valueOf(items.size()));
        list.addAll(items);
        return list;
    }

    private List<String> getExperienceFields(Section section){
        List<String> list = new ArrayList<>();
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
        return list;
    }
}